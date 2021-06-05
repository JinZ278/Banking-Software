package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandProcessorTest {

    public final String VALID_CREATE_COMMAND = "Create Checking 12345678 1";
    public final String VALID_CREATE_CHECKING_COMMAND = "Create Checking 12345678 1.5";
    public final String VALID_CREATE_SAVINGS_COMMAND = "Create Savings 29292929 3.0";
    public final String VALID_CREATE_CD_COMMAND = "Create Cd 99999999 5.7 1000";

    public final String VALID_DEPOSIT_CHECKING_COMMAND = "Deposit 12345678 1200";
    public final String VALID_DEPOSIT_SAVINGS_COMMAND = "Deposit 29292929 2000";

    public final String VALID_PASS_COMMAND = "Pass 23";


    CommandValidator commandValidator;
    CommandProcessor commandProcessor;
    Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    public void processor_creates_account() {
        assertTrue(bank.getAccounts().get("12345678") == null);
        commandProcessor.process(VALID_CREATE_COMMAND);
        assertTrue(bank.getAccounts().get("12345678") != null);
    }

    @Test
    public void process_create_checking_command() {
        commandProcessor.process(VALID_CREATE_CHECKING_COMMAND);
        assertTrue(bank.getAccounts().get("12345678").getApr() == 1.5);
        assertTrue(bank.getAccounts().get("12345678").getBalance() == 0);
    }

    @Test
    public void process_create_savings_command() {
        commandProcessor.process(VALID_CREATE_SAVINGS_COMMAND);
        assertTrue(bank.getAccounts().get("29292929").getApr() == 3.0);
        assertTrue(bank.getAccounts().get("29292929").getBalance() == 0);
    }

    @Test
    public void process_create_cd_command() {
        commandProcessor.process(VALID_CREATE_CD_COMMAND);
        assertTrue(bank.getAccounts().get("99999999").getApr() == 5.7);
        assertTrue(bank.getAccounts().get("99999999").getBalance() == 1000);
    }

    //

    @Test
    public void process_deposit_checking_command() {
        commandProcessor.process(VALID_CREATE_CHECKING_COMMAND);
        assertTrue(bank.getAccounts().get("12345678").getBalance() == 0);
        commandProcessor.process(VALID_DEPOSIT_CHECKING_COMMAND);
        assertTrue(bank.getAccounts().get("12345678").getBalance() == 1200);
    }

    @Test
    public void process_deposit_savings_command() {
        commandProcessor.process(VALID_CREATE_SAVINGS_COMMAND);
        assertTrue(bank.getAccounts().get("29292929").getBalance() == 0);
        commandProcessor.process(VALID_DEPOSIT_SAVINGS_COMMAND);
        assertTrue(bank.getAccounts().get("29292929").getBalance() == 2000);
    }

    @Test
    public void process_deposit_twice() {
        commandProcessor.process(VALID_CREATE_CHECKING_COMMAND);
        assertTrue(bank.getAccounts().get("12345678").getBalance() == 0);

        commandProcessor.process("Deposit 12345678 2000");
        assertTrue(bank.getAccounts().get("12345678").getBalance() == 2000);

        commandProcessor.process("Deposit 12345678 69.42");
        assertTrue(bank.getAccounts().get("12345678").getBalance() == 2069.42);
    }

    //

    @Test
    public void process_pass_twenty_three_months() {
        commandProcessor.process(VALID_CREATE_CHECKING_COMMAND);
        commandProcessor.process("Deposit 12345678 2000");
        commandProcessor.process(VALID_CREATE_SAVINGS_COMMAND);
        commandProcessor.process("Deposit 29292929 2130");
        commandProcessor.process(VALID_PASS_COMMAND);
        assertTrue(bank.getAccounts().get("12345678").age == 23);
        assertTrue(bank.getAccounts().get("12345678").balance == 2058.18);
        assertTrue(bank.getAccounts().get("29292929").age == 23);
    }

}
