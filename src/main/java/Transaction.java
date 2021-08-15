public class Transaction {
    Double amount;

    public Double getAmount() {
        return amount;
    }

    public int getAccountId() {
        return accountId;
    }

    int accountId;

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
