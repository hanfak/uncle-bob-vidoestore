package version1;

public class Movie {
    static final int CHILDRENS = 2;
    static final int REGULAR = 0;
    static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    private void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    double getCharge(int daysRented) {
        double thisAmount = 0;

        switch (getPriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (daysRented > 2) {
                    thisAmount += (daysRented - 2) * 1.5;
                }
                break;
            case NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (daysRented > 3) {
                    thisAmount += (daysRented - 3) * 1.5;
                }
                break;
        }
        return thisAmount;
    }

    int getPriceCode() {
        return priceCode;
    }

    String getTitle() {
        return title;
    }

    int getFrequentRenterPoints(int daysRented) {
        if (getPriceCode() == NEW_RELEASE && daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}