package Data;

import java.util.*;
import Movie.*;
import Utility.DataBase;
import LoyaltyPoints.LoyaltySystem;
import Utility.DataNotFoundException;

public class Employee implements Data {
    private String name;
    private String password;

    public Employee(String username, String password){
        this.name = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void generateMenu() {
        Scanner input = new Scanner(System.in);
        int answer = -1;
        while(answer != 0){
            System.out.println("Welcome " + this.name + ", What would you like to do?");
            System.out.println("1. See available movies");
            System.out.println("2. See reserved movies for attendances");
            System.out.println("3. Reward a loyal customer with points");
            System.out.println("4. See all loyal customers rewarded");
            System.out.println("5. View Users Ratings");
            System.out.println("0. Go back");
            try
            {
                answer = input.nextInt();
                input.nextLine(); //Consume newline

                switch (answer){
                    case 0:
                        System.out.println("Going back");
                        return;
                    case 1:
                        this.showAvailableMovies();
                        break;
                    case 2:
                        this.displayReservedMovies();
                        break;
                    case 3:
                        this.rewardLoyalUser();
                        break;
                    case 4:
                        this.displayRewardedUsers();
                        break;
                    case 5:
                        this.viewUserRatings();
                        break;
                    default:
                        System.out.println("Write a valid number");
                        break;
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }
    }

    public void showAvailableMovies(){
        LinkedList<Movie> movies = DataBase.Instance.getMovies();
        System.out.println("Here are all the movies");
        for (Movie movie : movies){
            movie.display();
            System.out.println("=====================");
        }
    }

    public void displayReservedMovies(){
        ArrayList<User> userList = DataBase.Instance.getUsers();
        System.out.println("All reservations:");
        for(User user : userList){
            System.out.println("For user: " + user.getName());
            user.seeCurrentReservations();
            System.out.println("=============");
        }
    }

    public void rewardLoyalUser() {
        Scanner scanner = new Scanner(System.in);
        LoyaltySystem loyaltySystem = DataBase.Instance.getLoyaltySystem();

        System.out.println("Enter the username of the customer to reward loyalty points:");
        String username = scanner.nextLine();

        System.out.println("Enter the number of loyalty points to reward:");
        int points = scanner.nextInt();

        try {
            User user = DataBase.Instance.getUserByName(username); // Get user from database
            loyaltySystem.rewardUser(username, points);
            //rewardedUsers.add(username); // Track rewarded user
            System.out.println("Rewarded " + points + " loyalty points to " + username);
            //loyaltySystem.updateLoyaltyPoints(username,points);
        } catch (DataNotFoundException e) {
            System.out.println("Customer " + username + " not found in the database.");
        }
    }

    public void displayRewardedUsers() {
        LoyaltySystem loyaltySystem1 = DataBase.Instance.getLoyaltySystem();

        if (loyaltySystem1.getLoyaltyPointsMap().isEmpty()) {
            System.out.println("No Customer(s) have been rewarded with loyalty points.");
        } else {
            System.out.println("Customer(s) rewarded with loyalty points:");
            for (String username : loyaltySystem1.getRewardedCusList()) {
                int points = loyaltySystem1.getLoyaltyPoints(username); // Get loyalty points for the customer
                System.out.println(username + ": " + points + " loyalty points");
            }
        }

    }

    public void viewUserRatings() {
        ArrayList<User> users = DataBase.Instance.getUsers();
        System.out.println("User Ratings:");
        for (User user : users) {
            System.out.println("User: " + user.getName());
            user.movieRatings.displayAllRatings();
            System.out.println("=====================");
        }
    }
}
