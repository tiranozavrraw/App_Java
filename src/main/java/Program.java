public class Program {
    public static void main(String[] args) {
        Program program = new Program();
        program.run();
    }

    private void run() {
        Repository repository = new PostgresqlRepository();

        UserInput input = UserInterface.provideUserInfo();
        User user = new User(input.name, input.address);
        int userId = repository.createUserAndGetID(user);
        user.setUserId(userId);


        String currency = UserInterface.provideAccountCurrency();
        Account account = new Account();
        account.setBalance(0.0);
        account.setCurrency(currency);
        account.setUserId(userId);
        repository.createAccount(account);


        currency = UserInterface.provideOneMoreAccountCurrency();
        while (currency != null) {
            if (!repository.hasCurrencyAccount(user.getUserId(), currency)) {
                account.setBalance(0.0);
                account.setCurrency(currency);
                account.setUserId(userId);
                repository.createAccount(account);
            } else {
                UserInterface.provideAlreadyHaveAccountMessage();
            }
            currency = UserInterface.provideOneMoreAccountCurrency();
        }

        int transactionType = UserInterface.provideTransactionType();
        currency = UserInterface.provideSelectedCurrencyAccount();
        Double amount = UserInterface.provideAmount();
        Transaction transaction = new Transaction();
        if (transactionType == 2) {
            amount = -Math.abs(amount);
        }
        transaction.setAmount(amount);
        int transactionAccountID = repository.getAccountId(user.getUserId(), currency);
        transaction.setAccountId(transactionAccountID);
        repository.applyTransaction(transaction);

        UserInterface.provideCompletedSuccessfullyMessage();

    }
}
