package version1;

abstract class Price {
    abstract double getAmount(int daysRented);
    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
