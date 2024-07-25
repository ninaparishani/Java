package assignment1;

import java.util.Scanner;

public class BankAccountTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to bank account program!");

        BankAccount account = new BankAccount(); // Creating a BankAccount object using the constructor

        boolean continueTransaction = true;
        while (continueTransaction) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Deposit (D)");
            System.out.println("2. Withdraw (W)");
            System.out.println("3. Check Balance (B)");

            String choice = input.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.print("Please enter the amount to deposit: ");
                    double depositAmount = input.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case "W":
                    System.out.print("Please enter the amount to withdraw: ");
                    double withdrawAmount = input.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case "B":
                    account.checkBalance();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }

            input.nextLine(); // Consume newline

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Do you want to continue (Y/N)? ");
                String continueChoice = input.nextLine().toUpperCase();
                if (continueChoice.equals("Y")) {
                    validChoice = true;
                } else if (continueChoice.equals("N")) {
                    validChoice = true;
                    continueTransaction = false;
                } else {
                    System.out.println("Invalid input! Please enter Y or N.");
                }
            }
        }

        System.out.println("Thank you for using the bank account program");
        input.close();
    }
}
