package Movie;

import Utility.AgeRatings;

public class FrMovies extends Movie{
    String type = "French Movies";

    public FrMovies(String title, int year, AgeRatings ageRating) {
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
