package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;

abstract class Accounts {

    protected double apr;
    protected double balance;

    Accounts(double apr, double balance) {
        this.apr = rounder(apr);
        this.balance = rounder(balance);
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

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    public abstract double getApr();

    public abstract boolean validateAmount(double amount);

    public abstract void aprCalculate();

}
