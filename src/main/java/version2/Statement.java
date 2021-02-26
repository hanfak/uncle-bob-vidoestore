package version2;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class Statement {
    String createStatement(double totalAmount, int frequentRenterPoints, Map<Rental, Double> amountPerRental, Customer customer) {
        String singleMovieRentalStatement = amountPerRental.entrySet().stream()
                .map(e -> "\t" + e.getKey().getMovieTitle() + "\t" + e.getValue())
                .collect(joining("\n"));
        final StringBuilder customerStatement = new StringBuilder("Rental Record for " + customer.getName() + "\n")
                .append(singleMovieRentalStatement);
        if (customer.hasRentedAMovie()) customerStatement.append("\n");
        return customerStatement
                .append("You owed ").append(totalAmount).append("\n")
                .append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n")
                .toString();
    }
}
