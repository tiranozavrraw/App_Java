public class PostgresqlRepository  implements Repository{
    @Override
    public int createUserAndGetID(User user) {
        String query = "INSERT INTO users (name, address) VALUES (?, ?) RETURNING user_id;";

        int userId = Database.executeCreateUserAndGetIdQuery(query, user);
        return userId;
    }

    @Override
    public void createAccount(Account account) {
        String queryAccount = "INSERT INTO accounts (balance, currency, user_id) VALUES (?, ?, ?);";

        Database.executeCreateAccountQuery(queryAccount, account);

    }

    @Override
    public void applyTransaction(Transaction transaction) {
        String query = "BEGIN;UPDATE accounts SET balance = balance + ? WHERE account_id = ? " +
                ";INSERT INTO transactions (amount, account_id) VALUES (?, ?);COMMIT;";
        Database.executeQuery(query, transaction);

    }

    @Override
    public Boolean hasCurrencyAccount(int userId, String currency) {
        String query = "SELECT currency FROM accounts WHERE user_id = ?;";
        var currencyAccounts = Database.executeSelectCurrencyAccountsOfUserQuery(query, userId);
        if(currencyAccounts.stream().anyMatch(n->n.equals(currency))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getAccountId(int userId, String currency) {
        String accountIdQuery = "SELECT account_id FROM accounts where currency = ? AND user_id = ? ;";
        int accountId = Database.executeSelectAccountAndReturnIdQuery(accountIdQuery, userId, currency);
        return accountId;
    }

    @Override
    public Double getBalance(Transaction transaction) {
        String balanceQuery = "SELECT balance FROM accounts where account_id = ? ;";
        Double balance = Database.executeSelectAccountBalanceQuery(balanceQuery, transaction);
        return balance;
    }
}
