public class Transaction {
    int transactionId;
    Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    int accountId;

    public void addMoney(Double amount, int accountId){
        String query = "BEGIN;UPDATE accounts SET balance = balance +" + amount + "WHERE account_id =" + accountId + ";INSERT INTO transactions (amount, account_id) VALUES ('" +amount + "', '" +accountId + "');COMMIT;";
        Database.executeQuery(query);

    }

    public void subtractMoney(Double amount, int accountId){
        //create transaction with negative amount
       String query = "BEGIN;UPDATE accounts SET balance = balance -" + amount + "WHERE account_id =" + accountId + ";INSERT INTO transactions (amount, account_id) VALUES ('-" +amount + "', '" +accountId + "');COMMIT;";
       Database.executeQuery(query);
    }
}
