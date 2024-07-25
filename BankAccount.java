package assignment1;


/*#Bank Account Exercise
#Create a program loop that asks the user if they want to continue.
#If they do, ask them what kind of transaction, deposit or withdrawal
#Otherwise thank the user and end the program.
#They must first deposit money to withdrawal because there
#is no money in the bank. If they try to withdrawal and
#they have insufficient funds, tell them and restart
#only allow amounts > 0 to be withdrawn.
#only allow Y or N as input for continuing the program, if they give the wrong
#input, tell them and ask again.
#Ask for Deposit (D), Withdrawal (W) or Balance(B)*/


public class BankAccount {
    private double balance;

    // Constructor
    public BankAccount() {
        this.balance = 0;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit was successful. Your new balance is: $" + balance);
        } else {
            System.out.println("Invalid amount. Please enter an amount greater than 0");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal was successful. Your new balance is: $" + balance);
            } else {
                System.out.println("Insufficient funds. Your balance is: $" + balance);
            }
        } else {
            System.out.println("Invalid amount. Please enter an amount greater than 0");
        }
    }

    // Method to check the current balance
    public void checkBalance() {
        System.out.println("Your balance is: $" + balance);
    }
}

