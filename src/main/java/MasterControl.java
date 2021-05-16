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
}
