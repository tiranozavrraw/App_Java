import java.sql.ResultSet;

public interface Repository {
    public int createUserAndGetID(User user);
    public void createAccount(Account account);
    public void applyTransaction(Transaction transaction);
    public Boolean hasCurrencyAccount(int userId, String currency);
    public int getAccountId(int userId, String currency);
}
