package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidatorTest {

    public final String QUICK_ID = "12345678";
    public final String QUICK_ID2 = "87654321";
    public final String QUICK_ID3 = "23456789";

    public final String GENERAL_CORRECT_TRANSFER_COMMAND = "Transfer 12345678 87654321 200";
    public final String GENERAL_EXTRA_SPACE_FRONT = " Transfer 12345678 87654321 200";
    public final String GENERAL_EXTRA_SPACE_MIDDLE = "Transfer  12345678 87654321 200";
    public final String GENERAL_EXTRA_SPACE_END = "Transfer 12345678 87654321 200 ";
    public final String GENERAL_CASE_SENSITIVITY = "TraNSfer 12345678 87654321 200";

    public final String GENERAL_MISSING_COMMAND = "12345678 87654321 150";
    public final String GENERAL_WRONG_COMMAND = "Trans 12345678 87654321 300";
    public final String GENERAL_NON_STRING_COMMAND = "2828282 68498494 12345678 100";

    public final String GENERAL_ACCOUNT_ID_MISSING = "Transfer 12345678 200";
    public final String GENERAL_ACCOUNT_BOTH_ID_MISSING = "Transfer 200";
    public final String GENERAL_ACCOUNT_EXISTS = "Transfer 12345678 87654321 100";
    public final String GENERAL_ACCOUNT_DOES_NOT_EXIST = "Transfer 12345678 88888889 100";
    public final String GENERAL_ACCOUNT_BOTH_DOES_NOT_EXIST = "Transfer 88888889 88888879 200";

    public final String GENERAL_9_DIGIT_ID = "Transfer 123456789 87654321 200";
    public final String GENERAL_7_DIGIT_ID = "Transfer 1234567 87654321 2000";
    public final String GENERAL_ID_HAS_CHARACTERS = "Transfer 1234%%%s 87654321 200";
    public final String GENERAL_ID_IS_DECIMAL = "Transfer 1234567.8 87654321 200";
    public final String GENERAL_LOWEST_ID = "Transfer 00000000 87654321 300";
    public final String GENERAL_HIGHEST_ID = "Transfer 99999999 87654321 200";
    public final String GENERAL_NEGATIVE_ID = "Transfer -12345678 87654321 250";

    public final String GENERAL_9_DIGIT_SECOND_ID = "Transfer 123456789 87654321 200";
    public final String GENERAL_7_DIGIT_SECOND_ID = "Transfer 1234567 87654321 2000";
    public final String GENERAL_SECOND_ID_HAS_CHARACTERS = "Transfer 1234%%%s 87654321 200";
    public final String GENERAL_SECOND_ID_IS_DECIMAL = "Transfer 1234567.8 87654321 200";
    public final String GENERAL_LOWEST_SECOND_ID = "Transfer 00000000 87654321 300";
    public final String GENERAL_HIGHEST_SECOND_ID = "Transfer 99999999 87654321 200";
    public final String GENERAL_NEGATIVE_SECOND_ID = "Transfer -12345678 87654321 250";

    public final String CHECKING_WITHDRAW_VALUE_NEGATIVE = "Transfer 12345678 87654321 -300";
    public final String CHECKING_WITHDRAW_VALUE_ZERO = "Transfer 12345678 87654321 0";
    public final String CHECKING_WITHDRAW_VALUE_HIGHEST = "Transfer 12345678 87654321 400";
    public final String CHECKING_WITHDRAW_VALUE_HIGHER_THAN_MAX = "Transfer 12345678 87654321 401";

    public final String SAVINGS_WITHDRAW_VALUE_NEGATIVE = "Transfer 87654321 12345678 -300";
    public final String SAVINGS_WITHDRAW_VALUE_ZERO = "Transfer 87654321 12345678 0";
    public final String SAVINGS_WITHDRAW_VALUE_HIGHEST = "Transfer 87654321 12345678 1000";
    public final String SAVINGS_WITHDRAW_VALUE_HIGHER_THAN_MAX = "Transfer 87654321 12345678 1001";

    public final String CD_ARBITRARY_WITHDRAW_1 = "Transfer 23456789 12345678 0";
    public final String CD_ARBITRARY_WITHDRAW_2 = "Transfer 23456789 12345678 10";
    public final String CD_ARBITRARY_WITHDRAW_3 = "Transfer 23456789 12345678 -10";

    public final String CHECKING_DEPOSIT_VALUE_NEGATIVE = "Transfer 87654321 12345678 -300";
    public final String CHECKING_DEPOSIT_VALUE_ZERO = "Transfer 87654321 12345678 0";
    public final String CHECKING_DEPOSIT_VALUE_MAX = "Transfer 87654321 12345678 1000";
    public final String CHECKING_DEPOSIT_VALUE_HIGHER_THAN_MAX = "Transfer 87654321 12345678 1001";

    public final String SAVINGS_DEPOSIT_VALUE_NEGATIVE = "Transfer 9765432 87654321 -300";
    public final String SAVINGS_DEPOSIT_VALUE_ZERO = "Transfer 97654322 87654321 0";
    public final String SAVINGS_DEPOSIT_VALUE_MAX = "Transfer 97654322 87654321 1000";
    public final String SAVINGS_DEPOSIT_VALUE_HIGHER_THAN_MAX = "Transfer 97654322 87654321 1001";

    public final String CD_ARBITRARY_DEPOSIT_1 = "Transfer 12345678 23456789 0";
    public final String CD_ARBITRARY_DEPOSIT_2 = "Transfer 12345678 23456789 10";
    public final String CD_ARBITRARY_DEPOSIT_3 = "Transfer 12345678 23456789 -10";

    public final String EMPTY_STRING = "";
    public final String JUST_SPACES = "    ";


    Bank bank;
    TransferValidator transferValidator;
    Accounts checkingAccount;
    Accounts savingsAccount;
    Accounts cdAccount;

    @BeforeEach
    void setup() {
        bank = new Bank();
        transferValidator = new TransferValidator();
        checkingAccount = Accounts.checking(2);
        savingsAccount = Accounts.savings(2);
        cdAccount = Accounts.cd(3, 1000);
        bank.addAccount(QUICK_ID, checkingAccount);
        bank.addAccount(QUICK_ID2, savingsAccount);
        bank.addAccount(QUICK_ID3, cdAccount);
    }

    @Test
    public void general_correct_transfer_command() {
        assertTrue(transferValidator.transferValidate(GENERAL_CORRECT_TRANSFER_COMMAND, bank));
    }

    @Test
    public void general_extra_space_front() {
        assertFalse(transferValidator.transferValidate(GENERAL_EXTRA_SPACE_FRONT, bank));
    }

    @Test
    public void general_extra_space_middle() {
        assertFalse(transferValidator.transferValidate(GENERAL_EXTRA_SPACE_MIDDLE, bank));
    }

    @Test
    public void general_extra_space_end() {
        assertTrue(transferValidator.transferValidate(GENERAL_EXTRA_SPACE_END, bank));
    }

    @Test
    public void general_case_sensitivity() {
        assertTrue(transferValidator.transferValidate(GENERAL_CASE_SENSITIVITY, bank));
    }

    @Test
    public void general_missing_command() {
        assertFalse(transferValidator.transferValidate(GENERAL_MISSING_COMMAND, bank));
    }

    @Test
    public void general_wrong_command() {
        assertFalse(transferValidator.transferValidate(GENERAL_WRONG_COMMAND, bank));
    }

    @Test
    public void general_non_string_command() {
        assertFalse(transferValidator.transferValidate(GENERAL_NON_STRING_COMMAND, bank));
    }

    @Test
    public void general_missing_one_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_ACCOUNT_ID_MISSING, bank));
    }

    @Test
    public void general_missing_two_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_ACCOUNT_BOTH_ID_MISSING, bank));
    }

    @Test
    public void general_both_accounts_exist() {
        assertTrue(transferValidator.transferValidate(GENERAL_ACCOUNT_EXISTS, bank));
    }

    @Test
    public void general_one_account_doesnt_exist() {
        assertFalse(transferValidator.transferValidate(GENERAL_ACCOUNT_DOES_NOT_EXIST, bank));
    }

    @Test
    public void general_both_accounts_dont_exist() {
        assertFalse(transferValidator.transferValidate(GENERAL_ACCOUNT_BOTH_DOES_NOT_EXIST, bank));
    }

    @Test
    public void general_9_digit_first_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_9_DIGIT_ID, bank));
    }

    @Test
    public void general_7_digit_first_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_7_DIGIT_ID, bank));
    }

    @Test
    public void general_first_id_is_character() {
        assertFalse(transferValidator.transferValidate(GENERAL_ID_HAS_CHARACTERS, bank));
    }

    @Test
    public void general_first_id_is_decimal() {
        assertFalse(transferValidator.transferValidate(GENERAL_ID_IS_DECIMAL, bank));
    }

    @Test
    public void general_lowest_first_id() {
        bank.addAccount("00000000", checkingAccount);
        assertTrue(transferValidator.transferValidate(GENERAL_LOWEST_ID, bank));
    }

    @Test
    public void general_highest_first_id() {
        bank.addAccount("99999999", checkingAccount);
        assertTrue(transferValidator.transferValidate(GENERAL_HIGHEST_ID, bank));
    }

    @Test
    public void general_negative_first_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_NEGATIVE_ID, bank));
    }

    @Test
    public void general_9_digit_second_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_9_DIGIT_SECOND_ID, bank));
    }

    @Test
    public void general_7_digit_second_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_7_DIGIT_SECOND_ID, bank));
    }

    @Test
    public void general_second_id_is_character() {
        assertFalse(transferValidator.transferValidate(GENERAL_SECOND_ID_HAS_CHARACTERS, bank));
    }

    @Test
    public void general_second_id_is_decimal() {
        assertFalse(transferValidator.transferValidate(GENERAL_SECOND_ID_IS_DECIMAL, bank));
    }

    @Test
    public void general_lowest_second_id() {
        bank.addAccount("00000000", checkingAccount);
        assertTrue(transferValidator.transferValidate(GENERAL_LOWEST_SECOND_ID, bank));
    }

    @Test
    public void general_highest_second_id() {
        bank.addAccount("99999999", checkingAccount);
        assertTrue(transferValidator.transferValidate(GENERAL_HIGHEST_SECOND_ID, bank));
    }

    @Test
    public void general_negative_second_id() {
        assertFalse(transferValidator.transferValidate(GENERAL_NEGATIVE_SECOND_ID, bank));
    }

    @Test
    public void checking_withdraw_negative_value() {
        assertFalse(transferValidator.transferValidate(CHECKING_WITHDRAW_VALUE_NEGATIVE, bank));
    }

    @Test
    public void checking_withdraw_zero_value() {
        assertTrue(transferValidator.transferValidate(CHECKING_WITHDRAW_VALUE_ZERO, bank));
    }

    @Test
    public void checking_withdraw_highest_value() {
        assertTrue(transferValidator.transferValidate(CHECKING_WITHDRAW_VALUE_HIGHEST, bank));
    }

    @Test
    public void checking_withdraw_higher_than_max_value() {
        assertFalse(transferValidator.transferValidate(CHECKING_WITHDRAW_VALUE_HIGHER_THAN_MAX, bank));
    }

    @Test
    public void savings_withdraw_negative_value() {
        assertFalse(transferValidator.transferValidate(SAVINGS_WITHDRAW_VALUE_NEGATIVE, bank));
    }

    @Test
    public void savings_withdraw_zero_value() {
        assertTrue(transferValidator.transferValidate(SAVINGS_WITHDRAW_VALUE_ZERO, bank));
    }

    @Test
    public void savings_withdraw_highest_value() {
        assertTrue(transferValidator.transferValidate(SAVINGS_WITHDRAW_VALUE_HIGHEST, bank));
    }

    @Test
    public void savings_withdraw_higher_than_max_value() {
        assertFalse(transferValidator.transferValidate(SAVINGS_WITHDRAW_VALUE_HIGHER_THAN_MAX, bank));
    }

    @Test
    public void cd_withdraw_is_always_false() {
        assertFalse(transferValidator.transferValidate(CD_ARBITRARY_WITHDRAW_1, bank));
        assertFalse(transferValidator.transferValidate(CD_ARBITRARY_WITHDRAW_2, bank));
        assertFalse(transferValidator.transferValidate(CD_ARBITRARY_WITHDRAW_3, bank));
    }

    //

    @Test
    public void checking_deposit_negative_value() {
        assertFalse(transferValidator.transferValidate(CHECKING_DEPOSIT_VALUE_NEGATIVE, bank));
    }

    @Test
    public void checking_deposit_zero_value() {
        assertTrue(transferValidator.transferValidate(CHECKING_DEPOSIT_VALUE_ZERO, bank));
    }

    @Test
    public void checking_deposit_max_value() {
        assertTrue(transferValidator.transferValidate(CHECKING_DEPOSIT_VALUE_MAX, bank));
    }

    @Test
    public void checking_deposit_higher_than_max_value() {
        assertFalse(transferValidator.transferValidate(CHECKING_DEPOSIT_VALUE_HIGHER_THAN_MAX, bank));
    }

    @Test
    public void savings_deposit_negative_value() {
        bank.addAccount("97654322", savingsAccount);
        assertFalse(transferValidator.transferValidate(SAVINGS_DEPOSIT_VALUE_NEGATIVE, bank));
    }

    @Test
    public void savings_deposit_zero_value() {
        bank.addAccount("97654322", savingsAccount);
        assertTrue(transferValidator.transferValidate(SAVINGS_DEPOSIT_VALUE_ZERO, bank));
    }

    @Test
    public void savings_deposit_max_value() {
        bank.addAccount("97654322", savingsAccount);
        assertTrue(transferValidator.transferValidate(SAVINGS_DEPOSIT_VALUE_MAX, bank));
    }

    @Test
    public void savings_deposit_higher_than_max_value() {
        bank.addAccount("97654322", savingsAccount);
        assertFalse(transferValidator.transferValidate(SAVINGS_DEPOSIT_VALUE_HIGHER_THAN_MAX, bank));
    }

    @Test
    public void cd_deposit_is_always_false() {
        assertFalse(transferValidator.transferValidate(CD_ARBITRARY_DEPOSIT_1, bank));
        assertFalse(transferValidator.transferValidate(CD_ARBITRARY_DEPOSIT_2, bank));
        assertFalse(transferValidator.transferValidate(CD_ARBITRARY_DEPOSIT_3, bank));

    }

    @Test
    public void empty_string() {
        assertFalse(transferValidator.transferValidate(EMPTY_STRING, bank));
    }

    @Test
    public void just_spaces() {
        assertFalse(transferValidator.transferValidate(JUST_SPACES, bank));
    }


}
