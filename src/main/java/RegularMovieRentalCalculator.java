public class RegularMovieRentalCalculator implements MovieRentalCalculator {

    private static final double DISCOUNT_DAYS_RENTED = 2;

    @Override
    public double determineAmount(int daysRented) {
        double total = 2;
        if (daysRented > DISCOUNT_DAYS_RENTED)
            total += (daysRented - 2) * 1.5;
        return total;
    }

    // Unused variable!!!
    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
