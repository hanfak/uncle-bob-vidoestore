package version1;

class RegularPrice extends Price {
    @Override
    public double getAmount(int daysRented) {
        return 2 + amountAfterDaysRented(daysRented);
    }

    private double amountAfterDaysRented(int daysRented) {
        if (daysRented > 2) {
            return (daysRented - 2) * 1.5;
        }
        return 0;
    }
}
