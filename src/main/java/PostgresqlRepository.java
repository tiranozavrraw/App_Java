public class PostgresqlRepository  implements Repository{
    @Override
    public int createUserAndGetID(User user) {
        String query = "INSERT INTO users (name, address) VALUES (" + "'"+ user.name + "'" + "," + "'" + user.address + "'" + ") RETURNING user_id;";

        int userId = Database.executeQueryWithResult(query);
        return userId;
    }

    @Override
    public void createAccount(Account account) {
        String queryAccount = "INSERT INTO accounts (balance, currency, user_id) VALUES (" + "'"+ account.balance + "'" + "," + "'" + account.currency + "'" + "," + "'"+ account.userId + "'" +");";

        Database.executeQueryWithResultAccount(queryAccount);

    }

    @Override
    public void applyTransaction(Transaction transaction) {
        String query = "BEGIN;UPDATE accounts SET balance = balance +" + transaction.amount + "WHERE account_id =" + transaction.accountId +
                ";INSERT INTO transactions (amount, account_id) VALUES ('" + transaction.amount + "', '" +transaction.accountId + "');COMMIT;";
        Database.executeQuery(query);

    }

    @Override
    public Boolean hasCurrencyAccount(int userId, String currency) {
        String query = "SELECT currency FROM accounts WHERE user_id =" + "'" + userId + "';";
        var currencyAccounts = Database.executeQueryWithResultCurrency(query);
        if(currencyAccounts.stream().anyMatch(n->n.equals(currency))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getAccountId(int userId, String currency) {
        String accountIdQuery = "SELECT account_id FROM accounts where currency = '" + currency + "' AND user_id = '" + userId + "';";
        int accountId = Database.executeQueryWithAccountResult(accountIdQuery);
        return accountId;
    }
}
