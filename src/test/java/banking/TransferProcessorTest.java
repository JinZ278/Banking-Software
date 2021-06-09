package banking;

import org.junit.jupiter.api.BeforeEach;

public class TransferProcessorTest {

    public final String QUICK_ID = "12345678";
    public final String QUICK_ID2 = "87654321";

    Bank bank;
    TransferProcessor transferProcessor;
    Accounts checkingAccount;
    Accounts savingsAccount;

    @BeforeEach
    void setup() {
        bank = new Bank();
        transferProcessor = new TransferProcessor();
        checkingAccount = Accounts.checking(2);
        savingsAccount = Accounts.savings(2);
        bank.addAccount(QUICK_ID, checkingAccount);
        bank.addAccount(QUICK_ID2, savingsAccount);
    }
}
