package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassValidatorTest {

    public final String GENERAL_CORRECT_PASS_COMMAND = "Pass 1";
    public final String GENERAL_SPACES_IN_FRONT = "  Pass 1";
    public final String GENERAL_SPACES_IN_MIDDLE = "Pass  1";
    public final String GENERAL_SPACES_IN_END = "Pass 1  ";

    public final String GENERAL_COMMAND_CASE_INSENSITIVITY = "PaSS 1";
    public final String GENERAL_COMMAND_SPELLED_WRONG = "Piss 1";
    public final String GENERAL_COMMAND_IS_WRONG_TYPE = "1 1";
    public final String GENERAL_COMMAND_IS_MISSING = "1";
    public final String GENERAL_COMMAND_IS_QUOTES = "'' 1";

    public final String GENERAL_MONTHS_IS_STRING = "Pass two";
    public final String GENERAL_MONTH_IS_MISSING = "Pass";
    public final String GENERAL_MONTH_IS_ZERO = "Pass 0";
    public final String GENERAL_MONTH_IS_THREE_DIGITS = "Pass 111";

    public final String MONTH_IS_NEGATIVE = "Pass -2";
    public final String MONTH_IS_LOWEST_BOUND = "Pass 1";
    public final String MONTH_IS_HIGHEST_BOUND = "Pass 60";
    public final String MONTH_IS_HIGHER_THAN_BOUND = "Pass 61";

    public final String EMPTY_STRING = "";
    public final String JUST_SPACES = " ";


    Bank bank;
    PassValidator passValidator;

    @BeforeEach
    void setup() {
        bank = new Bank();
        passValidator = new PassValidator();
    }

    @Test
    public void correct_pass_command() {
        assertTrue(passValidator.passValidate(GENERAL_CORRECT_PASS_COMMAND));
    }

    @Test
    public void spaces_in_front() {
        assertFalse(passValidator.passValidate(GENERAL_SPACES_IN_FRONT));
    }

    @Test
    public void spaces_in_middle() {
        assertFalse(passValidator.passValidate(GENERAL_SPACES_IN_MIDDLE));
    }

    @Test
    public void spaces_in_end() {
        assertTrue(passValidator.passValidate(GENERAL_SPACES_IN_END));
    }

    //

    @Test
    public void command_case_insensitive() {
        assertTrue(passValidator.passValidate(GENERAL_COMMAND_CASE_INSENSITIVITY));
    }

    @Test
    public void command_spelled_wrong() {
        assertFalse(passValidator.passValidate(GENERAL_COMMAND_SPELLED_WRONG));
    }

    @Test
    public void command_is_wrong_type() {
        assertFalse(passValidator.passValidate(GENERAL_COMMAND_IS_WRONG_TYPE));
    }

    @Test
    public void command_is_missing() {
        assertFalse(passValidator.passValidate(GENERAL_COMMAND_IS_MISSING));
    }

    @Test

    public void command_is_quotes() {
        assertFalse(passValidator.passValidate(GENERAL_COMMAND_IS_QUOTES));
    }

    //

    @Test
    public void months_is_string() {
        assertFalse(passValidator.passValidate(GENERAL_MONTHS_IS_STRING));
    }

    @Test
    public void months_is_missing() {
        assertFalse(passValidator.passValidate(GENERAL_MONTH_IS_MISSING));
    }

    @Test
    public void months_is_zero() {
        assertFalse(passValidator.passValidate(GENERAL_MONTH_IS_ZERO));
    }

    @Test
    public void months_is_three_digits() {
        assertFalse(passValidator.passValidate(GENERAL_MONTH_IS_THREE_DIGITS));
    }

    //

    @Test
    public void months_is_negative() {
        assertFalse(passValidator.passValidate(MONTH_IS_NEGATIVE));
    }

    @Test
    public void months_is_lowest_bound_one() {
        assertTrue(passValidator.passValidate(MONTH_IS_LOWEST_BOUND));
    }

    @Test
    public void months_is_highest_bound_sixty() {
        assertTrue(passValidator.passValidate(MONTH_IS_HIGHEST_BOUND));
    }

    @Test
    public void months_is_above_higher_bound() {
        assertFalse(passValidator.passValidate(MONTH_IS_HIGHER_THAN_BOUND));
    }

    //

    @Test
    public void empty_string() {
        assertFalse(passValidator.passValidate(EMPTY_STRING));
    }

    @Test
    public void just_space() {
        assertFalse(passValidator.passValidate(JUST_SPACES));
    }
}
