package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AprCalculationTest {
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
    public void checking_apr_calculations() {
        checking.deposit(5000);
        checking.aprCalculate();
        assertEquals(5002.50, checking.getBalance());
    }

    @Test
    public void savings_apr_calculations() {
        savings.deposit(5000);
        savings.aprCalculate();
        assertEquals(5006.66, savings.getBalance());
    }

    @Test
    public void cd_apr_calculations() {
        cd.deposit(5000);
        cd.aprCalculate();
        assertEquals(6052.16, cd.getBalance());
    }

}
