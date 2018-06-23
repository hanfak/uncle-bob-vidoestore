package version1;

public class Movie {
    static final int CHILDRENS = 2;
    static final int REGULAR = 0;
    static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    Movie(String title, Price price) {
        this.title = title;
        this.price = price;
    }

    double getCharge(int daysRented) {
        return price.getAmount(daysRented);
    }

    int getPriceCode() {
        return price.getPriceCode();
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