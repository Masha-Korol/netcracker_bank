package model;

import java.util.Objects;

public class Client {
    private OperationType operationType;
    private int amount;
    /**
     * Time that needs to be spent on a client service (in milliseconds)
     */
    private int serviceTime;

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Client(OperationType operationType, int amount, int serviceTime) {
        this.operationType = operationType;
        this.amount = amount;
        this.serviceTime = serviceTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return amount == client.amount &&
                serviceTime == client.serviceTime &&
                operationType == client.operationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, amount, serviceTime);
    }

    @Override
    public String toString() {
        return "Client{" +
                "operationType=" + operationType +
                ", amount=" + amount +
                ", secondsCount=" + serviceTime +
                '}';
    }
}
