import java.util.List;

public class MasterControl {
    protected Bank bank;
    protected CommandValidator commandValidator;
    protected CommandProcessor commandProcessor;
    protected CommandStorage commandStorage;

    public MasterControl(Bank bank, CommandValidator commandValidator, CommandProcessor commandProcessor, CommandStorage commandStorage) {
        this.bank = bank;
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
    }

    public List<String> start(List<String> input) {

        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.process(command);
            } else {
                commandStorage.addInvalidString(command);
            }
        }
        return commandStorage.getInvalidStrings();
    }
}
