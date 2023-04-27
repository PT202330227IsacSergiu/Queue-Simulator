package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;

    public Server(int capacity) {
        clients = new ArrayBlockingQueue<>(capacity);
        waitingPeriod = new AtomicInteger(0);
    }

    public void addClient(Client c) {
        clients.add(c);
        waitingPeriod.addAndGet(c.getServiceTime());
    }


    @Override
    public void run() {
        while (true) {
            if (clients.size() != 0) {
                Client c = clients.peek();
                try {
                    Thread.sleep(c.getServiceTime() * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                c = clients.remove();
                waitingPeriod.addAndGet(-c.getServiceTime());
            }
        }
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public Client[] getClients() {
        Client[] tasksArr = new Client[clients.size()];
        return clients.toArray(tasksArr);
    }
}
