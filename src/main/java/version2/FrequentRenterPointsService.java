package version2;

import java.util.List;

import static version2.PriceCode.NEW_RELEASE;

public class FrequentRenterPointsService {

    public int calculatePointsForAllRentals(List<Rental> rentals) {
        return rentals.stream().mapToInt(this::calculateFrequentRenterPoints).sum();
    }

    private int calculateFrequentRenterPoints(Rental rentedMovie) {
        return rentedMovie.isMovieType(NEW_RELEASE) && rentedMovie.getDaysRented() > 1 ?  2 : 1;
    }
}
