package version2;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class RentalsRepository {

    private final List<Rental> moviesRented = new ArrayList<>();

    public void addRental(Rental rental) {
        this.moviesRented.add(rental);
    }

    public Map<Rental, Double> amountPerRental() {
        return this.moviesRented.stream()
                .collect(toMap(
                        x -> x, Rental::calculateAmountOwedForRentedMovie,
                        (o1, o2) -> o1, LinkedHashMap::new));
    }

    public List<Rental> getRentals() {
        return Collections.unmodifiableList(moviesRented);
    }

    public boolean hasNoRentedMovies() {
        return this.moviesRented.size() == 0;
    }
}
