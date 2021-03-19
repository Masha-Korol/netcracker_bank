package model;

import java.util.Random;

/**
 * This class is used to generate entities of type Client
 */
public class ClientGenerator {
    private final int MIN_MONEY = 1;
    private final int MAX_MONEY = 100000;
    private final int MIN_SERVICE_TIME = 1000;
    private final int MAX_SERVICE_TIME = 8000;
    private static ClientGenerator instance;

    /**
     * This method generates new entity of type Client with randomly filled fields
     * Filling of fields is based on max/min final values, initialized in this class
     * @return new Client entity
     */
    public Client generateClient() {
        Random random = new Random();
        int randomAmountOfMoney = random.nextInt(MAX_MONEY - MIN_MONEY) + MIN_MONEY;
        int randomServiceTime = random.nextInt(MAX_SERVICE_TIME - MIN_SERVICE_TIME)
                + MIN_SERVICE_TIME;
        Client client = new Client(randomEnum(OperationType.class),
                randomAmountOfMoney,
                randomServiceTime);
        System.out.println("New client: " + client.toString());
        return client;
    }

    /**
     * This method returns random enum entity of passed type
     * @param clazz enum class
     * @param <T>
     * @return randomly chosen enum entity
     */
    private <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    private ClientGenerator(){}

    /**
     * Singleton realisation - this method is used to get an instance of this type in combination with private constructor
     * @return instance of current type
     */
    public static ClientGenerator getInstance(){
        if (instance == null){
            instance = new ClientGenerator();
        }
        return instance;
    }
}
