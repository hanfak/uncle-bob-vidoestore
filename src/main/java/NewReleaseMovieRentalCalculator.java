public class NewReleaseMovieRentalCalculator implements MovieRentalCalculator {
    // TODO extract magic numbers!!!
    private static final double DISCOUNT_DAYS_RENTED = 2;

    @Override
    public double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        if (daysRented > DISCOUNT_DAYS_RENTED) {
            return 2;
        }
        return 1;
    }
}
