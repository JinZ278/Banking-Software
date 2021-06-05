package banking;

import org.junit.jupiter.api.BeforeEach;

public class WithdrawValidatorTest {

    WithdrawValidator withdrawValidator;
    Bank bank;
    Accounts checkingAccount;
    Accounts savingsAccount;
    Accounts cdAccount;

    @BeforeEach
    void setup() {
        withdrawValidator = new WithdrawValidator();
        bank = new Bank();
        checkingAccount = Accounts.checking(1);
        savingsAccount = Accounts.savings(2);
        cdAccount = Accounts.cd(3, 1200);
    }

}
