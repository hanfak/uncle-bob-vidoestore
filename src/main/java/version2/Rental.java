package version2;

import static version2.PriceCode.*;

public class Rental {

    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return this.daysRented;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public double calculateAmountOwedForRentedMovie() {
        return movie.getPriceCode().cost(daysRented);
    }
}
