import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public final String CREATE_SAVINGS = "Create Savings 12345678 1.2";
    public final String WRONG_CREATE_SAVINGS = "Create Savings 22 2.2";

    public final String DEPOSIT_CHECKING = "Deposit 11122233 250.3";
    public final String WRONG_DEPOSIT_CHECKING = "Deposit 11122233 25022.3";

    public final String EMPTY_STRING = "";
    public final String JUST_SPACES = "    ";

    CommandValidator commandValidator;
    Bank bank;

    @BeforeEach
    void setup() {
        commandValidator = new CommandValidator();
        bank = new Bank();
    }

    @Test
    public void correct_create_string() {
        assertTrue(commandValidator.validate(CREATE_SAVINGS, bank));
    }


    @Test
    public void wrong_create_string() {
        assertFalse(commandValidator.validate(WRONG_CREATE_SAVINGS, bank));
    }

    @Test
    public void correct_deposit_string() {
        Accounts savingAccount = Accounts.checking(2);
        bank.addAccount("11122233", savingAccount);
        assertTrue(commandValidator.validate(DEPOSIT_CHECKING, bank));
    }

    @Test
    public void wrong_deposit_string() {
        Accounts savingAccount = Accounts.checking(2);
        bank.addAccount("11122233", savingAccount);
        assertFalse(commandValidator.validate(WRONG_DEPOSIT_CHECKING, bank));
    }

    @Test
    public void empty_string() {
        assertFalse(commandValidator.validate(EMPTY_STRING, bank));
    }

    @Test
    public void just_spaces() {
        assertFalse(commandValidator.validate(JUST_SPACES, bank));
    }

    @Test
    public void random_string() {
        assertFalse(commandValidator.validate("rhaowhraowriwhr", bank));
    }
}
