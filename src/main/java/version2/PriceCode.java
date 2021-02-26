package version2;

//tODO use interface with each enum being a impl
public enum PriceCode {
    CHILDRENS {
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
    REGULAR {
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
    NEW_RELEASE {
        @Override
        double cost(int daysRented) {
            return daysRented * 3;
        }
    };

    abstract double cost(int daysRented);
}
