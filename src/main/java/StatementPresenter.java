import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementPresenter {
    // TODO Change name
    public String outputer(String name, List<Rental> rentals, double total, int frequentRenterPoints) {
        return writeHeader(name) + writeRentals(rentals) + writeFooter(total, frequentRenterPoints);
    }

    private String writeHeader(String name) {
        return "Rental Record for " + name + "\n";
    }

    private String writeRentals(List<Rental> rentals) {
        return rentals.stream()
                .map(this::writeSingleRental)
                .collect(joining("\n"));
    }

    private String writeSingleRental(Rental rental) {
        return "\t" + rental.getTitle() + "\t" + rental.calculateTotal();
    }

    private String writeFooter(double total, int frequentRenterPoints) {
        return "\n" + "You owed " + total + "\n" +
                "You earned " + frequentRenterPoints + " frequent renter points\n";
    }
}
