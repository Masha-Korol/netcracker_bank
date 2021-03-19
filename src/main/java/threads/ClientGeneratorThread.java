package threads;

import model.Bank;
import model.Client;
import model.ClientGenerator;
import java.util.Random;

/**
 * This thread periodically generates client
 */
public class ClientGeneratorThread implements Runnable {
    private Bank bank;

    public ClientGeneratorThread(Bank bank) {
        this.bank = bank;
    }

    /**
     * This method generated client and puts them in the queues
     */
    private synchronized void generateClient() {
        Random random = new Random();
        ClientGenerator clientGenerator = ClientGenerator.getInstance();
        try {
            wait(random.nextInt(3001) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Client client = clientGenerator.generateClient();
        bank.addClientToTheQueue(client);
    }

    @Override
    public void run() {
        for (; ; ) {
            generateClient();
        }
    }
}
