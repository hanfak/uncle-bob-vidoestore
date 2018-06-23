package version1;

class Rental {
    private Movie movie;
    private int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    int getFrequentRenterPoints() {
        if (getMovie().getPriceCode() == Movie.NEW_RELEASE && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    Movie getMovie() {
        return movie;
    }

    double getCharge() {
        return movie.getCharge(getDaysRented());
    }

    private int getDaysRented() {
        return daysRented;
    }
}