import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    final String URL_DB = "jdbc:postgresql://127.0.0.1:5432/JavaHW";
    final String USER = "postgres";
    final String PASS = "*";

    private Database(){}

    public static Database connect() {
        return new Database();
    }

    public void executeTransaction(String query, Transaction transaction) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1,transaction.getAmount());
            preparedStatement.setInt(2,transaction.getAccountId());
            preparedStatement.setDouble(3,transaction.getAmount());
            preparedStatement.setInt(4,transaction.getAccountId());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Integer executeCreateUserAndGetId(String query, User user) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            ResultSet set = preparedStatement.executeQuery();
            int id = 0;
            while (set.next()) {
                id = Integer.parseInt(set.getString("user_id"));
            }
            set.close();
            connection.close();
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Integer executeSelectAccountAndGetId(String query, int userId, String currency) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, currency);
            prepareStatement.setInt(2, userId);
            ResultSet set = prepareStatement.executeQuery();
            int id = 0;
            while (set.next()) {
                id = Integer.parseInt(set.getString("account_id"));
            }
            set.close();
            connection.close();
            return id;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void executeCreateAccount(String query, Account account) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setDouble(1, account.getBalance());
            prepareStatement.setString(2, account.getCurrency());
            prepareStatement.setInt(3, account.getUserId());
            prepareStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> executeSelectCurrencyAccountsOfUser(String query, int userId) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, userId);
            ResultSet set = prepareStatement.executeQuery();
            List<String> currencyAccounts = new ArrayList<>();
            while (set.next()) {
                String currency = set.getString("currency");
                currencyAccounts.add(currency);
            }
            set.close();
            connection.close();
            return currencyAccounts;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Double executeSelectAccountBalance(String balanceQuery, Transaction transaction) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            PreparedStatement prepareStatement = connection.prepareStatement(balanceQuery);
            prepareStatement.setInt(1, transaction.getAccountId());
            ResultSet set = prepareStatement.executeQuery();
            Double balance = 0.0;
            while (set.next()) {
                balance = Double.valueOf(set.getString("balance"));
            }
            set.close();
            connection.close();
            return balance;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
