package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputTest {

    public final String QUICK_ID = "12345678";

    Accounts checkingAccount;
    Accounts savingsAccount;
    Bank bank;
    Output output;
    CommandProcessor commandProcessor;

    @BeforeEach
    void setup() {
        bank = new Bank();
        output = new Output(bank);
        commandProcessor = new CommandProcessor(bank);
        checkingAccount = Accounts.checking(2.5);
        savingsAccount = Accounts.savings(3);
        bank.addAccount(QUICK_ID, checkingAccount);
    }

    @Test
    public void output_current_state() {
        assertEquals("Checking 12345678 0.00 2.50", output.processOutput().get(0));
    }

    @Test
    public void output_transaction_history() {
        commandProcessor.process("Deposit 12345678 250");
        assertEquals("Deposit 12345678 250", output.processOutput().get(1));
    }

    @Test
    public void output_multiple_account_states_and_historys() {
        commandProcessor.process("Deposit 12345678 390");
        bank.addAccount("19122233", savingsAccount);
        commandProcessor.process("Transfer 12345678 19122233 90");
        assertEquals("Checking 12345678 300.00 2.50", output.processOutput().get(0));
        assertEquals("Deposit 12345678 390", output.processOutput().get(1));
        assertEquals("Transfer 12345678 19122233 90", output.processOutput().get(2));
        assertEquals("Savings 19122233 90.00 3.00", output.processOutput().get(3));
        assertEquals("Transfer 12345678 19122233 90", output.processOutput().get(4));
    }

}
