import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    public final int QUICK_ID = 12345678;
    public final float QUICK_APR = 1;

    Bank bank;
    Accounts account;
    Accounts account2;

    @BeforeEach
    void setup() {
        bank = new Bank();
        account = Accounts.checking(QUICK_APR);
        account2 = Accounts.savings(QUICK_APR + 2);
    }

    @Test
    public void bank_is_initially_empty() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    public void add_account_to_bank() {
        bank.addAccount(QUICK_ID, account);
        assertEquals(QUICK_APR, bank.getAccounts().get(QUICK_ID).getApr());
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void add_two_accounts_to_bank() {
        bank.addAccount(QUICK_ID, account);
        bank.addAccount(QUICK_ID + 1, account2);
        assertEquals(account2, bank.getAccounts().get(QUICK_ID + 1));
    }

    @Test
    public void deposit_money_to_bank_account() {
        bank.addAccount(QUICK_ID, account);
        bank.getAccounts().get(QUICK_ID).deposit(400);
        assertEquals(400, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void withdraw_money_from_bank_account() {
        bank.addAccount(QUICK_ID, account);
        bank.getAccounts().get(QUICK_ID).deposit(1000);
        bank.getAccounts().get(QUICK_ID).withdraw(500);
        assertEquals(500, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void method_test() {
        bank.addAccount(QUICK_ID, account);

    }
}
