package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateProcessorTest {

    public final String[] VALID_CREATE_CHECKING_COMMAND_IN_ARRAY = {"create", "checking", "11001100", "1.2"};
    public final String[] VALID_CREATE_CD_COMMAND_IN_ARRAY = {"create", "cd", "69696969", "4.2", "2549"};

    Bank bank;
    CreateProcessor createProcessor;

    @BeforeEach
    void setup() {
        bank = new Bank();
        createProcessor = new CreateProcessor(bank);
    }

    @Test
    public void initially_no_accounts() {
        assertTrue(bank.getAccounts().get("11001100") == null);
    }

    @Test
    public void create_checking_account() {
        createProcessor.processCreate(VALID_CREATE_CHECKING_COMMAND_IN_ARRAY);
        assertTrue(bank.getAccounts().get("11001100") != null);
        assertTrue(bank.getAccounts().get("11001100").getBalance() == 0);
        assertTrue(bank.getAccounts().get("11001100").getApr() == 1.2);
    }

    @Test
    public void create_cd_account() {
        createProcessor.processCreate(VALID_CREATE_CD_COMMAND_IN_ARRAY);
        assertTrue(bank.getAccounts().get("69696969") != null);
        assertTrue(bank.getAccounts().get("69696969").getBalance() == 2549);
        assertTrue(bank.getAccounts().get("69696969").getApr() == 4.2);
    }


}
