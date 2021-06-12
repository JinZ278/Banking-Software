package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

abstract class Accounts {

    public List<String> history;
    protected int age;
    protected double apr;
    protected double balance;
    protected boolean withdrawn;

    Accounts(double apr, double balance) {
        this.apr = rounder(apr);
        this.balance = rounder(balance);
        this.age = 0;
        this.withdrawn = false;
        this.history = new ArrayList<>();
    }

    static Accounts checking(double apr) {
        Checking checking = new Checking(apr);
        return checking;
    }

    static Accounts savings(double apr) {
        Savings savings = new Savings(apr);
        return savings;
    }

    static Accounts cd(double apr, double balance) {
        Cd cd = new Cd(apr, balance);
        return cd;
    }

    public double rounder(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String value = decimalFormat.format(amount);
        return Double.parseDouble(value);
    }


    public abstract double getBalance();

    public abstract double getApr();

    public abstract double getAge();

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    public abstract boolean validateDepositAmount(double amount);

    public abstract boolean validateWithdrawAmount(double amount);

    public abstract void aprCalculate();

    public abstract String getCurrentState(String id);
}
