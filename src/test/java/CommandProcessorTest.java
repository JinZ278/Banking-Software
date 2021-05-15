import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandProcessorTest {

    public final String VALID_CREATE_CHECKING_COMMAND = "Create Checking 12345678 1.5";
    public final String VALID_CREATE_SAVINGS_COMMAND = "Create Savings 29292929 3.0";
    public final String VALID_CREATE_CD_COMMAND = "Create Cd 99999999 5.7 1000";

    CommandValidator commandValidator;
    CommandProcessor commandProcessor;
    Bank bank;

    @BeforeEach
    void setup() {
        commandValidator = new CommandValidator();
        commandProcessor = new CommandProcessor();
        bank = new Bank();
    }

    @Test
    public void process_create_checking_command() {
        assertTrue(bank.getAccounts().get("12345678") == null);
        commandProcessor.process(VALID_CREATE_CHECKING_COMMAND, bank);
        assertTrue(bank.getAccounts().get("12345678") != null);
        assertTrue(bank.getAccounts().get("12345678").getApr() == 1.5);
        assertTrue(bank.getAccounts().get("12345678").getBalance() == 0);
    }

    @Test
    public void process_create_savings_command() {
        assertTrue(bank.getAccounts().get("29292929") == null);
        commandProcessor.process(VALID_CREATE_SAVINGS_COMMAND, bank);
        assertTrue(bank.getAccounts().get("29292929") != null);
        assertTrue(bank.getAccounts().get("29292929").getApr() == 3.0);
        assertTrue(bank.getAccounts().get("29292929").getBalance() == 0);
    }

    @Test
    public void process_create_cd_command() {
        assertTrue(bank.getAccounts().get("99999999") == null);
        commandProcessor.process(VALID_CREATE_CD_COMMAND, bank);
        assertTrue(bank.getAccounts().get("99999999") != null);
        assertTrue(bank.getAccounts().get("99999999").getApr() == 5.7);
        assertTrue(bank.getAccounts().get("99999999").getBalance() == 1000);
    }

    @Test
    public void process_deposit_checking_command() {

    }

}
