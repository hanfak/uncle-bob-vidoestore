package version2;

import java.util.*;

import static java.util.stream.Collectors.*;
import static version2.PriceCode.*;

public class Customer {

    private final String customerName;

    private final List<Rental> moviesRented = new ArrayList<>();

    public Customer(String customerName) {
        this.customerName = customerName;
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
        return createStatement(totalAmount, frequentRenterPoints, amountPerRental);
    }

    public String getName() { // Want to get rid, but maybe part of api
        return this.customerName;
    }

    private int calculateFrequentRenterPoints(Rental rentedMovie) {
        if (rentedMovie.getMovie().getPriceCode() == PriceCode.NEW_RELEASE && rentedMovie.getDaysRented() > 1) {
            return 2;
        }
        return 1;
    }

    // TODO extract to delegate
    private String createStatement(double totalAmount, int frequentRenterPoints, Map<Rental, Double> amountPerRental) {
        String singleMovieRentalStatement = amountPerRental.entrySet().stream()
                .map(e -> "\t" + e.getKey().getMovie().getTitle() + "\t" + e.getValue())
                .collect(joining("\n"));
        final StringBuilder customerStatement = new StringBuilder("Rental Record for " + this.customerName + "\n")
                .append(singleMovieRentalStatement);
        if (moviesRented.size() > 0) customerStatement.append("\n");
        return customerStatement
                .append("You owed ").append(totalAmount).append("\n")
                .append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n")
                .toString();
    }

}
