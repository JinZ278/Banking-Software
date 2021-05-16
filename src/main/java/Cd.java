public class Cd extends Accounts {

    public final int MIN_STARTING_AMOUNT = 1000;
    public final int MAX_STARTING_AMOUNT = 10000;
    public final double MIN_APR = 0;
    public final double MAX_APR = 10;


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
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        double withdrawal = withdrawOverBalance(amount);
        this.balance -= withdrawal;
    }

    public double withdrawOverBalance(double num) {
        if (this.balance < num) {
            return this.balance;
        } else {
            return num;
        }
    }

    @Override
    public boolean validateAmount(double amount) {
        return false;
    }

}
