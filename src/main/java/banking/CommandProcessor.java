package banking;

import static java.lang.Integer.parseInt;

public class CommandProcessor {

    public Bank bank;
    CreateProcessor createProcessor;
    DepositProcessor depositProcessor;
    WithdrawProcessor withdrawProcessor;
    PassProcessor passProcessor;

    public CommandProcessor(Bank bank) {
        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);
        withdrawProcessor = new WithdrawProcessor(bank);
        passProcessor = new PassProcessor(bank);
    }

    public void process(String valid_create_string) {
        String[] info = stringSplitter(valid_create_string);
        if (info[0].toLowerCase().equals("create")) {
            createProcessor.processCreate(info);
        }
        if (info[0].toLowerCase().equals("deposit")) {
            depositProcessor.processDeposit(info);
        }
        if (info[0].toLowerCase().equals("withdraw")) {
            withdrawProcessor.processWithdraw(info);
        }
        if (info[0].toLowerCase().equals("pass")) {
            passProcessor.processPass(parseInt(info[1]));
        }
    }

    private String[] stringSplitter(String newString) {
        String[] stringSplitIntoArray = newString.split(" ");
        return stringSplitIntoArray;
    }
}
