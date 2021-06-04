package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassProcessorTest {
    public final String QUICK_ID = "12345678";
    public final String QUICK_ID2 = "12121212";
    public final String QUICK_ID3 = "11111111";
    Bank bank;
    PassProcessor passProcessor;
    Accounts checkingAccount;
    Accounts savingsAccount;
    Accounts cdAccount;

    @BeforeEach
    void setup() {
        bank = new Bank();
        passProcessor = new PassProcessor(bank);
        checkingAccount = Accounts.checking(1);
        savingsAccount = Accounts.checking(1.5);
        cdAccount = Accounts.cd(2, 2000);
        checkingAccount.deposit(1000);
        savingsAccount.deposit(1500);
        bank.addAccount(QUICK_ID, checkingAccount);
        bank.addAccount(QUICK_ID2, savingsAccount);
        bank.addAccount(QUICK_ID3, cdAccount);
    }

    @Test
    public void pass_apr_works_once() {
        passProcessor.process(1);
        assertEquals(1000.83, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void pass_aging_works_once() {
        passProcessor.process(1);
        assertEquals(1, bank.getAccounts().get(QUICK_ID).getAge());
    }

    @Test
    public void pass_deletes_zero_balance_account() {
        bank.getAccounts().get(QUICK_ID).balance = 0;
        passProcessor.process(1);
        assertEquals(null, bank.getAccounts().get(QUICK_ID));
    }

    @Test
    public void pass_removes_minimum_balance_fee() {
        bank.getAccounts().get(QUICK_ID).balance = 99;
        passProcessor.process(1);
        assertEquals(74.06, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    //

    @Test
    public void pass_two_months_apr() {
        passProcessor.process(2);
        assertEquals(1001.66, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void pass_two_months_aging() {
        passProcessor.process(2);
        assertEquals(2, bank.getAccounts().get(QUICK_ID).getAge());
    }

    @Test
    public void pass_deletes_empty_account_on_second_month() {
        bank.getAccounts().get(QUICK_ID2).balance = 25;
        passProcessor.process(2);
        assertEquals(null, bank.getAccounts().get(QUICK_ID2));
    }

    @Test
    public void pass_two_months_remove_minimum_balance_fee() {
        bank.getAccounts().get(QUICK_ID2).balance = 99;
        passProcessor.process(2);
        assertEquals(49.15, bank.getAccounts().get(QUICK_ID2).getBalance());
    }

    //

    @Test
    public void pass_does_apr_for_all_accounts() {
        passProcessor.process(3);
        assertEquals(1002.49, bank.getAccounts().get(QUICK_ID).getBalance());
        assertEquals(1505.61, bank.getAccounts().get(QUICK_ID2).getBalance());
        assertEquals(2040.35, bank.getAccounts().get(QUICK_ID3).getBalance());
    }

    @Test
    public void pass_ages_all_accounts() {
        passProcessor.process(5);
        assertEquals(5, bank.getAccounts().get(QUICK_ID).getAge());
        assertEquals(5, bank.getAccounts().get(QUICK_ID2).getAge());
        assertEquals(5, bank.getAccounts().get(QUICK_ID3).getAge());
    }

    @Test
    public void pass_removes_all_empty_accounts() {
        bank.getAccounts().get(QUICK_ID).balance = 0;
        bank.getAccounts().get(QUICK_ID2).balance = 0;
        bank.getAccounts().get(QUICK_ID3).balance = 0;
        passProcessor.process(1);
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    public void pass_removes_minimum_balance_fees_on_all_accounts() {
        bank.getAccounts().get(QUICK_ID).balance = 99;
        bank.getAccounts().get(QUICK_ID2).balance = 98;
        bank.getAccounts().get(QUICK_ID3).balance = 97;
        passProcessor.process(2);
        assertEquals(49.1, bank.getAccounts().get(QUICK_ID).getBalance());
        assertEquals(48.15, bank.getAccounts().get(QUICK_ID2).getBalance());
        assertEquals(47.79, bank.getAccounts().get(QUICK_ID3).getBalance());
    }

    //

    @Test
    public void pass_ages_only_existing_accounts() {
        bank.getAccounts().remove(QUICK_ID);
        passProcessor.process(1);
        bank.addAccount(QUICK_ID, checkingAccount);
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getAge());
        assertEquals(1, bank.getAccounts().get(QUICK_ID2).getAge());
    }

    @Test
    public void pass_doesnt_remove_previously_deleted_account_id() {
        bank.getAccounts().get(QUICK_ID).balance = 0;
        passProcessor.process(1);
        checkingAccount = Accounts.checking(2);
        checkingAccount.deposit(1000);
        bank.addAccount(QUICK_ID, checkingAccount);
        passProcessor.process(1);
        assertEquals(1001.66, bank.getAccounts().get(QUICK_ID).getBalance());
    }

}
