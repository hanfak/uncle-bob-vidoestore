package version1;

public class StringOutput extends Output {

     String statementHeaderToPrint(String name) {
        return "Rental Record for " + name + "\n";
    }

     String costOfRentalToPrint(Rental rental) {
        return "\t" + rental.getMovieTitle() + "\t"
                + String.valueOf(rental.getCharge()) + "\n";
    }

     String amountOwedToPrint(double totalCharge) {
        return "Amount owed " + String.valueOf(totalCharge) + "\n";
    }

     String totalFrequentRentersPointToPrint(int totalFrequentRenterPoints) {
        return "You earned " + String.valueOf(totalFrequentRenterPoints) +
                " frequent renter points\n";
    }
}
