import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CommandStorageTest {

    public final String INVALID_STRING = "efjbjfaowfaoawfjwaslakjbf auwhoabwf auwf";
    public final String ANOTHER_STRING = "huekkbefsfhsfnd";

    public List<String> list1 = new ArrayList<>();

    CommandStorage commandStorage;

    @BeforeEach
    void setup() {
        commandStorage = new CommandStorage();
    }

    @Test
    public void adding_invalid_strings() {
        commandStorage.addInvalidString(INVALID_STRING);
        assertFalse(commandStorage.invalidStrings.isEmpty());
    }

    @Test
    public void returning_invalid_commands() {
        commandStorage.addInvalidString(INVALID_STRING);
        commandStorage.addInvalidString(ANOTHER_STRING);
        list1.add(INVALID_STRING);
        list1.add(ANOTHER_STRING);
        assertEquals(list1, commandStorage.getInvalidStrings());
    }

}
