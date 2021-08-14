import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        Program program = new Program();
        program.run();
    }

    private void run() {
        System.out.println("This is a registration form");
        System.out.println("Please, enter your name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Please, enter your address. Step is optional, press enter if you want to skip it");
        String address = scanner.nextLine();
        User user;
        if (!address.equals("")){
            user = new User(name, address);
        } else {
            user = new User(name);
        }
//        user.registerUser(user);


        String query = "INSERT INTO users (name, address) VALUES (" + "'"+ user.name + "'" + "," + "'" + user.address + "'" + ") RETURNING user_id;";

        user.userId = Database.executeQueryWithResult(query);

        System.out.println("Please, select currency of your account. Enter 1 for USD, 2 for GBP or 3 for EUR");
        Account account = new Account();
        account.balance = 0.0;
        int currencyIndex = Integer.parseInt(scanner.nextLine());
        selectCurrency(currencyIndex, account);

//        user create account


        String queryAccount = "INSERT INTO accounts (balance, currency, user_id) VALUES (" + "'"+ account.balance + "'" + "," + "'" + account.currency + "'" + "," + "'"+ user.userId + "'" +");";

        Database.executeQueryWithResultAccount(queryAccount);

        System.out.println("If you want to create one more account enter 1 for USD, 2 for GBP or 3 for EUR or press enter to skip");
        currencyIndex = Integer.parseInt(scanner.nextLine());
        if (!String.valueOf(currencyIndex).equals("")) {
            selectCurrency(currencyIndex, account);
            if (account.hasCurrencyAccount(user.userId, account.currency)) {
                System.out.println("You already have account in this currency");
            } else {
                queryAccount = "INSERT INTO accounts (balance, currency, user_id) VALUES (" + "'"+ account.balance + "'" + "," + "'" + account.currency + "'" + "," + "'"+ user.userId + "'" +");";
                Database.executeQueryWithResultAccount(queryAccount);
            }
        }
//            create transaction
            System.out.println("Please, select type of transaction to perform. Enter 1 if you want to add money or 2 to withdraw money");
            int transactionType = Integer.parseInt(scanner.nextLine());
            System.out.println("Please, select account.Enter 1 for USD, 2 for GBP or 3 for EUR");
            currencyIndex = Integer.parseInt(scanner.nextLine());
            selectCurrency(currencyIndex, account);
            System.out.println("Enter amount to add or withdraw");
            Transaction transaction = new Transaction();
            transaction.amount = Double.valueOf(scanner.nextLine());
            if (transactionType == 1){
                transaction.addMoney(transaction.amount, account.currency);
            } else {
                String accountIdQuery = "SELECT account_id FROM accounts where currency = '" + account.currency + "' AND user_id = '" + user.userId + "';";
                account.accountId = Database.executeQueryWithAccountResult(accountIdQuery);
                transaction.subtractMoney(transaction.amount, account.accountId);
            }

    }

    private void selectCurrency(int index, Account account) {
        String currency = "";
        switch (index){
            case 1:
                currency = String.valueOf(Currency.USD);
                account.currency = currency;
                break;
            case 2:
                currency = String.valueOf(Currency.GBP);
                account.currency = currency;
                break;
            case 3:
                currency = String.valueOf(Currency.EUR);
                account.currency = currency;
                break;
        }
    }
}
