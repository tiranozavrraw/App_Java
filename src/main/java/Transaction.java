public class Transaction {
    int transactionId;
    Double amount;

    public void addMoney(Double amount, String currency){

    }

    public void subtractMoney(Double amount, int accountId){
        //create transaction with negative amount
       String query = "BEGIN;UPDATE accounts SET balance = balance -" + amount + "WHERE account_id =" + accountId + ";INSERT INTO transactions (amount, account_id) VALUES ('" +amount + "', '" +accountId + "');COMMIT;";
       Database.executeQuery(query);
    }
}
