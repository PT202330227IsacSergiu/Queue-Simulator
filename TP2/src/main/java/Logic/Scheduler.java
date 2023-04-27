package Logic;

import Model.Server;
import Model.Client;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServers){
        servers = new ArrayList<>(maxNoServers);
        for (int i = 0; i < maxNoServers; i++) {
            Server s = new Server(maxTasksPerServers);
            servers.add(s);
            Thread t = new Thread(s);
            t.start();
        }

    }

    public void ChangeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();

        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchClient(Client c){

        strategy.addClient(servers, c);
    }

    public List<Server> getServers(){
        return servers;
    }

    public int getServersTotal(){
        int size = 0;
        for (int i = 0; i < servers.size(); i++) {
            size += servers.get(i).getClients().length;
        }
        return size;
    }
    public int getServersWait(){
        int wait = 0;
        for (int i = 0; i < servers.size(); i++) {
            wait += servers.get(i).getWaitingPeriod();
        }
        return wait;
    }
}
