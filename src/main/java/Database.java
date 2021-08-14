import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static final String URL_DB = "jdbc:postgresql://127.0.0.1:5432/JavaHW";
    static final String USER = "postgres";
    static final String PASS = "*";


    public static void executeQuery(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static Integer executeQueryWithResult(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            int id = 0;
            while (set.next()) {
                id = Integer.parseInt(set.getString("user_id"));
            }
            connection.close();
            return id;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static Integer executeQueryWithAccountResult(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            int id = 0;
            while (set.next()) {
                id = Integer.parseInt(set.getString("account_id"));
            }
            connection.close();
            return id;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static Integer executeQueryWithResultAccount(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            int id = 0;
            while (set.next()) {
                id = Integer.parseInt(set.getString("account_id"));
            }
            connection.close();
            return id;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static List<String> executeQueryWithResultCurrency(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            List<String> currencyAccounts = new ArrayList<>();
            while (set.next()) {
                String currency = set.getString("currency");
                currencyAccounts.add(currency);
            }
            connection.close();
            return currencyAccounts;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


}
