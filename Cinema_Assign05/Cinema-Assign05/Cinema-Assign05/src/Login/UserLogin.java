package Login;

import Data.*;
import Utility.DataBase;
import Utility.DataNotFoundException;

import java.util.Scanner;
public class UserLogin extends Login{
    Scanner scanner = new Scanner(System.in);
    @Override
    public Data generateLogin() {
        System.out.println("User login");
        System.out.println("=================");

        String isNewCustomer = null;
        boolean validInput = false;

        // Loop until valid input is provided
        while (!validInput) {
            System.out.println("Are you a new customer? (yes/no)");
            isNewCustomer = scanner.nextLine().trim().toLowerCase();

            // Check if input is either "yes" or "no"
            if (isNewCustomer.equals("yes") || isNewCustomer.equals("no")) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        User currentUser = null;

        if (isNewCustomer.equals("yes")) {
            System.out.println("Please enter your name:");
            String userName = scanner.nextLine();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine();
            System.out.println("Please enter your age:");
            int age = scanner.nextInt();

            try {
                // Check if the customer exists in the database
                currentUser = DataBase.Instance.getUserByCredentials(userName, password);
                System.out.println("Customer already exists in the database.");
            } catch (DataNotFoundException e) {
                System.out.println("New customer detected. Creating a new account...");
                // If customer doesn't exist, create a new customer entry in the database
                DataBase.Instance.addUser(new User(userName, password, age));
                System.out.println("New account created successfully!");
                currentUser = new User(userName, password, age);
            }
        } else if (isNewCustomer.equals("no")) {
            boolean loginSuccessful = false;
            while (!loginSuccessful) {
                try {
                    System.out.println("Enter existing username: ");
                    String userName = scanner.nextLine();
                    System.out.println("\nEnter existing password: ");
                    String userPass = scanner.nextLine();
                    currentUser = DataBase.Instance.getUserByCredentials(userName, userPass);
                    loginSuccessful = true; // If login succeeds, exit the loop
                } catch (DataNotFoundException e) {
                    System.err.println("Incorrect username or password. Please try again. \n Enter existing username: ");
                }
            }
        }

        return currentUser;
    }
}
