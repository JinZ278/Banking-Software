package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    public final String CREATE_SAVINGS = "Create Savings 12345678 1.2";
    public final String WRONG_CREATE_SAVINGS = "Create Savings 22 2.2";

    public final String DEPOSIT_CHECKING = "Deposit 11122233 250.3";
    public final String WRONG_DEPOSIT_CHECKING = "Deposit 11122233 25022.3";

    public final String PASS_COMMAND = "Pass 12";
    public final String WRONG_PASS_COMMAND = "pass infinity";

    public final String EMPTY_STRING = "";
    public final String JUST_SPACES = "    ";

    CommandValidator commandValidator;
    Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    public void correct_create_string() {
        assertTrue(commandValidator.validate(CREATE_SAVINGS));
    }


    @Test
    public void wrong_create_string() {
        assertFalse(commandValidator.validate(WRONG_CREATE_SAVINGS));
    }

    @Test
    public void correct_deposit_string() {
        Accounts savingAccount = Accounts.checking(2);
        bank.addAccount("11122233", savingAccount);
        assertTrue(commandValidator.validate(DEPOSIT_CHECKING));
    }

    @Test
    public void wrong_deposit_string() {
        Accounts savingAccount = Accounts.checking(2);
        bank.addAccount("11122233", savingAccount);
        assertFalse(commandValidator.validate(WRONG_DEPOSIT_CHECKING));
    }

    @Test
    public void correct_pass_string() {
        assertTrue(commandValidator.validate(PASS_COMMAND));
    }

    @Test
    public void wrong_pass_string() {
        assertFalse(commandValidator.validate(WRONG_PASS_COMMAND));
    }

    @Test
    public void empty_string() {
        assertFalse(commandValidator.validate(EMPTY_STRING));
    }

    @Test
    public void just_spaces() {
        assertFalse(commandValidator.validate(JUST_SPACES));
    }

    @Test
    public void random_string() {
        assertFalse(commandValidator.validate("rhaowhraowriwhr"));
    }
}
