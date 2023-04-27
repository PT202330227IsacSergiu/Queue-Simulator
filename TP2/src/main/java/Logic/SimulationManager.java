package Logic;

import GUI.SimulationStatusFrame;
import Model.Client;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int numberOfServers;
    public int numberOfClients;

    public int maxServiceTime;
    public int minServiceTime;

    public int maxArrivalTime;
    public int minArrivalTime;

    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    private List<Client> generatedClients = new ArrayList<>();

    private String fileLogName;
    private FileWriter writeLogFile;

    private double avgWaitingTime = 0.0;
    private double avgServiceTime = 0.0;
    private int peekHour = 0;

    private String currStatus;
    private SimulationStatusFrame simFrame;

    public SimulationManager(int timeLimit, int numberOfServers, int numberOfClients, int
            maxServiceTime, int minServiceTime, int maxArrivalTime, int minArrivalTime,
                             SelectionPolicy selectionPolicy,
                             String fileLogName, SimulationStatusFrame simFrame) {

        this.timeLimit = timeLimit;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.maxServiceTime = maxServiceTime;
        this.minServiceTime = minServiceTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.fileLogName = fileLogName;

        try {
            validateInputs(numberOfClients, numberOfServers, timeLimit, maxArrivalTime, minArrivalTime, maxServiceTime, minServiceTime);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.simFrame = simFrame;
        this.scheduler = new Scheduler(numberOfServers, numberOfClients);
        this.scheduler.ChangeStrategy(selectionPolicy);
        generateNRandomClients(numberOfClients, maxArrivalTime, minArrivalTime, maxServiceTime, minServiceTime);


    }

    private void validateInputs(int noClients, int noQueues, int serviceTime,
                                int maxArrival, int minArrival, int maxService, int minService) throws Exception {
        if (noClients < 0 || noQueues < 0 || serviceTime < 0 || minArrival < 0 || maxArrival < 0 || minService < 0 || maxService < 0) {
            throw new Exception("Every value must be a positive integer !");
        }

        if (minArrival > maxArrival) {
            throw new Exception("Max. arrival time must be greater than min. arrival time !");
        }

        if (minService > maxService) {
            throw new Exception("Max. service time must be greater than min. service time !");
        }

    }

    public void generateNRandomClients(int numberOfClients, int maxArrivalTime, int minArrivalTime, int maxServiceTime, int minServiceTime) {
        double svcTime = 0.0;
        for (int i = 1; i <= numberOfClients; i++) {
            Random random = new Random();

            int arrivalTime = random.nextInt(minArrivalTime, maxArrivalTime + 1);
            int serviceTime = random.nextInt(minServiceTime, maxServiceTime + 1);

            Client c = new Client(i, arrivalTime, serviceTime);

            generatedClients.add(c);
        }
    }

    @Override
    public void run() {

        File file = new File(fileLogName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int currentTime = 0;
        int initNoClient = generatedClients.size();
        String strRes = "";
        String strCurrTime = "";
        int peekC = 0;
        while (currentTime < timeLimit) {
            System.out.println(currentTime);
            double currAvg = 0.0;
            int k = 0;
            while (k < generatedClients.size()) {
                int currClientArrival = generatedClients.get(k).getArrivalTime();
                if (currClientArrival == currentTime) {
                    scheduler.dispatchClient(generatedClients.remove(k));
                    currAvg = svrWait() / initNoClient;

                } else {
                    k++;
                }
            }

            avgWaitingTime += currAvg;
            if (peekClients() > peekC) {
                peekC = peekClients();
                peekHour = currentTime;
            }

            strCurrTime = strResFunc(currentTime);
            updateFrmae(strCurrTime);
            strRes += strCurrTime + "\n";

            currentTime++;
            sleep1sec();
        }

        writeInLog(file, strRes);
    }

    public double svrWait() {
        return scheduler.getServersWait();
    }

    public int peekClients() {
        return scheduler.getServersTotal();
    }

    private String strResFunc(int currentTime) {
        String strRes = "";
        strRes += "TIME: [" + currentTime + "]\n\n";
        strRes += "Waiting:\n";
        for (int i = 0; i < generatedClients.size(); i++) {
            strRes += generatedClients.get(i) + "  ";
            if ((i + 1) % 15 == 0) strRes += "\n";
        }
        strRes += "\n\n";
        for (int i = 0; i < scheduler.getServers().size(); i++) {
            strRes += "Queue " + (i + 1) + ": ";
            for (int j = 0; j < scheduler.getServers().get(i).getClients().length; j++) {
                strRes += scheduler.getServers().get(i).getClients()[j].toString() + " ";

            }
            if (scheduler.getServers().get(i).getClients().length > 0)
                scheduler.getServers().get(i).getClients()[0].setServiceTime(scheduler.getServers().get(i).getClients()[0].getServiceTime() - 1);
            strRes += "\n";
        }

        strRes += "\n";

        return strRes;
    }

    public void updateFrmae(String strCurrTime) {
        if (this.simFrame != null)
            this.simFrame.getStatusArea().setText(strCurrTime);
    }

    public void sleep1sec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeInLog(File file, String strRes) {
        try {
            writeLogFile = new FileWriter(file);
            writeLogFile.write(strRes);
            writeLogFile.write("Avg. waiting time: " + avgWaitingTime + "\n");
            writeLogFile.write("Avg. service time: " + avgServiceTime + "\n");
            writeLogFile.write("Peek hour: " + peekHour + "\n");
            writeLogFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
