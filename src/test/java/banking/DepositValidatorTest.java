package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {

    public final String GENERAL_CORRECT_STRING = "Deposit 12345678 200";
    public final String GENERAL_EXTRA_SPACE_FRONT = " Deposit 12345678 100";
    public final String GENERAL_EXTRA_SPACE_MIDDLE = "Deposit  12345678 100";
    public final String GENERAL_EXTRA_SPACE_END = "Deposit 12345678 100 ";
    public final String GENERAL_CASE_SENSITIVITY = "DePoSiT 12345678 100";

    public final String GENERAL_MISSING_COMMAND = "12345678 1000";
    public final String GENERAL_WRONG_COMMAND = "Depo 12345678 100";
    public final String GENERAL_NON_STRING_COMMAND = "68498494 12345678 100";

    public final String GENERAL_ACCOUNT_ID_MISSING = "Deposit 2000";
    public final String GENERAL_ACCOUNT_EXISTS = "Deposit 12345678 1000";
    public final String GENERAL_ACCOUNT_DOES_NOT_EXIST = "Deposit 12345678 1000";

    public final String GENERAL_9_DIGIT_ID = "Deposit 123456789 2000";
    public final String GENERAL_7_DIGIT_ID = "Deposit 1234567 2000";
    public final String GENERAL_ID_HAS_CHARACTERS = "Deposit 1234%%%s 2000";
    public final String GENERAL_ID_IS_DECIMAL = "Deposit 1234567.8 2000";
    public final String GENERAL_LOWEST_ID = "Deposit 00000000 500";
    public final String GENERAL_HIGHEST_ID = "Deposit 99999999 700";
    public final String GENERAL_NEGATIVE_ID = "Deposit -12345678 2000";

    public final String GENERAL_MISSING_INPUT_VALUE = "Deposit 12345678";
    public final String GENERAL_EXTRA_INPUT = "Deposit 12345678 2000 2000";

    public final String DEPOSIT_CHECKING_MINIMUM = "Deposit 12345678 0";
    public final String DEPOSIT_CHECKING_MAXIMUM = "Deposit 12345678 1000";
    public final String DEPOSIT_CHECKING_NEGATIVE_VALUE = "Deposit 12345678 -500";
    public final String DEPOSIT_CHECKING_ABOVE_MAXIMUM = "Deposit 12345678 1200";

    public final String DEPOSIT_SAVINGS_MINIMUM = "Deposit 12345678 0";
    public final String DEPOSIT_SAVINGS_MAXIMUM = "Deposit 12345678 2500";
    public final String DEPOSIT_SAVINGS_NEGATIVE_VALUE = "Deposit 12345678 -500";
    public final String DEPOSIT_SAVINGS_ABOVE_MAXIMUM = "Deposit 12345678 3000";

    public final String DEPOSIT_CD_ANYTHING = "Deposit 12345678 1";
    public final String DEPOSIT_VALID_COMMAND = "deposit 12345678 200";
    public final String DEPOSIT_DECIMAL_VALUE = "depoSit 12345678 69.69";

    public final String QUICK_ID = "12345678";

    public final String EMPTY_STRING = "";
    public final String JUST_SPACES = "    ";

    DepositValidator depositValidator;
    Bank bank;
    Accounts checkingAccount;
    Accounts savingsAccount;
    Accounts cdAccount;

    @BeforeEach
    void setup() {
        depositValidator = new DepositValidator();
        bank = new Bank();
        checkingAccount = Accounts.checking(1);
        savingsAccount = Accounts.savings(2);
        cdAccount = Accounts.cd(3, 1200);
        bank.addAccount(QUICK_ID, checkingAccount);
    }

    @Test
    public void general_correct_string() {
        assertTrue(depositValidator.depositValidate(GENERAL_CORRECT_STRING, bank));
    }

    @Test
    public void general_extra_space_front() {
        assertFalse(depositValidator.depositValidate(GENERAL_EXTRA_SPACE_FRONT, bank));
    }

    @Test
    public void general_extra_space_middle() {
        assertFalse(depositValidator.depositValidate(GENERAL_EXTRA_SPACE_MIDDLE, bank));
    }

    @Test
    public void general_extra_space_end() {
        assertTrue(depositValidator.depositValidate(GENERAL_EXTRA_SPACE_END, bank));
    }

    @Test
    public void general_case_sensitivity() {
        assertTrue(depositValidator.depositValidate(GENERAL_CASE_SENSITIVITY, bank));
    }

    @Test
    public void general_missing_command() {
        assertFalse(depositValidator.depositValidate(GENERAL_MISSING_COMMAND, bank));
    }

    @Test
    public void general_wrong_command() {
        assertFalse(depositValidator.depositValidate(GENERAL_WRONG_COMMAND, bank));
    }

    @Test
    public void general_non_string_command() {
        assertFalse(depositValidator.depositValidate(GENERAL_NON_STRING_COMMAND, bank));
    }

    @Test
    public void general_account_id_missing() {
        assertFalse(depositValidator.depositValidate(GENERAL_ACCOUNT_ID_MISSING, bank));
    }

    @Test
    public void general_account_does_not_exist_in_bank() {
        bank.getAccounts().remove(QUICK_ID);
        assertFalse(depositValidator.depositValidate(GENERAL_ACCOUNT_DOES_NOT_EXIST, bank));
    }

    @Test
    public void general_account_exist_in_bank() {
        assertTrue(depositValidator.depositValidate(GENERAL_ACCOUNT_EXISTS, bank));
    }

    @Test
    public void general_9_digit_id() {
        assertFalse(depositValidator.depositValidate(GENERAL_9_DIGIT_ID, bank));
    }

    @Test
    public void general_7_digit_id() {
        assertFalse(depositValidator.depositValidate(GENERAL_7_DIGIT_ID, bank));
    }

    @Test
    public void general_id_has_character() {
        assertFalse(depositValidator.depositValidate(GENERAL_ID_HAS_CHARACTERS, bank));
    }

    @Test
    public void general_id_is_decimal() {
        assertFalse(depositValidator.depositValidate(GENERAL_ID_IS_DECIMAL, bank));
    }

    @Test
    public void general_lowest_id() {
        bank.addAccount("00000000", checkingAccount);
        assertTrue(depositValidator.depositValidate(GENERAL_LOWEST_ID, bank));
    }

    @Test
    public void general_highest_id() {
        bank.addAccount("99999999", checkingAccount);
        assertTrue(depositValidator.depositValidate(GENERAL_HIGHEST_ID, bank));
    }

    @Test
    public void general_negative_id() {
        assertFalse(depositValidator.depositValidate(GENERAL_NEGATIVE_ID, bank));
    }

    @Test
    public void general_missing_input() {
        assertFalse(depositValidator.depositValidate(GENERAL_MISSING_INPUT_VALUE, bank));
    }

    @Test
    public void general_extra_input() {
        assertFalse(depositValidator.depositValidate(GENERAL_EXTRA_INPUT, bank));
    }

    @Test
    public void deposit_checking_minimum() {
        assertTrue(depositValidator.depositValidate(DEPOSIT_CHECKING_MINIMUM, bank));
    }

    @Test
    public void deposit_checking_maximum() {
        assertTrue(depositValidator.depositValidate(DEPOSIT_CHECKING_MAXIMUM, bank));
    }

    @Test
    public void deposit_checking_negative_value() {
        assertFalse(depositValidator.depositValidate(DEPOSIT_CHECKING_NEGATIVE_VALUE, bank));
    }

    @Test
    public void deposit_checking_above_maximum_value() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount(QUICK_ID, checkingAccount);
        assertFalse(depositValidator.depositValidate(DEPOSIT_CHECKING_ABOVE_MAXIMUM, bank));
    }

    @Test
    public void deposit_savings_minimum() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount(QUICK_ID, savingsAccount);
        assertTrue(depositValidator.depositValidate(DEPOSIT_SAVINGS_MINIMUM, bank));
    }

    @Test
    public void deposit_savings_maximum() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount(QUICK_ID, savingsAccount);
        assertTrue(depositValidator.depositValidate(DEPOSIT_SAVINGS_MAXIMUM, bank));
    }

    @Test
    public void deposit_savings_negative_value() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount(QUICK_ID, savingsAccount);
        assertFalse(depositValidator.depositValidate(DEPOSIT_SAVINGS_NEGATIVE_VALUE, bank));
    }

    @Test
    public void deposit_savings_above_maximum_value() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount(QUICK_ID, savingsAccount);
        assertFalse(depositValidator.depositValidate(DEPOSIT_SAVINGS_ABOVE_MAXIMUM, bank));
    }

    @Test
    public void deposit_cd_anything() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount(QUICK_ID, cdAccount);
        assertFalse(depositValidator.depositValidate(DEPOSIT_CD_ANYTHING, bank));
    }

    @Test
    public void deposit_valid_command() {
        assertTrue(depositValidator.depositValidate(DEPOSIT_VALID_COMMAND, bank));
    }

    @Test
    public void deposit_decimal_value() {
        assertTrue(depositValidator.depositValidate(DEPOSIT_DECIMAL_VALUE, bank));
    }

    @Test
    public void empty_string() {
        assertFalse(depositValidator.depositValidate(EMPTY_STRING, bank));
    }

    @Test
    public void just_spaces() {
        assertFalse(depositValidator.depositValidate(JUST_SPACES, bank));
    }
}
