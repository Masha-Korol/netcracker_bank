package model;

import threads.ClientGeneratorThread;
import threads.OperatorThread;

import java.util.ArrayDeque;
import java.util.*;

public class Bank {
    /**
     * The number of operators equals the number of the queues
     */
    private final int NUMBER_OF_OPERATORS = 3;
    private final int MAX_QUEUE_SIZE = 1000000;

    private List<ArrayDeque<Client>> queues = new ArrayList<>(NUMBER_OF_OPERATORS);
    private List<OperatorThread> operators = new ArrayList<>(NUMBER_OF_OPERATORS);

    /**
     * This method starts work of the bank - starts all the needed threads
     */
    public void startWorking() {
        for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
            queues.add(new ArrayDeque<>());
        }

        //create operators threads, attaching to them their own queues
        for (ArrayDeque<Client> queue : queues) {
            OperatorThread operator = new OperatorThread(queue);
            operators.add(operator);
        }

        //start all operators threads
        for (OperatorThread operator : operators) {
            Thread thread = new Thread(operator);
            thread.start();
        }

        //start thread that generates clients
        ClientGeneratorThread clientGeneratorThread = new ClientGeneratorThread(this);
        Thread thread = new Thread(clientGeneratorThread);
        thread.start();
    }

    /**
     * This method adds new client to one of the queues based on their sizes (to whichever is the shortest)
     *
     * @param client new client
     */
    public void addClientToTheQueue(Client client) {

        //search for the smallest queue index in the array of queues
        int i = 0;
        int theSmallestQueueSize = MAX_QUEUE_SIZE;
        int theSmallestQueueIndex = -1;
        for(ArrayDeque<Client> queue : queues){
            if (queue.size() < theSmallestQueueSize) {
                theSmallestQueueIndex = i;
                theSmallestQueueSize = queue.size();
            }
            i++;
        }

        //add new client to the smallest queue and
        //if the smallest queue is empty, notify its operator
        ArrayDeque<Client> theSmallestQueue = queues.get(theSmallestQueueIndex);
        if (theSmallestQueue.isEmpty()) {
            synchronized (operators.get(theSmallestQueueIndex)) {
                theSmallestQueue.add(client);
                operators.get(theSmallestQueueIndex).notify();
            }
        } else {
            theSmallestQueue.add(client);
        }
    }
}
