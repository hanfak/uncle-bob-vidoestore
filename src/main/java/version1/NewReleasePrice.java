package version1;

class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getAmount(int daysRented) {
        return 0;
    }
}