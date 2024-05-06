package Movie;

import Showtimes.Showtime;
import Utility.AgeRatings;
import PriceSystem.*;
import java.lang.reflect.Array;
import java.util.Random;

public abstract class Movie {
    private String title;
    private int year;
    private Showtime[] showtimes;
    private int showtimeCounter = 0;
    private AgeRatings ageRating;
    private double price; // Add price attribute

    public Movie(String title, int year, AgeRatings ageRating){
        this.title = title;
        this.year = year;
        this.showtimes = new Showtime[7];
        // (static size), so now there cannot be more than 7 show per movie in a day I guess
        this.ageRating = ageRating;
        this.price = generateRandomPrice();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Showtime[] getShowtimes() {
        return showtimes;
    }

    public void addShowtime(Showtime showtime) {
        if(showtimeCounter < showtimes.length) {
            this.showtimes[this.showtimeCounter] = showtime;
            this.showtimeCounter++;
        } else {
            System.out.println("No more showtimes for today");
        }
    }

    public void display(){
        System.out.println(this.title + ", " + this.year + ":\n");

        for (int i = 0; i < this.showtimeCounter; i++){
            this.showtimes[i].displayShowtime();
            System.out.println("--------------\n");
        }
    }

    public AgeRatings getAgeRating() {
        return ageRating;
    }

    public double getPrice() {
        return price;
    }
    // Generate random prices for the movie
    public double generateRandomPrice() {
        Random random = new Random();
        price = 5.0 + (20.0 - 5.0) * random.nextDouble(); // Adjust the range of prices as needed
        //price formal to have two decimal places
        price = Double.parseDouble(String.format("%.2f", price));
        return price;
    }

    // Calculate discounted price based on the provided reduction code
    public double getDiscountedPrice(PriceReductionCode code) {
        return PriceReduction.calculateDiscountedPrice(this.price, code);
    }
    abstract void showMovie();
}
