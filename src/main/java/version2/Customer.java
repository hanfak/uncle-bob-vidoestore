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
        StringBuilder customerStatement = new StringBuilder("Rental Record for " + this.customerName + "\n");

        while (moviesRented.hasMoreElements()) {
            double thisAmount = 0;
            Rental rentedMovie = moviesRented.nextElement();

            // determines the amount for each line
            switch (rentedMovie.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rentedMovie.getDaysRented() > 2) {
                        thisAmount += (rentedMovie.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rentedMovie.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rentedMovie.getDaysRented() > 3) {
                        thisAmount += (rentedMovie.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

            frequentRenterPoints++;

            if (rentedMovie.getMovie().getPriceCode() == Movie.NEW_RELEASE && rentedMovie.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            customerStatement.append("\t").append(rentedMovie.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        customerStatement.append("You owed ").append(totalAmount).append("\n");
        customerStatement.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

        return customerStatement.toString();
    }

    public String getName() { // Want to get rid, but maybe part of api
        return this.customerName;
    }
}
