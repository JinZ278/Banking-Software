package banking;

import org.junit.jupiter.api.BeforeEach;

public class PassProcessorTest {
    public final String QUICK_ID = "12345678";
    public final String QUICK_ID2 = "12121212";
    public final String QUICK_ID3 = "11111111";
    Bank bank;
    PassProcessor passProcessor;
    Accounts checkingAccount;
    Accounts savingsAccount;
    Accounts cdAccount;

    @BeforeEach
    void setup() {
        bank = new Bank();
        passProcessor = new PassProcessor();
        checkingAccount = Accounts.checking(1);
        savingsAccount = Accounts.checking(1.5);
        cdAccount = Accounts.cd(2, 2000);
    }
}
