package model;

import java.util.Objects;

public class Terminal {
    private static Terminal instance;
    private int cash;

    public double getCash() {
        return cash;
    }

    /**
     * This method deposits money
     * @param amount the amount of money to be deposited
     */
    public void depositCash(int amount) {
        this.cash += amount;
        System.out.println("DEPOSIT operation. Cash now = " + this.cash);
    }

    /**
     * This method withdraws money if possible
     * @param amount the amount of money to be withdrawn
     */
    public synchronized void withdrawCash(int amount) {
        if (this.cash - amount >= 0) {
            this.cash -= amount;
            System.out.println("WITHDRAW operation. Cash now = " + this.cash);
        } else{
            System.out.println("Operation denied! Cash now = " + this.cash+ ", you can't withdraw " + amount);
        }
    }

    private Terminal(){}

    /**
     * Singleton realisation - this method is used to get an instance of this type in combination with private constructor
     * @return instance of current type
     */
    public static Terminal getInstance() {
        if(instance == null){
            instance = new Terminal();
        }
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal terminal = (Terminal) o;
        return cash == terminal.cash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cash);
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "cash=" + cash +
                '}';
    }
}
