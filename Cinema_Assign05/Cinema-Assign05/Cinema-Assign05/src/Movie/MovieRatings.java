package Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRatings {
    private Map<String, List<String>> movieRatings;

    public MovieRatings() {
        this.movieRatings = new HashMap<>();
    }

    // Method to add a rating and comment for a movie
    public void addRating(String movie, String rating) {
        if (movieRatings.containsKey(movie)) {
            movieRatings.get(movie).add(rating);
        } else {
            List<String> ratings = new ArrayList<>();
            ratings.add(rating);
            movieRatings.put(movie, ratings);
        }
    }

    // Method to get ratings and comments for a movie
    public List<String> getRatings(String movie) {
        return movieRatings.getOrDefault(movie, new ArrayList<>());
    }


    // Method to display all ratings and comments for all movies
    public void displayAllRatings() {
        if (!movieRatings.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : movieRatings.entrySet()) {
                String movie = entry.getKey();
                List<String> ratings = entry.getValue();
                System.out.println("Movie: " + movie);
                System.out.println("Ratings and Comments:");
                for (String rating : ratings) {
                    System.out.println(rating);
                }
                System.out.println();
            }
        } else {
            System.out.println("You have not rated any movies.");
            System.out.println();
        }
    }


    // Method to remove all ratings and comments for a movie
    public void removeRatings(String movie) {
        movieRatings.remove(movie);
    }
    public boolean movieRatingsIsEmpty() {
        return movieRatings.isEmpty();
    }
}

