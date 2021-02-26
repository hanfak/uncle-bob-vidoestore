package version2;

public final class Rental {

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
        return this.movie.costOfMovie(daysRented);
    }

    public boolean isMovieType(PriceCode priceCode) {
        return this.movie.isMovieType(priceCode);
    }
}
