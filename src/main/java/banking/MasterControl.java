package banking;

import java.util.ArrayList;
import java.util.List;

public class MasterControl {
    protected Bank bank;
    protected CommandValidator commandValidator;
    protected CommandProcessor commandProcessor;
    protected CommandStorage commandStorage;
    protected Output output;

    public MasterControl(Bank bank, CommandValidator commandValidator, CommandProcessor commandProcessor, CommandStorage commandStorage, Output output) {
        this.bank = bank;
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
        this.output = output;
    }

    public ArrayList<String> start(List<String> input) {

        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.process(command);
            } else {
                commandStorage.addInvalidString(command);
            }
        }
        return commandStorage.getAllStrings(this.output.processOutput());
    }
}
