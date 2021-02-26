package version2;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class Statement {

    String createStatement(double totalAmount, int frequentRenterPoints, String name, Set<Map.Entry<Rental, Double>> entries) {
        final StringBuilder customerStatement = new StringBuilder("Rental Record for " + name + "\n")
                .append(singleMovieRentalStatement(entries))
                .append("\n");
        return customerStatement
                .append("You owed ").append(totalAmount).append("\n")
                .append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n")
                .toString();
    }

    public String createEmptyStatement(String name) {
        return String.format("Rental Record for %s\nYou owed 0.0\nYou earned 0 frequent renter points\n", name);
    }

    private String singleMovieRentalStatement(Set<Map.Entry<Rental, Double>> entries) {
        return entries.stream()
                .map(e -> "\t" + e.getKey().getMovieTitle() + "\t" + e.getValue())
                .collect(joining("\n"));
    }
}
