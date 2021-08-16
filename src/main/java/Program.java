public class Program {
    public static void main(String[] args) {
        Program program = new Program();
        program.run();
    }

    private void run() {
        Repository repository = new PostgresqlRepository(new Database());

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

        while (UserInterface.provideCreateTransaction()) {

            int transactionType = UserInterface.provideTransactionType();
            currency = UserInterface.provideSelectedCurrencyAccount();
            Double amount = UserInterface.provideAmount();
            if (checkAmount(amount)) {
                Transaction transaction = new Transaction();
                if (transactionType == 2) {
                    amount = -Math.abs(amount);
                }
                transaction.setAmount(amount);
                int transactionAccountID = repository.getAccountId(user.getUserId(), currency);
                transaction.setAccountId(transactionAccountID);
                Double balance = repository.getBalance(transaction);
                if(checkBalance(transaction.getAmount(), balance)){
                    repository.applyTransaction(transaction);
                    UserInterface.provideCompletedSuccessfullyMessage();
                } else {
                    UserInterface.unacceptedBalance();
                }

            } else {
                UserInterface.provideUnacceptedTransactionAmountMessage();
            }

        }

    }

    private static Boolean checkAmount(Double amount) {
        if (amount > 0 && amount <= 100000000) {
            return true;
        } else {
            return false;
        }
    }

    private static Boolean checkBalance(Double amount, Double balance) {
        Double result = balance + amount;
            if (result <= 2000000000 && result >= 0) {
                return true;
            }
            return false;

        }


}
