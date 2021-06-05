package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawProcessorTest {

    public final String QUICK_ID = "12345678";

    public final String[] VALID_WITHDRAW_FROM_CHECKING_COMMAND_IN_ARRAY = {"Withdraw", "12345678", "12.22"};
    public final String[] VALID_WITHDRAW_FROM_SAVINGS_COMMAND_IN_ARRAY = {"Withdraw", "12345678", "69"};
    public final String[] VALID_WITHDRAW_FROM_CD_COMMAND_IN_ARRAY = {"Withdraw", "12345678", "1127.27"};

    Bank bank;
    WithdrawProcessor withdrawProcessor;
    PassProcessor passProcessor;
    Accounts accounts;

    @BeforeEach
    void setup() {
        bank = new Bank();
        withdrawProcessor = new WithdrawProcessor(bank);
        passProcessor = new PassProcessor(bank);
    }

    @Test
    public void withdraw_from_checking_account() {
        accounts = Accounts.checking(1);
        accounts.balance = 1000;
        bank.addAccount(QUICK_ID, accounts);
        withdrawProcessor.processWithdraw(VALID_WITHDRAW_FROM_CHECKING_COMMAND_IN_ARRAY);
        assertEquals(1000 - 12.22, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void withdraw_from_savings_account() {
        accounts = Accounts.savings(2);
        accounts.balance = 1000;
        bank.addAccount(QUICK_ID, accounts);
        withdrawProcessor.processWithdraw(VALID_WITHDRAW_FROM_SAVINGS_COMMAND_IN_ARRAY);
        assertEquals(1000 - 69, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    public void withdraw_from_cd_account() {
        accounts = Accounts.cd(3, 1000);
        bank.addAccount(QUICK_ID, accounts);
        passProcessor.processPass(12);
        withdrawProcessor.processWithdraw(VALID_WITHDRAW_FROM_CD_COMMAND_IN_ARRAY);
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }


}
