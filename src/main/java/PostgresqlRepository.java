public class PostgresqlRepository  implements Repository{
    Database database;
    PostgresqlRepository(Database database){
        this.database = database;
    }

    @Override
    public int createUserAndGetID(User user) {
        String query = "INSERT INTO users (name, address) VALUES (?, ?) RETURNING user_id;";

        int userId = database.executeCreateUserAndGetId(query, user);
        return userId;
    }

    @Override
    public void createAccount(Account account) {
        String queryAccount = "INSERT INTO accounts (balance, currency, user_id) VALUES (?, ?, ?);";

        database.executeCreateAccount(queryAccount, account);

    }

    @Override
    public void applyTransaction(Transaction transaction) {
        String query = "BEGIN;UPDATE accounts SET balance = balance + ? WHERE account_id = ? " +
                ";INSERT INTO transactions (amount, account_id) VALUES (?, ?);COMMIT;";
        database.executeTransaction(query, transaction);

    }

    @Override
    public Boolean hasCurrencyAccount(int userId, String currency) {
        String query = "SELECT currency FROM accounts WHERE user_id = ?;";
        var currencyAccounts = database.executeSelectCurrencyAccountsOfUser(query, userId);
        if(currencyAccounts.stream().anyMatch(n->n.equals(currency))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getAccountId(int userId, String currency) {
        String accountIdQuery = "SELECT account_id FROM accounts where currency = ? AND user_id = ? ;";
        int accountId = database.executeSelectAccountAndGetId(accountIdQuery, userId, currency);
        return accountId;
    }

    @Override
    public Double getBalance(Transaction transaction) {
        String balanceQuery = "SELECT balance FROM accounts where account_id = ? ;";
        Double balance = database.executeSelectAccountBalance(balanceQuery, transaction);
        return balance;
    }
}
