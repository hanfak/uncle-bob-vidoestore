package version2;

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

    public String getMovieTitle() {
        return this.movie.getTitle();
    }

    public double calculateAmountOwedForRentedMovie() {
        return movie.costOfMovie(daysRented);
    }

    public boolean isMovieType(PriceCode priceCode) {
        return movie.isMovieType(priceCode);
    }
}
