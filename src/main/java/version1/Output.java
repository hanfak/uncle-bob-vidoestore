package version1;

import java.util.List;

class Output {

    @SuppressWarnings("StringConcatenationInLoop")
    String print(List<Rental> rentals, String name, double totalCharge, int totalFrequentRenterPoints) {
        String result = statementHeaderToPrint(name);

        for (Rental rental : rentals) {
            result += costOfRentalToPrint(rental);
        }

        result += amountOwedToPrint(totalCharge);
        result += totalFrequentRentersPointToPrint(totalFrequentRenterPoints);

        return result;
    }

    private String statementHeaderToPrint(String name) {
        return "Rental Record for " + name + "\n";
    }

    private String costOfRentalToPrint(Rental rental) {
        return "\t" + rental.getMovieTitle() + "\t"
                + String.valueOf(rental.getCharge()) + "\n";
    }

    private String amountOwedToPrint(double totalCharge) {
        return "Amount owed " + String.valueOf(totalCharge) + "\n";
    }

    private String totalFrequentRentersPointToPrint(int totalFrequentRenterPoints) {
        return "You earned " + String.valueOf(totalFrequentRenterPoints) +
                " frequent renter points\n";
    }
}
