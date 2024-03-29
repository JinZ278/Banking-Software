package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Savings extends Accounts {

    Savings(double apr) {
        super(apr, 0);
    }

    @Override
    public boolean validateDepositAmount(double amount) {
        if (amount >= 0 && amount <= 2500) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validateWithdrawAmount(double amount) {
        if (amount >= 0 && amount <= 1000 && this.withdrawn == false) {
            this.withdrawn = true;
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

        String current = "Savings " + id + " " + decimalFormat.format(this.balance) + " " + decimalFormat.format(this.apr);
        return current;
    }

}
