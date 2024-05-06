package Data;

import Movie.Movie;
import Movie.MovieRatings;
import PriceSystem.PriceReduction;
import PriceSystem.PriceReductionCode;
import Reservations.Reservation;
import Reservations.ReservationSystem;
import Showtimes.Seat;
import Showtimes.Showtime;
import Utility.DataBase;
import LoyaltyPoints.LoyaltySystem;

import java.util.*;
public class User implements Data{
    private String name;
    private String password;
    //Employee emp;
    private int age;
    private final ReservationSystem reservations = new ReservationSystem();
    public final MovieRatings movieRatings = new MovieRatings();
    private final ArrayList<Seat> seats = new ArrayList<>();

    public User(String username, String password, int age){
        this.name = username;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void generateMenu() {
        Scanner input = new Scanner(System.in);
        int answer = -1;
        while(answer != 0){
            System.out.println("\nWelcome " + this.name + " What would you like to do?");
            System.out.println("1. See available movies");
            System.out.println("2. See recommendations");
            System.out.println("3. Reserve a show");
            System.out.println("4. See current reservations");
            System.out.println("5. Add rating to a movie");
            System.out.println("6. Remove rating from a movie");
            System.out.println("7. Get rating for specific movie");
            System.out.println("8. See your ratings");
            System.out.println("9. See all ratings");
            System.out.println("10. See Loyal Points");
            System.out.println("0. Go back");
            try
            {
                answer = input.nextInt();
                input.nextLine();

                switch (answer){
                    case 0:
                        System.out.println("Going back");
                        return;
                    case 1:
                        this.showAvailableMovies();
                        break;
                    case 2:
                        DataBase.Instance.recommendByAge(this);
                        break;
                    case 3:
                        this.reserveShow();
                        break;
                    case 4:
                        this.seeCurrentReservations();
                        break;
                    case 5:
                        this.addRating();
                        break;
                    case 6:
                        this.removeRating();
                        break;
                    case 7:
                        this.getRating();
                        break;
                    case 8:
                        this.movieRatings.displayAllRatings();
                        break;
                    case 9:
                        this.displayUserRatings();
                        break;
                    case 10:
                        this.displayLoyaltyPoints();
                        break;

                    default:
                        System.out.println("Write a valid number");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }
    }

    public void reserveShow(){
        LinkedList<Movie> movies = DataBase.Instance.getMovies();
        Scanner scanner = new Scanner(System.in);

        //Let the user choose a movie
        for(int i = 0; i < movies.size(); i++){
            Movie movie = movies.get(i);
            System.out.println("Movie #" + (i+1));
            System.out.println(movie.getTitle());
            System.out.println(movie.getYear());
            System.out.println("================");
        }
        int movieIndex = -1;
        boolean validMovieIndex = false;
        while (!validMovieIndex) {
            try {
                System.out.println("Enter the index of the movie you want to reserve:");
                movieIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                validMovieIndex = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer for the movie index.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        // Validate movie index
        if (movieIndex < 1 || movieIndex > movies.size()) {
            System.out.println("Invalid movie index. Please enter a valid index.");
            return;
        }

        Movie selectedMovie = movies.get(movieIndex);

        //Show the showtimes
        for (int i = 0; i < selectedMovie.getShowtimes().length; i++) {
            Showtime showtime = selectedMovie.getShowtimes()[i];
            if (showtime != null) {
                System.out.println("Showtime#: " + (i+1));
                showtime.displayShowtime();
                System.out.println("---------------------------");
            }
        }

        // Let the user choose a showtime
        int showtimeIndex = -1;
        boolean validShowtimeIndex = false;
        while (!validShowtimeIndex) {
            try {
                System.out.println("Enter the index of the showtime you want to reserve:");
                showtimeIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                validShowtimeIndex = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer for the showtime index.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        // Validate showtime index
        if (showtimeIndex < 1 || showtimeIndex > selectedMovie.getShowtimes().length) {
            System.out.println("Invalid showtime index. Please enter a valid index.");
            return;
        }

        Showtime selectedShowtime = selectedMovie.getShowtimes()[showtimeIndex - 1];

        /// Show available seats
        selectedShowtime.displaySeats();

        // Let the user choose a seat
        int row = -1;
        int column = -1;
        boolean validSeatSelection = false;
        while (!validSeatSelection) {
            try {
                System.out.println("Enter the row index of the seat you want to reserve:");
                row = scanner.nextInt();
                System.out.println("Enter the column index of the seat you want to reserve:");
                column = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                validSeatSelection = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers for the row and column indices.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        // Reserve the seat
        boolean seatReserved = selectedShowtime.reserveSeat(row, column);
        this.seats.add(new Seat(row, column));
        if (!seatReserved) {
            System.out.println("Seat could not be reserved. Please try again.");
            return;
        }

        double price = selectedMovie.generateRandomPrice();;
        System.out.println("Price for movie " + selectedMovie.getTitle() + ": $" + price);
        // Apply discount code
        System.out.println("Do you have a discount code? (yes/no)");
        String discountOption = scanner.nextLine();
        if (discountOption.equalsIgnoreCase("yes")) {
            boolean validDiscountCode = false;
            while (!validDiscountCode) {
                System.out.println("Enter one of the discount code that applies for you:" +
                        "\n (STUDENT_DISCOUNT, FIRST_NATION_DISCOUNT, EARLY_CUSTOMER_DISCOUNT):");
                String discountCodeStr = scanner.nextLine();
                try {
                    PriceReductionCode discountCode = PriceReductionCode.valueOf(discountCodeStr.toUpperCase());
                    if (PriceReduction.isValidDiscountCode(discountCode)) {
                        double discountPercentage = PriceReduction.getDiscountPercentage(discountCode);
                        double finalPrice = selectedMovie.getDiscountedPrice(discountCode);//DataBase.Instance.getDiscountedPriceForMovie(selectedMovie, discountCode);
                        finalPrice = Double.parseDouble(String.format("%.2f", finalPrice));
                        System.out.println("Discount applied successfully. \nDiscount percentage: " + discountPercentage + "%. \nFinal price: $" + finalPrice);
                        price = Double.parseDouble(String.format("%.2f", finalPrice));
                        validDiscountCode = true;
                    } else {
                        System.out.println("Invalid discount code. Please try again.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid discount code. Please try again.");
                }
            }
        }

        System.out.println("\n --------------RECEIPT--------------");
        //Create a new reservation and add it to the users reservations
        Reservation reservation = new Reservation(this, selectedMovie, selectedShowtime);//,row, column);
        this.reservations.AddToReservationRequest(reservation);

        //Show the reservation
        System.out.println("Reservation confirmed for movie: " + selectedMovie.getTitle() +
                "\n Showtime: " + selectedShowtime.getStartTime() + " - " + selectedShowtime.getEndTime() +
                "\n Hall: " + selectedShowtime.getCinemaHall() +
                "\n Seat: Row: " +  + (row) + ", Column: " + (column)+ //", Column: " + column +
                ". \n Final Price: $" + price + " \n Enjoy your movie!");

    }

    public void seeCurrentReservations(){
        if(!this.reservations.getReservations().isEmpty()) {
            System.out.println("These are your current reservations");
            this.reservations.displayAllReservations();
        } else {
            System.out.println("You have no current reservations");
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

    //Ratings methods
    public void addRating() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie:");
        String movieTitle = scanner.nextLine();

        boolean movieExists = false;
        for (Movie movie : DataBase.Instance.getMovies()) {
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                movieExists = true;
                break;
            }
        }

        if (movieExists) {
            System.out.println("Enter the rating for " + movieTitle);
            String userRating = scanner.nextLine();
            this.movieRatings.addRating(movieTitle, userRating);
            System.out.println("Rating successfully added for movie: " + movieTitle);
        } else {
            System.out.println("The following movie: " + movieTitle + ". Does not exist in the database.");
        }
        System.out.println();
    }

    public void removeRating() {
        Scanner removeScanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie to remove its ratings:");
        String movieToRemove = removeScanner.nextLine();
        List<String> ratings = this.movieRatings.getRatings(movieToRemove);
        if (ratings != null && !ratings.isEmpty()) {
            this.movieRatings.removeRatings(movieToRemove);
            System.out.println("Ratings removed for movie: " + movieToRemove);
        } else {
            System.out.println("The following movie: " + movieToRemove + ". Does not have any ratings.");
        }
        System.out.println();
    }

    public void getRating() {
        Scanner getScanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie to get its ratings:");
        String movieToGetRatings = getScanner.nextLine();
        List<String> ratings = this.movieRatings.getRatings(movieToGetRatings);
        if (!ratings.isEmpty()) {
            System.out.println("Below are the ratings for movie " + movieToGetRatings);
            for (String rating : ratings) {
                System.out.println(rating);
            }
            System.out.println();
        } else {
            System.out.println("No ratings found for movie " + movieToGetRatings );
            System.out.println();
        }
    }
    public void displayUserRatings() {
        ArrayList<User> users = DataBase.Instance.getUsers();
        boolean anyReviewsFound = false;
        for (User user : users) {
            if (!user.movieRatings.movieRatingsIsEmpty()) {
                anyReviewsFound = true;
                user.movieRatings.displayAllRatings();
            }
        }
        if (!anyReviewsFound) {
            System.out.println("No movie ratings found.");
        }
    }
    public void displayLoyaltyPoints() {
        LoyaltySystem loyaltySystem1 = DataBase.Instance.getLoyaltySystem();
        int points = loyaltySystem1.getLoyaltyPoints(this.name);
        System.out.println("Your loyalty points: " + points);
    }

    public ArrayList<Seat> getSeats(){
        return this.seats;
    }
}
