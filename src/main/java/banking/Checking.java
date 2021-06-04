package banking;

public class Checking extends Accounts {

    public final int MIN_STARTING_AMOUNT = 0;
    public final double MIN_APR = 0;
    public final double MAX_APR = 10;
    public final double MIN_DEPOSIT = 0;
    public final double MAX_DEPOSIT = 1000;


    Checking(double apr) {
        super(apr, 0);
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
    public void deposit(double amount) {
        this.balance += rounder(amount);
    }

    @Override
    public void withdraw(double amount) {
        double withdrawal = withdrawOverBalance(amount);
        this.balance -= rounder(withdrawal);
    }

    public double withdrawOverBalance(double num) {
        if (this.balance <= num) {
            return this.balance;
        } else {
            return num;
        }
    }

    @Override
    public boolean validateAmount(double amount) {
        if (amount >= MIN_DEPOSIT && amount <= MAX_DEPOSIT) {
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
    public int getAge() {
        return this.age;
    }

}
