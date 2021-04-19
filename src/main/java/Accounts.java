public class Accounts {

    private final double apr;
    private double balance;

    Accounts(double apr, double balance) {
        this.apr = apr;
        this.balance = balance;
    }

    static Accounts checking(double apr) {
        return new Checking(apr);
    }

    static Accounts savings(double apr) {
        return new Savings(apr);
    }

    static Accounts cd(double apr, double balance) {
        return new Cd(apr, balance);
    }

    public double getBalance() {
        return this.balance;
    }

    public double getApr() {
        return this.apr;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

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

}
