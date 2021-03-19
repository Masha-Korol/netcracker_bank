package threads;

import model.Client;
import model.Terminal;

import java.util.ArrayDeque;

/**
 * This thread imitated the work of an operator in the bank
 */
public class OperatorThread implements Runnable {
    private ArrayDeque<Client> queue;
    private Terminal terminal;

    public OperatorThread(ArrayDeque<Client> queue) {
        this.queue = queue;
        this.terminal = Terminal.getInstance();
    }

    /**
     * This method withdraws money from the terminal cash
     * @param amount the amount of money to by withdrawn
     */
    private void withdrawMoney(int amount) {
        this.terminal.withdrawCash(amount);
    }

    /**
     * This method deposits money from the terminal cash
     * @param amount the amount of money to by deposited
     */
    private void depositMoney(int amount) {
        this.terminal.depositCash(amount);
    }

    /**
     * This method performs an operation with client
     * @param client client with whom an operation has to be performed
     */
    private void performOperation(Client client) throws InterruptedException {
        wait(client.getServiceTime());
        switch (client.getOperationType()) {
            case DEPOSIT:
                this.depositMoney(client.getAmount());
                break;
            case WITHDRAW:
                this.withdrawMoney(client.getAmount());
                break;
        }
    }

    private void sleepWhileWaitingForClient() throws InterruptedException {
        if(queue.isEmpty()){
            wait();
        }
    }

    /**
     * This method takes the first client from the queue,
     * performs an operation with him,
     * removes him from the queue,
     * if he was the last one in the queue, current thread waits for new clients to come
     */
    private synchronized void dealWithClient() throws InterruptedException {
        sleepWhileWaitingForClient();
        if (!queue.isEmpty()) {
            Client client = queue.getFirst();
            this.performOperation(client);
            queue.removeFirst();
            sleepWhileWaitingForClient();
        }
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                dealWithClient();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
