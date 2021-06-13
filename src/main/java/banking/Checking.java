package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Checking extends Accounts {

    Checking(double apr) {
        super(apr, 0);
    }

    @Override
    public boolean validateDepositAmount(double amount) {
        if (amount >= 0 && amount <= 1000) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validateWithdrawAmount(double amount) {
        if (amount >= 0 && amount <= 400) {
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
        this.balance = rounder(this.balance);
    }

    @Override
    public String getCurrentState(String id) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        String current = "Checking " + id + " " + decimalFormat.format(this.balance) + " " + decimalFormat.format(this.apr);
        return current;
    }

}
