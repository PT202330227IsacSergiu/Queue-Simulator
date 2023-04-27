package Logic;

import Model.Server;
import Model.Client;

import java.util.List;

public interface Strategy {
    public void addClient(List<Server> servers, Client t);
}

