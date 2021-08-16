public interface Repository {
    int createUserAndGetID(User user);
    void createAccount(Account account);
    void applyTransaction(Transaction transaction);
    Boolean hasCurrencyAccount(int userId, String currency);
    int getAccountId(int userId, String currency);
    Double getBalance(Transaction transaction);
}
