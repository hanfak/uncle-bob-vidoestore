package version2;

public class Movie {

    private final String title;
    private final PriceCode priceCode;

    public Movie(String title, PriceCode priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return this.title;
    }

    public double costOfMovie(int daysRented) {
       return priceCode.cost(daysRented);
    }

    public boolean isMovieType(PriceCode priceCode) {
        return this.priceCode.equals(priceCode);
    }
}
