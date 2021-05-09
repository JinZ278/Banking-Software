public class Checking extends Accounts {

    public final int STARTING_AMOUNT = 0;
    public final double MIN_APR = 0;
    public final double MAX_APR = 10;
    public final double MIN_DEPOSIT = 0.01;
    public final double MAX_DEPOSIT = 1000;


    Checking(double apr) {
        super(apr, 0);
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

    @Override
    public double withdrawOverBalance(double num) {
        if (this.balance < num) {
            return this.balance;
        } else {
            return num;
        }
    }

}
