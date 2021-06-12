package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferProcessorTest {

    public final String QUICK_ID = "12345678";
    public final String QUICK_ID2 = "87654321";

    public final String[] TRANSFER_CHECKING_TO_CHECKING = {"Transfer", "12345678", "12345679", "100"};
    public final String[] TRANSFER_CHECKING_TO_SAVINGS = {"Transfer", "12345678", "87654321", "200"};
    public final String[] TRANSFER_SAVINGS_TO_CHECKING = {"Transfer", "87654321", "12345678", "300"};
    public final String[] TRANSFER_SAVINGS_TO_SAVINGS = {"Transfer", "87654321", "87654322", "400"};

    Bank bank;
    TransferProcessor transferProcessor;
    Accounts checkingAccount;
    Accounts savingsAccount;

    @BeforeEach
    void setup() {
        bank = new Bank();
        transferProcessor = new TransferProcessor(bank);
        checkingAccount = Accounts.checking(2);
        savingsAccount = Accounts.savings(2);
        checkingAccount.balance = 5000;
        savingsAccount.balance = 5000;
        bank.addAccount(QUICK_ID, checkingAccount);
        bank.addAccount(QUICK_ID2, savingsAccount);
    }

    @Test
    public void transfer_checking_to_checking() {
        Accounts checkingAccount2 = Accounts.checking(3);
        bank.addAccount("12345679", checkingAccount2);
        transferProcessor.processTransfer(TRANSFER_CHECKING_TO_CHECKING);
        assertEquals(4900, bank.getAccounts().get("12345678").getBalance());
        assertEquals(100, bank.getAccounts().get("12345679").getBalance());
    }

    @Test
    public void transfer_checking_to_savings() {
        transferProcessor.processTransfer(TRANSFER_CHECKING_TO_SAVINGS);
        assertEquals(4800, bank.getAccounts().get("12345678").getBalance());
        assertEquals(5200, bank.getAccounts().get("87654321").getBalance());
    }

    @Test
    public void transfer_savings_to_checking() {
        transferProcessor.processTransfer(TRANSFER_SAVINGS_TO_CHECKING);
        assertEquals(4700, bank.getAccounts().get(QUICK_ID2).getBalance());
        assertEquals(5300, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void transfer_savings_to_savings() {
        Accounts savingsAccount2 = Accounts.savings(3);
        bank.addAccount("87654322", savingsAccount2);
        transferProcessor.processTransfer(TRANSFER_SAVINGS_TO_SAVINGS);
        assertEquals(4600, bank.getAccounts().get(QUICK_ID2).getBalance());
        assertEquals(400, bank.getAccounts().get("87654322").getBalance());
    }
}
