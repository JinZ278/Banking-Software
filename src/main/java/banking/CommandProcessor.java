package banking;

import static java.lang.Integer.parseInt;

public class CommandProcessor {

    public Bank bank;
    CreateProcessor createProcessor;
    DepositProcessor depositProcessor;
    WithdrawProcessor withdrawProcessor;
    TransferProcessor transferProcessor;
    PassProcessor passProcessor;

    public CommandProcessor(Bank bank) {
        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);
        withdrawProcessor = new WithdrawProcessor(bank);
        transferProcessor = new TransferProcessor(bank);
        passProcessor = new PassProcessor(bank);
        this.bank = bank;
    }

    public void process(String valid_string) {
        String[] info = valid_string.split(" ");

        if (info[0].toLowerCase().equals("create")) {
            createProcessor.processCreate(info);
        }
        if (info[0].toLowerCase().equals("deposit")) {
            depositProcessor.processDeposit(info);
            this.bank.getAccounts().get(info[1]).history.add(valid_string);
        }
        if (info[0].toLowerCase().equals("withdraw")) {
            withdrawProcessor.processWithdraw(info);
            this.bank.getAccounts().get(info[1]).history.add(valid_string);
        }
        if (info[0].toLowerCase().equals("transfer")) {
            transferProcessor.processTransfer(info);
            this.bank.getAccounts().get(info[1]).history.add(valid_string);
            this.bank.getAccounts().get(info[2]).history.add(valid_string);
        }
        if (info[0].toLowerCase().equals("pass")) {
            passProcessor.processPass(parseInt(info[1]));
        }
    }
}
