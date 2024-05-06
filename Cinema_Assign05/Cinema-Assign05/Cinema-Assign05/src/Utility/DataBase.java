package Utility;

import java.util.ArrayList;
import java.util.LinkedList;

import Data.*;
import LoyaltyPoints.LoyaltySystem;
import Movie.*;
import Showtimes.*;
import PriceSystem.*;

public class DataBase {
    //Singleton
    public static DataBase Instance = new DataBase();
    private DataBase(){
        InitializeData();
    }


    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Employee> employees = new ArrayList<>();
    private final LinkedList<Movie> movies = new LinkedList<>();
    private final LoyaltySystem loyaltySystem = new LoyaltySystem();

    public void InitializeData(){
        //For employees
        Employee emp1 = new Employee("Employee1", "12345");
        Employee emp2 = new Employee("Emp2", "12345");

        this.employees.add(emp1);
        this.employees.add(emp2);

        //For users
        User user1 = new User("Loe", "1234", 19);
        User user2 = new User("Sarah", "5678", 50);
        User user3 = new User("Jakub", "1234", 25);
        User user4 = new User("Kosar", "5678", 12);
        User user5 = new User("Hazel", "1234", 5);
        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);
        this.users.add(user4);
        this.users.add(user5);

        //For movies
        Movie movie1 = new EngMovies("Coming To America", 2012, AgeRatings.PG13);
        movie1.addShowtime(new Showtime("10:00 am", "12:00 pm", 4,2,13));
        movie1.addShowtime(new Showtime("01:00 pm", "03:00 pm", 5,2,3));
        movie1.addShowtime(new Showtime("02:00 pm", "04:00 pm", 7,1,5));

        Movie movie2 = new FrMovies("Petite Nicholas", 1999, AgeRatings.R);
        movie2.addShowtime(new Showtime("10:00 am", "01:00pm", 9,6,2));
        movie2.addShowtime(new Showtime("09:00 am", "12:00pm", 7,3,2));

        Movie movie3 = new EngMovies("On The Wings Of Love", 2015, AgeRatings.G);
        movie3.addShowtime(new Showtime("04:00 pm", "06:00pm", 2,5,7));
        movie3.addShowtime(new Showtime("08:00 pm", "10:00 pm", 1,7,4));

        Movie movie4 = new EngMovies("The Avengers", 2012, AgeRatings.PG13);
        movie4.addShowtime(new Showtime("01:00 pm", "03:00 pm", 5, 3, 8));
        movie4.addShowtime(new Showtime("04:00 pm", "06:00 pm", 5, 3, 8));

        Movie movie5 = new FrMovies("Amélie", 2001, AgeRatings.PG);
        movie5.addShowtime(new Showtime("10:00 am", "12:00 pm", 3, 1, 6));
        movie5.addShowtime(new Showtime("02:00 pm", "04:00 pm", 3, 1, 6));

        Movie movie6 = new EngMovies("Invisible", 2010, AgeRatings.PG13);
        movie6.addShowtime(new Showtime("05:00 pm", "07:00 pm", 6, 2, 9));
        movie6.addShowtime(new Showtime("08:00 pm", "10:00 pm", 6, 2, 9));

        Movie movie7 = new FrMovies("La Reine", 1995, AgeRatings.R);
        movie7.addShowtime(new Showtime("03:00 pm", "05:00 pm", 8, 4, 3));
        movie7.addShowtime(new Showtime("06:00 pm", "08:00 pm", 8, 4, 3));

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
        movies.add(movie6);
        movies.add(movie7);
    }

    // Method to add a new user
    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByCredentials(String name, String password) throws DataNotFoundException{
        for(User user : users){
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new DataNotFoundException("User has not been found in Database");
    }

    public Employee getEmployeeByCredentials(String name, String password) throws DataNotFoundException{
        for(Employee employee : employees){
            if(employee.getName().equals(name) && employee.getPassword().equals(password)){
                return employee;
            }
        }
        throw new DataNotFoundException("Employee has not been found in Database");
    }

    //To be used only by the employee
    public ArrayList<User> getUsers(){
        return this.users;
    }

    public User getUserByName(String name) throws DataNotFoundException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new DataNotFoundException("User '" + name + "' not found in the database.");
    }
    public LinkedList<Movie> getMovies(){
        return this.movies;
    }


    private void displayMovieByAge(AgeRatings ageRating){
        for (Movie movie: movies){
            if(movie.getAgeRating() == ageRating){
                System.out.println("Language: " + (movie instanceof EngMovies ? "English" : "French"));
                System.out.println(movie.getTitle() + " - " + movie.getYear());
                System.out.println("Age rating: " + movie.getAgeRating());
                System.out.println("=========================");
            }
        }
    }

    //Recommendation By Age
    public void recommendByAge(User user){
        int userAge = user.getAge();

        System.out.println("Recommended for you: \n");

        if(userAge < 13){
            //return movie of PG
            displayMovieByAge(AgeRatings.PG);
        }

        if(userAge > 13 && userAge < 17){
            //return movie PG13
            displayMovieByAge(AgeRatings.PG13);
        }

        if(userAge >= 17){
            //return R and NC17
            displayMovieByAge(AgeRatings.R);
            displayMovieByAge(AgeRatings.RC17);
        }

        displayMovieByAge(AgeRatings.G);
    }

    public double getDiscountedPriceForMovie(Movie movie, PriceReductionCode code) {
        return movie.getDiscountedPrice(code);
    }

    public LoyaltySystem getLoyaltySystem() {
        return loyaltySystem;
    }
}
