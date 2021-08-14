import java.util.Scanner;

public class UserInterface {
    final static String registrationStart = "This is a registration form";
    final static String enterName = "Please, enter your name";
    final static String enterAddress = "Please, enter your address. Step is optional, press enter if you want to skip it";
    final static String selectCurrency = "Please, select currency of your account. Enter 1 for USD, 2 for GBP or 3 for EUR";
    final static String oneMoreAccount = "Would you like to create one more currency account?";
    final static String alreadyHaveAccount = "You already have account in this currency";
    final static String selectTransactionType = "Please, select type of transaction to perform. Enter 1 if you want to add money or 2 to withdraw money";
    final static String selectAccount = "Please, select account.Enter 1 for USD, 2 for GBP or 3 for EUR";
    final static String enterAmount = "Enter amount to add or withdraw";
    final static String completedSuccessfully = "Completed Successfully";

    public static UserInput provideUserInfo() {
        System.out.println(registrationStart);
        System.out.println(enterName);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println(enterAddress);
        String address = scanner.nextLine();
        if(address.equals("")){
            address = null;
        }
        return new UserInput(name, address);
    }
}
