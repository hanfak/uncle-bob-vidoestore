package version2;

import java.util.*;

public class Customer {

    private final String customerName;

    private final List<Rental> moviesRented = new ArrayList<>();

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental rentalMovie) {
        moviesRented.add(rentalMovie);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> moviesRented = this.moviesRented.iterator();
        StringBuilder customerStatement = new StringBuilder("Rental Record for " + this.customerName + "\n");

        while (moviesRented.hasNext()) {
            Rental rentedMovie = moviesRented.next();

            double thisAmount = calculateAmountOwedForRentedMovie(rentedMovie);

            frequentRenterPoints += calculateFrequentRenterPoints(rentedMovie);

            rentalMovieStatment(customerStatement, rentedMovie, thisAmount);
            totalAmount += thisAmount;
        }


        customerStatement.append("You owed ").append(totalAmount).append("\n");
        customerStatement.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

        return customerStatement.toString();
    }

    private int calculateFrequentRenterPoints(Rental rentedMovie) {
        if (rentedMovie.getMovie().getPriceCode() == Movie.NEW_RELEASE && rentedMovie.getDaysRented() > 1) {
            return 2;
        } else {
             return 1;
        }
    }

    private void rentalMovieStatment(StringBuilder customerStatement, Rental rentedMovie, double thisAmount) {
        customerStatement.append("\t").append(rentedMovie.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
    }

    private double calculateAmountOwedForRentedMovie(Rental rentedMovie) {
        switch (rentedMovie.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                return amountForRegularRental(rentedMovie);
            case Movie.NEW_RELEASE:
                return amountForNewReleaseRental(rentedMovie);
            case Movie.CHILDRENS:
                return amountForChildrensRental(rentedMovie);
            default:
                return 0;
        }
    }

    private double amountForChildrensRental(Rental rentedMovie) {
        double thisAmount = 1.5;
        if (rentedMovie.getDaysRented() > 3) {
            return thisAmount + (rentedMovie.getDaysRented() - 3) * 1.5;
        }
        return thisAmount;
    }

    private int amountForNewReleaseRental(Rental rentedMovie) {
        return rentedMovie.getDaysRented() * 3;
    }

    private double amountForRegularRental(Rental rentedMovie) {
        double thisAmount = 2;
        if (rentedMovie.getDaysRented() > 2) {
            return thisAmount +  (rentedMovie.getDaysRented() - 2) * 1.5;
        }
        return thisAmount;
    }

    public String getName() { // Want to get rid, but maybe part of api
        return this.customerName;
    }
}
