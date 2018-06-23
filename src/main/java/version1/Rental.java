package version1;

class Rental {
    private Movie movie;
    private int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(getDaysRented());
    }

    String getMovieTitle() {
        return movie.getTitle();
    }

    double getCharge() {
        return movie.getCharge(getDaysRented());
    }

    private int getDaysRented() {
        return daysRented;
    }
}