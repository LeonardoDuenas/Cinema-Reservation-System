package Movie;

import Utility.AgeRatings;

public class EngMovies extends Movie{
    String type = "English Movies";

    public EngMovies(String title, int year, AgeRatings ageRating) {
        super(title, year, ageRating);
    }

    @Override
    void showMovie() {
        System.out.println("The movie is " + this.type);
    }

    public void display(){
        showMovie();
        super.display();
    }
}
