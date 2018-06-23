package version1;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private final String name;
    private final Output output;
    private final List<Rental> rentals = new ArrayList<>();

    Customer(Output output, String name) {
        this.output = output;
        this.name = name;
    }

    void addRental(Rental rental) {
        rentals.add(rental);
    }

    String statement() {
        return output.print(rentals, getName(), getTotalCharge(), getTotalFrequentRenterPoints());
    }

    private String getName() {
        return name;
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
    }

    private double getTotalCharge() {
        return rentals.stream().mapToDouble(Rental::getCharge).sum();
    }
}