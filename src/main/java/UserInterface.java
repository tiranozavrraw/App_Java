import java.util.Scanner;

public class UserInterface {
    private final static String registrationStart = "This is a registration form";
    private final static String enterName = "Please, enter your name";
    private final static String enterAddress = "Please, enter your address. Step is optional, press enter if you want to skip it";
    private final static String createAccount = "Please, select currency of your account. Enter 1 for USD, 2 for GBP or 3 for EUR";
    private final static String oneMoreAccount = "If you want to create one more account enter 1 for USD, 2 for GBP or 3 for EUR or press enter to skip";
    private final static String alreadyHaveAccount = "You already have account in this currency";
    private final static String selectTransactionType = "Please, select type of transaction to perform. Enter 1 if you want to add money or 2 to withdraw money";
    private final static String selectAccount = "Please, select account.Enter 1 for USD, 2 for GBP or 3 for EUR";
    private final static String enterAmount = "Enter amount to add or withdraw";
    private final static String completedSuccessfully = "Completed Successfully";
    private final static String createTransaction = "Would you like to create transaction? Enter 1 if YES or 2 if NO";
    private final static String unacceptedTransactionAmount = "Please, enter positive amount that is bigger than 0 and less than 100.000.000";
    private final static String unacceptedBalance = "Balance can not be negative or be bigger then 2.000.000.000";

    public static UserInput provideUserInfo() {
        System.out.println(registrationStart);
        System.out.println(enterName);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println(enterAddress);
        String address = scanner.nextLine();
        if (address.equals("")) {
            address = null;
        }
        return new UserInput(name, address);
    }

    public static String provideAccountCurrency() {
        System.out.println(createAccount);
        int currencyIndex = Integer.parseInt(readLine());
        return mapCurrencyIndexWithCurrency(currencyIndex);
    }

    public static String provideOneMoreAccountCurrency() {
        System.out.println(oneMoreAccount);
        String currencyIndex = readLine();
        if (!currencyIndex.equals("")) {
            String currency = mapCurrencyIndexWithCurrency(Integer.parseInt(currencyIndex));
            return currency;
        } else {
            return null;
        }
    }

    public static int provideTransactionType() {
        System.out.println(selectTransactionType);
        int transactionType = Integer.parseInt(readLine());
        return transactionType;

    }

    public static String provideSelectedCurrencyAccount() {
        System.out.println(selectAccount);
        int currencyIndex = Integer.parseInt(readLine());
        String selectedCurrency = mapCurrencyIndexWithCurrency(currencyIndex);
        return selectedCurrency;
    }

    public static Double provideAmount() {
        System.out.println(enterAmount);
        Double amount = Double.valueOf(readLine());
        return amount;
    }

    public static Boolean provideCreateTransaction() {
        System.out.println(createTransaction);
        int oneMoreTransaction = Integer.parseInt(readLine());
        if (oneMoreTransaction == 1){
            return true;
        } else {
            return false;
        }
    }

    public static void provideAlreadyHaveAccountMessage() {
        System.out.println(alreadyHaveAccount);
    }

    public static void provideUnacceptedTransactionAmountMessage() {
        System.out.println(unacceptedTransactionAmount);
    }

    public static void unacceptedBalance() {
        System.out.println(unacceptedBalance);
    }

    public static void provideCompletedSuccessfullyMessage() {
        System.out.println(completedSuccessfully);
    }

    private static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static String mapCurrencyIndexWithCurrency(int index) {
            String currency = "";
            switch (index){
                case 1:
                    currency = String.valueOf(Currency.USD);
                    break;
                case 2:
                    currency = String.valueOf(Currency.GBP);
                    break;
                case 3:
                    currency = String.valueOf(Currency.EUR);
                    break;
            }
            return currency;
        }


}
