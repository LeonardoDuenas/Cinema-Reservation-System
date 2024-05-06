package Reservations;

import Showtimes.Showtime;
import Movie.Movie;
import Data.User;
public class Reservation {
    private User customerName;
    private Movie movie;
    private Showtime showtime;
    public Reservation(User name, Movie movie, Showtime showtime ){
        this.customerName = name;
        this.movie = movie;
        this.showtime = showtime;
    }
    public Movie getMovie() {
        return movie;
    }
    public Showtime getShowtime() {
        return showtime;
    }
    public User getCustomerName() {
        return customerName;
    }
}
