public class Account {
    int accountId;
    Double balance;
    String currency;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    Double calculateBalance(Double amount, int accountId) {
        return balance;
    }


    Double getBalance(int accountId){
        return balance;
    }

    Boolean hasCurrencyAccount(int userId, String currency){
        String query = "SELECT currency FROM accounts WHERE user_id =" + "'" + userId + "';";
        var currencyAccounts = Database.executeQueryWithResultCurrency(query);
        if(currencyAccounts.stream().anyMatch(n->n.equals(currency))){
            return true;
        } else {
            return false;
        }
    }
}
