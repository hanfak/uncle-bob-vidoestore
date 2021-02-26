package version2;

public enum PriceCode {
    CHILDRENS(2) {
        @Override
        double cost(int daysRented) {
            return 1.5 + amountForChildrensRentalAfterDaysRented(daysRented);
        }

        private double amountForChildrensRentalAfterDaysRented(int daysRented) {
            if (daysRented > 3) {
                return (daysRented - 3) * 1.5;
            }
            return 0;
        }
    },
    REGULAR(0) {
        @Override
        double cost(int daysRented) {
            return 2 + amountForRegularRentalAfterDaysRented(daysRented);
        }

        private double amountForRegularRentalAfterDaysRented(double daysRented) {
            if (daysRented > 2) {
                return (daysRented - 2) * 1.5;
            }
            return 0;
        }
    },
    NEW_RELEASE(1) {
        @Override
        double cost(int daysRented) {
            return daysRented * 3;
        }
    };

    private final int code;

    PriceCode(int code) {
        this.code = code;
    }

    abstract double cost(int daysRented);
}
