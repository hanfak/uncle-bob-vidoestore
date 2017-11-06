public class ChildrenMovieRentalCalculator implements MovieRentalCalculator {

    private static final double DISCOUNT_DAYS_RENTED = 3;

    @Override
    public double determineAmount(int daysRented) {
        double total = 1.5;
        if (daysRented > DISCOUNT_DAYS_RENTED)
            total += (daysRented - 3) * 1.5;
        return total;
    }

    // Unused variable!!!
    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
