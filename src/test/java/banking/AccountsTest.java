package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountsTest {

    public final double QUICK_APR = 0.6;

    Accounts checking;
    Accounts savings;
    Accounts cd;

    @BeforeEach
    void setup() {
        checking = Accounts.checking(QUICK_APR);
        savings = Accounts.savings(QUICK_APR + 1);
        cd = Accounts.cd(QUICK_APR + 2, 1000);
    }

    @Test
    public void checking_initial_balance() {
        assertEquals(0, checking.getBalance());
    }

    @Test
    public void checking_correct_apr() {
        assertEquals(0.6, checking.getApr());
    }

    @Test
    public void checking_deposit() {
        checking.deposit(500);
        assertEquals(500, checking.getBalance());
    }

    @Test
    public void checking_withdraw() {
        checking.deposit(200);
        checking.withdraw(50);
        assertEquals(150, checking.getBalance());
    }

    @Test
    public void checking_cant_go_below_zero() {
        checking.withdraw(9999999);
        assertEquals(0, checking.getBalance());
    }

    @Test
    public void checking_apr_calculations() {
        checking.deposit(5000);
        checking.aprCalculate();
        assertEquals(5002.50, checking.getBalance());
    }

    //

    @Test
    public void savings_initial_balance() {
        assertEquals(0, savings.getBalance());
    }

    @Test
    public void savings_correct_apr() {
        assertEquals(1.6, savings.getApr());
    }

    @Test
    public void savings_deposit() {
        savings.deposit(1000);
        assertEquals(1000, savings.getBalance());
    }

    @Test
    public void savings_withdraw() {
        savings.deposit(299);
        savings.withdraw(99);
        assertEquals(200, savings.getBalance());
    }

    @Test
    public void savings_cant_go_below_zero() {
        savings.withdraw(666666);
        assertEquals(0, savings.getBalance());
    }

    @Test
    public void savings_apr_calculations() {
        savings.deposit(5000);
        savings.aprCalculate();
        assertEquals(5006.66, savings.getBalance());
    }

    //

    @Test
    public void cd_initial_balance() {
        assertEquals(1000, cd.getBalance());
    }

    @Test
    public void cd_correct_apr() {
        assertEquals(2.6, cd.getApr());
    }

    @Test
    public void cd_withdraw() {
        cd.deposit(900);
        cd.withdraw(299);
        assertEquals(1601, cd.getBalance());
    }

    @Test
    public void cd_cant_go_below_zero() {
        cd.withdraw(33333);
        assertEquals(0, cd.getBalance());
    }

    //

    @Test
    public void decimal_apr() {
        checking = Accounts.checking(1.11);
        assertEquals(1.11, checking.getApr());
    }

    @Test
    public void decimal_balance() {
        cd = Accounts.cd(1, 10001.99);
        assertEquals(10001.99, cd.getBalance());
    }

    @Test
    public void decimal_apr_and_balance() {
        cd = Accounts.cd(9.99, 1000.42);
        assertEquals(9.99, cd.getApr());
        assertEquals(1000.42, cd.getBalance());
    }

    @Test
    public void depositing_decimal_amounts() {
        checking.deposit(2.55);
        assertEquals(2.55, checking.getBalance());
    }

    @Test
    public void withdrawing_decimal_amounts() {
        savings.deposit(500);
        savings.withdraw(200.98);
        assertEquals(299.02, savings.getBalance());
    }

    @Test
    public void deposit_zero() {
        checking.deposit(0);
        assertEquals(0, checking.getBalance());
    }

    @Test
    public void withdraw_zero() {
        savings.withdraw(0);
        assertEquals(0, savings.getBalance());
    }
}
