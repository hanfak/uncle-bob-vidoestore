package version1;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    void addRental(Rental rental) {
        rentals.add(rental);
    }

    private String getName() {
        return name;
    }

    // TODO extract to dependecy, and can call html or text printer, pass rentals, getTotals
    @SuppressWarnings("StringConcatenationInLoop") // Small amount no performance issues affected
    String statement() {
        String result = "Rental Record for " + getName() + "\n";

        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t"
                    + String.valueOf(rental.getCharge()) + "\n";
        }

        result += "Amount owed " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) +
                " frequent renter points\n";

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
    }

    private double getTotalCharge() {
        return rentals.stream().mapToDouble(Rental::getCharge).sum();
    }
}