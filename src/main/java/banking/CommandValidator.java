package banking;

public class CommandValidator {
    protected Bank bank;
    protected boolean validation;

    CreateValidator createValidator;
    DepositValidator depositValidator;
    PassValidator passValidator;
    WithdrawValidator withdrawValidator;
    TransferValidator transferValidator;

    CommandValidator(Bank bank) {
        this.bank = bank;
        this.validation = false;
        createValidator = new CreateValidator();
        depositValidator = new DepositValidator();
        withdrawValidator = new WithdrawValidator();
        transferValidator = new TransferValidator();
        passValidator = new PassValidator();
    }

    public boolean validate(String command_string) {
        String[] stringInfoInArray = command_string.split(" ");

        if (stringInfoInArray.length == 0) {
            return this.validation;
        }

        try {
            if (stringInfoInArray[0].toLowerCase().equals("create")) {
                this.validation = createValidator.createValidate(command_string, this.bank);
            }

            if (stringInfoInArray[0].toLowerCase().equals("deposit")) {
                this.validation = depositValidator.depositValidate(command_string, this.bank);
            }

            if (stringInfoInArray[0].toLowerCase().equals("withdraw")) {
                this.validation = withdrawValidator.withdrawValidate(command_string, this.bank);
            }

            if (stringInfoInArray[0].toLowerCase().equals("transfer")) {
                this.validation = transferValidator.transferValidate(command_string, this.bank);
            }

            if (stringInfoInArray[0].toLowerCase().equals("pass")) {
                this.validation = passValidator.passValidate(command_string);
            }
        } catch (Exception e) {
            this.validation = false;
        }

        return this.validation;
    }
}
