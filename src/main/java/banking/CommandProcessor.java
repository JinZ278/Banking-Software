package banking;

public class CommandProcessor {

    public Bank bank;
    CreateProcessor createProcessor;
    DepositProcessor depositProcessor;


    public CommandProcessor(Bank bank) {
        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);
    }

    public void process(String valid_create_string) {
        String[] info = stringSplitter(valid_create_string);
        if (info[0].toLowerCase().equals("create")) {
            createProcessor.processCreate(info);
        }
        if (info[0].toLowerCase().equals("deposit")) {
            depositProcessor.processDeposit(info);
        }
    }

    private String[] stringSplitter(String newString) {
        String[] stringSplitIntoArray = newString.split(" ");
        return stringSplitIntoArray;
    }
}
