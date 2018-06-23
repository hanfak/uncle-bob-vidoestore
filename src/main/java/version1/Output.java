package version1;

import java.util.List;

abstract class Output {

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

    abstract String statementHeaderToPrint(String name);
    abstract String costOfRentalToPrint(Rental rental);
    abstract String amountOwedToPrint(double totalCharge);
    abstract String totalFrequentRentersPointToPrint(int totalFrequentRenterPoints);
}
