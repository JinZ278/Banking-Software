import org.junit.jupiter.api.BeforeEach;

public class MasterControlTest {

    Bank bank;
    MasterControl masterControl;

    @BeforeEach
    void setup() {
        bank = new Bank();
        masterControl = new MasterControl(bank, new CommandValidator(bank), new CommandProcessor(bank), new CommandStorage());
    }
}
