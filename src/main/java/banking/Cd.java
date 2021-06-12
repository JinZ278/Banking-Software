package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Cd extends Accounts {

    Cd(double apr, double balance) {
        super(apr, balance);
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public double getApr() {
        return this.apr;
    }

    @Override
    public double getAge() {
        return this.age;
    }

    ;

    @Override
    public void deposit(double amount) {
        this.balance = rounder(this.balance + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (this.balance <= amount) {
            amount = this.balance;
        }
        this.balance = rounder(this.balance - amount);
    }

    @Override
    public boolean validateDepositAmount(double amount) {
        return false;
    }

    @Override
    public boolean validateWithdrawAmount(double amount) {
        if (amount >= this.balance && this.age >= 12) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void aprCalculate() {
        double rate = this.apr / 100 / 12;
        double interest = rate * this.balance;

        this.balance += interest;
        interest = rate * this.balance;
        this.balance += interest;
        interest = rate * this.balance;
        this.balance += interest;
        interest = rate * this.balance;
        this.balance += interest;

        this.balance = rounder(this.balance);
    }

    @Override
    public String getCurrentState(String id) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        String current = "Cd " + id + " " + decimalFormat.format(this.balance) + " " + decimalFormat.format(this.apr);
        return current;
    }

}