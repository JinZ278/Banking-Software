import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    List<String> input;
    Bank bank;
    MasterControl masterControl;

    @BeforeEach
    void setup() {
        input = new ArrayList<>();
        bank = new Bank();
        masterControl = new MasterControl(bank, new CommandValidator(bank), new CommandProcessor(bank), new CommandStorage());
    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @Test
    public void typo_in_create_command_is_invalid() {
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("creat checking 12345678 1.0", actual);
    }

    @Test
    public void typo_in_deposit_command_is_invalid() {
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("depositt 12345678 100", actual);
    }

    @Test
    public void two_typo_commands_both_invalid() {
        input.add("creat checking 12345678 1.0");
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);
        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 1.0", input.get(0));
        assertEquals("depositt 12345678 100", input.get(1));

    }

    @Test
    public void invalid_to_create_accounts_with_same_id() {
        input.add("create checking 12345678 1.2");
        input.add("create checking 12345678 1.2");

        List<String> actual = masterControl.start(input);

        assertSingleCommand("create checking 12345678 1.2", actual);

    }
}