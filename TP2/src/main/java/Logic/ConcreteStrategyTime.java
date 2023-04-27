package Logic;

import Model.Server;
import Model.Client;

import java.util.Collections;
import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    public ConcreteStrategyTime(){

    }
    @Override
    public void addClient(List<Server> servers, Client t) {
        int pos = 0;
        int min = servers.get(0).getWaitingPeriod();
        for (int i = 1; i < servers.size(); i++) {
            if(servers.get(i).getWaitingPeriod() < min){
                pos = i;
                min = servers.get(i).getWaitingPeriod();
            }
        }
        servers.get(pos).addClient(t);
    }
}
