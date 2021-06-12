package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidatorTest {

    public final String GENERAL_QUOTES_INPUT = "Create '' 12345678 1.6";
    public final String GENERAL_EXTRA_SPACE_FRONT = "  Create Checking 12345678 1.5";
    public final String GENERAL_EXTRA_SPACE_MIDDLE = "Create Checking  12345678 1.5";
    public final String GENERAL_EXTRA_SPACE_END = "Create Checking 12345678 1.5  ";
    public final String GENERAL_CASE_SENSITIVITY = "CReATe ChECKing 12345678 2.3";

    public final String GENERAL_MISSING_COMMAND = "Checking 12345678 1.5";
    public final String GENERAL_WRONG_COMMAND = "Cremate checking 12345678 1.9";
    public final String GENERAL_NON_STRING_COMMAND = "23124 chicken 12345678 1.3";

    public final String GENERAL_EXISTING_ID = "Create checking 12345678 3.2";
    public final String GENERAL_9_DIGIT_ID = "Create checking 123456789 2.2";
    public final String GENERAL_7_DIGIT_ID = "Create checking 1234567 3.2";
    public final String GENERAL_ID_HAS_CHARACTERS = "Create checking 123^5t78 1.1";
    public final String GENERAL_ID_IS_DECIMAL = "Create Checking 1234567.9 1.5";
    public final String GENERAL_LOWEST_ID = "Create Checking 00000000 1.5";
    public final String GENERAL_HIGHEST_ID = "Create Checking 99999999 1.8";
    public final String GENERAL_NEGATIVE_ID = "Create Checking -12345678 1.2";
    public final String GENERAL_STRING_AS_APR = "Create Checking 00002300 hi";

    public final String QUICK_ID = "12345678";

    public final String CREATE_COMMAND_MISSING_ACCOUNT = "Create 12345678 1.2";
    public final String CREATE_COMMAND_WRONG_ACCOUNT = "Create Chicken 12345678 1.5";
    public final String CREATE_COMMAND_NON_STRING_ACCOUNT = "Create 123123 12345678 1.3";

    public final String CREATE_CHECKING_COMMAND = "Create Checking 12345678 1.5";
    public final String CREATE_CHECKING_COMMAND_MISSING_ID = "Create Checking 1.0";

    public final String CREATE_CHECKING_COMMAND_MISSING_APR = "Create Checking 12345678";
    public final String CREATE_CHECKING_COMMAND_NEGATIVE_APR = "Create Checking 12345678 -1.5";
    public final String CREATE_CHECKING_COMMAND_MINIMUM_APR = "Create Checking 12345678 0";
    public final String CREATE_CHECKING_COMMAND_MAXIMUM_APR = "Create Checking 12345678 10";
    public final String CREATE_CHECKING_COMMAND_ABOVE_MAXIMUM_APR = "Create Checking 12345678 101";

    public final String CREATE_SAVINGS_COMMAND = "Create Savings 12345678 1.2";
    public final String CREATE_SAVINGS_COMMAND_MISSING_ID = "Create Savings 1.2";

    public final String CREATE_SAVINGS_COMMAND_MISSING_INPUT = "Create Savings 12345678";
    public final String CREATE_SAVINGS_COMMAND_EXTRA_INPUT = "Create Savings 12345678 1.3 1000";

    public final String CREATE_CD_COMMAND_ZERO_BALANCE = "Create Cd 12345678 1.4 0";
    public final String CREATE_CD_COMMAND_MIN_BALANCE = "Create Cd 12345678 1.4 1000";
    public final String CREATE_CD_COMMAND_MAX_BALANCE = "Create Cd 12345678 1.2 10000";
    public final String CREATE_CD_COMMAND_OVER_MAX_BALANCE = "Create Cd 12345678 1.2 100001";
    public final String CREATE_CD_COMMAND_DECIMAL_BALANCE = "Create Cd 12345678 1.2 1000.01";

    public final String CREATE_CD_COMMAND_EXTRA_INPUT = "Create cd 12345678 1.3 10000 10000";
    public final String CREATE_CD_COMMAND_MISSING_INPUT = "Create cd 12345678 1.3";

    public final String EMPTY_STRING = "";
    public final String JUST_SPACES = " ";

    CreateValidator createValidator;
    Bank bank;
    Accounts account;
    Accounts account2;

    @BeforeEach
    void setup() {
        createValidator = new CreateValidator();
        bank = new Bank();
        account = Accounts.checking(1);
        account2 = Accounts.savings(2);
    }

    @Test
    public void general_quotes_as_input() {
        assertFalse(createValidator.createValidate(GENERAL_QUOTES_INPUT, bank));
    }

    @Test
    public void general_extra_space_front() {
        assertFalse(createValidator.createValidate(GENERAL_EXTRA_SPACE_FRONT, bank));
    }

    @Test
    public void general_extra_space_middle() {
        assertFalse(createValidator.createValidate(GENERAL_EXTRA_SPACE_MIDDLE, bank));
    }

    @Test
    public void general_extra_space_end() {
        assertTrue(createValidator.createValidate(GENERAL_EXTRA_SPACE_END, bank));
    }

    @Test
    public void general_insensitive_case() {
        assertTrue(createValidator.createValidate(GENERAL_CASE_SENSITIVITY, bank));
    }

    @Test
    public void general_missing_command() {
        assertFalse(createValidator.createValidate(GENERAL_MISSING_COMMAND, bank));
    }

    @Test
    public void general_wrong_command() {
        assertFalse(createValidator.createValidate(GENERAL_WRONG_COMMAND, bank));
    }

    @Test
    public void general_non_string_command() {
        assertFalse(createValidator.createValidate(GENERAL_NON_STRING_COMMAND, bank));
    }

    @Test
    public void general_id_already_exists() {
        bank.addAccount(QUICK_ID, account);
        assertFalse(createValidator.createValidate(GENERAL_EXISTING_ID, bank));
    }

    @Test
    public void general_9_digit_id() {
        assertFalse(createValidator.createValidate(GENERAL_9_DIGIT_ID, bank));
    }

    @Test
    public void general_7_digit_id() {
        assertFalse(createValidator.createValidate(GENERAL_7_DIGIT_ID, bank));
    }

    @Test
    public void general_id_has_characters() {
        assertFalse(createValidator.createValidate(GENERAL_ID_HAS_CHARACTERS, bank));
    }

    @Test
    public void general_decimal_id() {
        assertFalse(createValidator.createValidate(GENERAL_ID_IS_DECIMAL, bank));
    }

    @Test
    public void general_lowest_id() {
        assertTrue(createValidator.createValidate(GENERAL_LOWEST_ID, bank));
    }

    @Test
    public void general_highest_id() {
        assertTrue(createValidator.createValidate(GENERAL_HIGHEST_ID, bank));
    }

    @Test
    public void general_negative_id() {
        assertFalse(createValidator.createValidate(GENERAL_NEGATIVE_ID, bank));
    }

    @Test
    public void general_string_as_apr() {
        assertFalse(createValidator.createValidate(GENERAL_STRING_AS_APR, bank));
    }

    /**********************************************Seperator***********************************************************/

    @Test
    public void create_checking_missing_account() {
        assertFalse(createValidator.createValidate(CREATE_COMMAND_MISSING_ACCOUNT, bank));
    }

    @Test
    public void create_wrong_account() {
        assertFalse(createValidator.createValidate(CREATE_COMMAND_WRONG_ACCOUNT, bank));
    }

    @Test
    public void create_non_string_account() {
        assertFalse(createValidator.createValidate(CREATE_COMMAND_NON_STRING_ACCOUNT, bank));
    }

    @Test
    public void create_correct_checking_string() {
        assertTrue(createValidator.createValidate(CREATE_CHECKING_COMMAND, bank));
    }

    @Test
    public void create_checking_missing_id() {
        assertFalse(createValidator.createValidate(CREATE_CHECKING_COMMAND_MISSING_ID, bank));
    }

    @Test
    public void create_checking_missing_apr() {
        assertFalse(createValidator.createValidate(CREATE_CHECKING_COMMAND_MISSING_APR, bank));
    }

    @Test
    public void create_checking_negative_apr() {
        assertFalse(createValidator.createValidate(CREATE_CHECKING_COMMAND_NEGATIVE_APR, bank));
    }

    @Test
    public void create_checking_minimum_apr() {
        assertTrue(createValidator.createValidate(CREATE_CHECKING_COMMAND_MINIMUM_APR, bank));
    }

    @Test
    public void create_checking_maximum_apr() {
        assertTrue(createValidator.createValidate(CREATE_CHECKING_COMMAND_MAXIMUM_APR, bank));
    }

    @Test
    public void create_checking_above_maximum_apr() {
        assertFalse(createValidator.createValidate(CREATE_CHECKING_COMMAND_ABOVE_MAXIMUM_APR, bank));
    }

    @Test
    public void create_correct_savings_string() {
        assertTrue(createValidator.createValidate(CREATE_SAVINGS_COMMAND, bank));
    }

    @Test
    public void create_savings_missing_id() {
        assertFalse(createValidator.createValidate(CREATE_SAVINGS_COMMAND_MISSING_ID, bank));
    }

    @Test
    public void create_savings_missing_input() {
        assertFalse(createValidator.createValidate(CREATE_SAVINGS_COMMAND_MISSING_INPUT, bank));
    }

    @Test
    public void create_savings_extra_input() {
        assertFalse(createValidator.createValidate(CREATE_SAVINGS_COMMAND_EXTRA_INPUT, bank));
    }

    @Test
    public void create_cd_empty_balance() {
        assertFalse(createValidator.createValidate(CREATE_CD_COMMAND_ZERO_BALANCE, bank));
    }

    @Test
    public void create_cd_min_balance() {
        assertTrue(createValidator.createValidate(CREATE_CD_COMMAND_MIN_BALANCE, bank));
    }

    @Test
    public void create_cd_max_balance() {
        assertTrue(createValidator.createValidate(CREATE_CD_COMMAND_MAX_BALANCE, bank));
    }

    @Test
    public void create_cd_over_max_balance() {
        assertFalse(createValidator.createValidate(CREATE_CD_COMMAND_OVER_MAX_BALANCE, bank));
    }

    @Test
    public void create_cd_decimal_balance() {
        assertTrue(createValidator.createValidate(CREATE_CD_COMMAND_DECIMAL_BALANCE, bank));
    }

    @Test
    public void create_cd_extra_input() {
        assertFalse(createValidator.createValidate(CREATE_CD_COMMAND_EXTRA_INPUT, bank));
    }

    @Test
    public void create_cd_missing_input() {
        assertFalse(createValidator.createValidate(CREATE_CD_COMMAND_MISSING_INPUT, bank));
    }

    @Test
    public void empty_string() {
        assertFalse(createValidator.createValidate(EMPTY_STRING, bank));
    }

    @Test
    public void just_space() {
        assertFalse(createValidator.createValidate(JUST_SPACES, bank));
    }

}
