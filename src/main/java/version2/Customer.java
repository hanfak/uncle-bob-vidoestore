package version2;

import java.util.*;

import static java.util.stream.Collectors.*;
import static version2.PriceCode.*;

public class Customer {

    private final String customerName;
    private final Statement statement;

    private final List<Rental> moviesRented = new ArrayList<>();

    public Customer(String customerName, Statement statement) {
        this.customerName = customerName;
        this.statement = statement;
    }

    public void addRental(Rental rentalMovie) {
        moviesRented.add(rentalMovie);
    }

    public String statement() {
        Map<Rental, Double> amountPerRental = this.moviesRented.stream()
                .collect(toMap(
                        x -> x, Rental::calculateAmountOwedForRentedMovie,
                        (o1, o2) -> o1, LinkedHashMap::new));
        double totalAmount = amountPerRental.values().stream().reduce(0.0, Double::sum);
        int frequentRenterPoints = this.moviesRented.stream().mapToInt(this::calculateFrequentRenterPoints).sum();
        return statement.createStatement(totalAmount, frequentRenterPoints, amountPerRental, this);
    }

    public boolean hasRentedAMovie() {
        return moviesRented.size() > 0;
    }

    public String getName() {
        return this.customerName;
    }

    // TODO extract to delegate FrequentRentersPointsService
    private int calculateFrequentRenterPoints(Rental rentedMovie) {
       return rentedMovie.isMovieType(NEW_RELEASE) && rentedMovie.getDaysRented() > 1 ?  2 : 1;
    }
}
