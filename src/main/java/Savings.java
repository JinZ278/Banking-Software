public class Savings extends Accounts {

    public final int STARTING_AMOUNT = 0;
    public final double MIN_APR = 0;
    public final double MAX_APR = 10;
    public final double MIN_DEPOSIT = 0;
    public final double MAX_DEPOSIT = 1000;


    Savings(double apr) {
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
        if (amount >= MIN_DEPOSIT && amount <= MAX_DEPOSIT) {
            return true;
        } else {
            return false;
        }
    }
}
