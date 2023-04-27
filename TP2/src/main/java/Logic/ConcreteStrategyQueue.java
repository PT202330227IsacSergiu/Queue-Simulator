package Logic;

import Model.Server;
import Model.Client;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{

    public ConcreteStrategyQueue(){

    }
    @Override
    public void addClient(List<Server> servers, Client t) {
        int pos = 0;
        int min = servers.get(0).getClients().length;
        for (int i = 1; i < servers.size(); i++) {
            if(servers.get(i).getClients().length < min){
                pos = i;
                min = servers.get(i).getClients().length;
            }
        }
        servers.get(pos).addClient(t);
    }
}
