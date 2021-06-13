package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WithdrawValidatorTest {

    public final String QUICK_ID = "12345678";
    public final String QUICK_ID2 = "22233344";
    public final String QUICK_ID3 = "99999999";

    public final String GENERAL_EXTRA_SPACE_FRONT = " Withdraw 12345678 100";
    public final String GENERAL_EXTRA_SPACE_MIDDLE = "Withdraw  12345678 100";
    public final String GENERAL_EXTRA_SPACE_END = "Withdraw 12345678 100 ";
    public final String GENERAL_CASE_SENSITIVITY = "WiThDRaw 12345678 100";

    public final String GENERAL_MISSING_COMMAND = "12345678 100";
    public final String GENERAL_WRONG_COMMAND = "Withe 12345678 100";
    public final String GENERAL_NON_STRING_COMMAND = "68498494 12345678 100";

    public final String GENERAL_ACCOUNT_ID_MISSING = "Withdraw 200";
    public final String GENERAL_ACCOUNT_EXISTS = "Withdraw 12345678 100";
    public final String GENERAL_ACCOUNT_DOES_NOT_EXIST = "Withdraw 12345678 100";

    public final String GENERAL_9_DIGIT_ID = "Withdraw 123456789 200";
    public final String GENERAL_7_DIGIT_ID = "Withdraw 1234567 200";
    public final String GENERAL_ID_HAS_CHARACTERS = "Withdraw 1234%%%s 200";
    public final String GENERAL_ID_IS_DECIMAL = "Withdraw 1234567.8 200";
    public final String GENERAL_LOWEST_ID = "Withdraw 00000000 300";
    public final String GENERAL_HIGHEST_ID = "Withdraw 99999999 200";
    public final String GENERAL_NEGATIVE_ID = "Withdraw -12345678 100";

    public final String GENERAL_MISSING_INPUT_VALUE = "Withdraw 12345678";
    public final String GENERAL_EXTRA_INPUT = "Withdraw 12345678 200 200";

    public final String WITHDRAW_CHECKING_MINIMUM_VALUE = "Withdraw 12345678 0";
    public final String WITHDRAW_CHECKING_MAXIMUM_VALUE = "Withdraw 12345678 400";
    public final String WITHDRAW_CHECKING_NEGATIVE_VALUE = "Withdraw 12345678 -100";
    public final String WITHDRAW_CHECKING_ABOVE_MAX_VALUE = "Withdraw 12345678 402";

    public final String WITHDRAW_SAVINGS_MINIMUM_VALUE = "Withdraw 22233344 0";
    public final String WITHDRAW_SAVINGS_MAXIMUM_VALUE = "Withdraw 22233344 1000";
    public final String WITHDRAW_SAVINGS_NEGATIVE_VALUE = "Withdraw 22233344 -23";
    public final String WITHDRAW_SAVINGS_ABOVE_MAX_VALUE = "Withdraw 22233344 5160";
    public final String WITHDRAW_SAVINGS_TWICE_IN_A_MONTH = "Withdraw 22233344 12";
    public final String WITHDRAW_SAVINGS_RESETS_WITH_PASS = "Withdraw 22233344 10";

    public final String WITHDRAW_CD_DOESNT_WORK = "Withdraw 99999999 15";
    public final String WITHDRAW_CD_MAX_VALUE = "Withdraw 99999999 1200";
    public final String WITHDRAW_CD_ABOVE_MAX_VALUE = "Withdraw 99999999 9999";
    public final String WITHDRAW_CD_BELOW_MAX_VALUE_AFTER_12_MONTHS = "Withdraw 99999999 100";
    public final String WITHDRAW_CD_MAX_VALUE_AFTER_12_MONTHS = "Withdraw 99999999 1127.27";
    public final String WITHDRAW_CD_ABOVE_MAX_VALUE_AFTER_12_MONTHS = "Withdraw 99999999 9999";

    public final String EMPTY_STRING = "";
    public final String JUST_SPACES = "    ";

    WithdrawValidator withdrawValidator;
    Bank bank;
    PassProcessor passProcessor;
    Accounts checkingAccount;
    Accounts savingsAccount;
    Accounts cdAccount;

    @BeforeEach
    void setup() {
        withdrawValidator = new WithdrawValidator();
        bank = new Bank();
        passProcessor = new PassProcessor(bank);
        checkingAccount = Accounts.checking(1);
        savingsAccount = Accounts.savings(2);
        cdAccount = Accounts.cd(3, 1000);
        bank.addAccount(QUICK_ID, checkingAccount);
        bank.addAccount(QUICK_ID2, savingsAccount);
        bank.addAccount(QUICK_ID3, cdAccount);
    }

    @Test
    public void general_extra_space_front() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_EXTRA_SPACE_FRONT, bank));
    }

    @Test
    public void general_extra_space_middle() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_EXTRA_SPACE_MIDDLE, bank));
    }

    @Test
    public void general_extra_space_end() {
        assertTrue(withdrawValidator.withdrawValidate(GENERAL_EXTRA_SPACE_END, bank));
    }

    @Test
    public void general_case_sensitivity() {
        assertTrue(withdrawValidator.withdrawValidate(GENERAL_CASE_SENSITIVITY, bank));
    }

    @Test
    public void general_missing_command() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_MISSING_COMMAND, bank));
    }

    @Test
    public void general_wrong_command() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_WRONG_COMMAND, bank));
    }

    @Test
    public void general_non_string_command() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_NON_STRING_COMMAND, bank));
    }

    @Test
    public void general_account_id_missing() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_ACCOUNT_ID_MISSING, bank));
    }

    @Test
    public void general_account_exists() {
        assertTrue(withdrawValidator.withdrawValidate(GENERAL_ACCOUNT_EXISTS, bank));
    }

    @Test
    public void general_account_does_not_exist() {
        bank.getAccounts().remove("12345678");
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_ACCOUNT_DOES_NOT_EXIST, bank));
    }

    @Test
    public void general_9_digit_id() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_9_DIGIT_ID, bank));
    }

    @Test
    public void general_7_digit_id() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_7_DIGIT_ID, bank));
    }

    @Test
    public void general_id_has_character() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_ID_HAS_CHARACTERS, bank));
    }

    @Test
    public void general_id_is_decimal() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_ID_IS_DECIMAL, bank));
    }

    @Test
    public void general_lowest_id() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount("00000000", checkingAccount);
        assertTrue(withdrawValidator.withdrawValidate(GENERAL_LOWEST_ID, bank));
    }

    @Test
    public void general_highest_id() {
        bank.getAccounts().remove(QUICK_ID);
        bank.addAccount("99999999", checkingAccount);
        assertTrue(withdrawValidator.withdrawValidate(GENERAL_HIGHEST_ID, bank));
    }

    @Test
    public void general_negative_id() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_NEGATIVE_ID, bank));
    }

    @Test
    public void general_missing_value() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_MISSING_INPUT_VALUE, bank));
    }

    @Test
    public void general_extra_value() {
        assertFalse(withdrawValidator.withdrawValidate(GENERAL_EXTRA_INPUT, bank));
    }

    //

    @Test
    public void withdraw_checking_minimum() {
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_CHECKING_MINIMUM_VALUE, bank));
    }

    @Test
    public void withdraw_checking_maximum() {
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_CHECKING_MAXIMUM_VALUE, bank));
    }

    @Test
    public void withdraw_checking_negative_value() {
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_CHECKING_NEGATIVE_VALUE, bank));
    }

    @Test
    public void withdraw_checking_above_max_value() {
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_CHECKING_ABOVE_MAX_VALUE, bank));
    }

    @Test
    public void withdraw_savings_minimum() {
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_MINIMUM_VALUE, bank));
    }

    @Test
    public void withdraw_savings_maximum() {
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_MAXIMUM_VALUE, bank));
    }

    @Test
    public void withdraw_savings_negative_value() {
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_NEGATIVE_VALUE, bank));
    }

    @Test
    public void withdraw_savings_above_max_value() {
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_ABOVE_MAX_VALUE, bank));
    }

    @Test
    public void withdraw_savings_twice_a_month() {
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_TWICE_IN_A_MONTH, bank));
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_TWICE_IN_A_MONTH, bank));
    }

    @Test
    public void withdraw_savings_pass_resets_monthly() {
        bank.getAccounts().get(QUICK_ID2).deposit(300);
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_RESETS_WITH_PASS, bank));
        passProcessor.processPass(1);
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_SAVINGS_RESETS_WITH_PASS, bank));
    }

    @Test
    public void withdraw_cd_doesnt_work() {
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_CD_DOESNT_WORK, bank));
    }

    @Test
    public void withdraw_cd_max_value() {
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_CD_MAX_VALUE, bank));
    }

    @Test
    public void withdraw_cd_above_max_value() {
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_CD_ABOVE_MAX_VALUE, bank));
    }

    @Test
    public void withdraw_cd_below_max_value_after_12_months() {
        passProcessor.processPass(12);
        assertFalse(withdrawValidator.withdrawValidate(WITHDRAW_CD_BELOW_MAX_VALUE_AFTER_12_MONTHS, bank));
    }

    @Test
    public void withdraw_cd_max_value_after_12_months() {
        passProcessor.processPass(12);
        assertEquals(bank.getAccounts().get(QUICK_ID3).balance, 1127.27);
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_CD_MAX_VALUE_AFTER_12_MONTHS, bank));
    }

    @Test
    public void withdraw_cd_above_max_value_after_12_months() {
        passProcessor.processPass(12);
        assertTrue(withdrawValidator.withdrawValidate(WITHDRAW_CD_ABOVE_MAX_VALUE_AFTER_12_MONTHS, bank));
    }

    @Test
    public void empty_string() {
        assertFalse(withdrawValidator.withdrawValidate(EMPTY_STRING, bank));
    }

    @Test
    public void just_spaces() {
        assertFalse(withdrawValidator.withdrawValidate(JUST_SPACES, bank));
    }


}
