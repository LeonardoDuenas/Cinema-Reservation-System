package Login;

import Utility.LoginType;
import Data.Data;

import java.util.Scanner;
public abstract class Login {
    protected Scanner input = new Scanner(System.in);
    public static Login loginFactory(LoginType type){ //factory design
        Login login = null;
        switch (type){
            case UserLogin:
                login = new UserLogin();
                break;
            case EmpLogin:
                login = new EmpLogin();
                break;
        }
        return login;
    }

    public abstract Data generateLogin();

    public static int displayLogin(){
        Scanner input = new Scanner(System.in);
        int option = -1;

        System.out.println("Welcome to Cinema Ticket Reservation System!");
        System.out.println("1. Login as Customer");
        System.out.println("2. Login as Employee");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");

        try
        {
            option = input.nextInt();
            input.nextLine(); // Consume newline
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

        switch (option) {
            case 0:
                System.out.println("Exiting...");
                return 0;
            case 1:
                Data user = Login.loginFactory(LoginType.UserLogin).generateLogin();
                user.generateMenu();
                return 1;
            case 2:
                Data employee = Login.loginFactory(LoginType.EmpLogin).generateLogin();
                employee.generateMenu();
                return 2;
            default:
                System.out.println("Invalid option! Please choose again.");
                return 4;
        }
    }
}
