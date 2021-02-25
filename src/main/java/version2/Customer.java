package version2;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private final String customerName;

    private final Vector<Rental> moviesRented = new Vector<>();

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental rentalMovie) {
        moviesRented.addElement(rentalMovie);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration<Rental> moviesRented = this.moviesRented.elements();
        StringBuilder result = new StringBuilder("Rental Record for " + this.customerName + "\n");

        while (moviesRented.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = moviesRented.nextElement();

            // determines the amount for each line
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

            frequentRenterPoints++;

            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        result.append("You owed ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

        return result.toString();
    }

    public String getName() { // Want to get rid, but maybe part of api
        return this.customerName;
    }
}
