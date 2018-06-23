package version1;

class ChildrenPrice extends Price {
    @Override
    public double getAmount(int daysRented) {
        return 1.5 + amountAfterDaysRented(daysRented);
    }

    private double amountAfterDaysRented(int daysRented) {
        if (daysRented > 3) {
            return (daysRented - 3) * 1.5;
        }
        return 0;
    }
}