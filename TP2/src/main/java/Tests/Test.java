package Tests;

import Logic.SelectionPolicy;
import Logic.SimulationManager;

public class Test {

    public Test(){
    }

    public void test(int timeLimit, int numberOfServers, int numberOfClients, int
            maxServiceTime, int minServiceTime, int maxArrivalTime, int minArrivalTime,
                     SelectionPolicy selectionPolicy, String test){

        SimulationManager gen = new SimulationManager(timeLimit, numberOfServers, numberOfClients,
                maxServiceTime, minServiceTime, maxArrivalTime, minArrivalTime,
                selectionPolicy, test, null);
        Thread t = new Thread(gen);
        t.start();
    }

}
