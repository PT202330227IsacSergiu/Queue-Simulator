package GUI;

import Logic.SelectionPolicy;
import Logic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerInputs {


    private FrameGetInputData frame;

    public ControllerInputs(FrameGetInputData frame, String log) {

        this.frame = frame;
        this.frame.startButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int timeLimit = 0, numberOfServers = 0, numberOfClients = 0, maxServiceTime = 0, minServiceTime = 0, maxArrivalTime = 0, minArrivalTime = 0;
                try {
                    timeLimit = Integer.parseInt(frame.getMaxSimTime());
                    numberOfServers = Integer.parseInt(frame.getNoQueues());
                    numberOfClients = Integer.parseInt(frame.getNoClients());
                    maxServiceTime = Integer.parseInt(frame.getMaxService());
                    minServiceTime = Integer.parseInt(frame.getMinService());
                    maxArrivalTime = Integer.parseInt(frame.getMaxArrival());
                    minArrivalTime = Integer.parseInt(frame.getMinArrival());
                }catch (Exception ex){

                    JOptionPane.showMessageDialog(null, "Only numbers are accepted");
                }
                SelectionPolicy selectionPolicy = frame.getStrategy();

                SimulationStatusFrame simframe = new SimulationStatusFrame();

                SimulationManager gen = new SimulationManager(timeLimit, numberOfServers, numberOfClients,
                        maxServiceTime, minServiceTime, maxArrivalTime, minArrivalTime,
                        selectionPolicy, log, simframe);

                Thread t = new Thread(gen);
                t.start();

            }
        });


    }

}
