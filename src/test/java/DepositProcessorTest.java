import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositProcessorTest {

    public final String[] VALID_DEPOSIT_CHECKING_COMMAND_IN_ARRAY = {"Deposit", "12345999", "12.22"};
    public final String[] VALID_DEPOSIT_SAVINGS_COMMAND_IN_ARRAY = {"Deposit", "29292000", "20"};
    Bank bank;
    DepositProcessor depositProcessor;
    Accounts accounts;

    @BeforeEach
    void setup() {
        bank = new Bank();
        depositProcessor = new DepositProcessor(bank);
    }

    @Test
    public void deposits_in_checking_account() {
        accounts = Accounts.checking(1);
        bank.addAccount("12345999", accounts);
        assertEquals(0, bank.getAccounts().get("12345999").getBalance());
        depositProcessor.processDeposit(VALID_DEPOSIT_CHECKING_COMMAND_IN_ARRAY);
        assertEquals(12.22, bank.getAccounts().get("12345999").getBalance());
    }

    @Test
    public void deposits_in_savings_account() {
        accounts = Accounts.savings(2);
        bank.addAccount("29292000", accounts);
        assertEquals(0, bank.getAccounts().get("29292000").getBalance());
        depositProcessor.processDeposit(VALID_DEPOSIT_SAVINGS_COMMAND_IN_ARRAY);
        assertEquals(20, bank.getAccounts().get("29292000").getBalance());
    }

}
