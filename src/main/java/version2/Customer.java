package version2;

import java.util.*;

import static java.util.stream.Collectors.*;

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
        Map<Rental, Double> amountPerRental = this.moviesRented.stream()
                .collect(toMap(
                        x -> x, this::calculateAmountOwedForRentedMovie,
                        (o1, o2) -> o1, LinkedHashMap::new));
        double totalAmount = amountPerRental.values().stream().reduce(0.0, Double::sum);
        int frequentRenterPoints = this.moviesRented.stream().mapToInt(this::calculateFrequentRenterPoints).sum();
        return createStatement(totalAmount, frequentRenterPoints, amountPerRental);
    }

    public String getName() { // Want to get rid, but maybe part of api
        return this.customerName;
    }

    // TODO turn Movies into enum, use abstract method for each movie type
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
        return 1.5 + amountForChildrensRentalAfterDaysRented(rentedMovie.getDaysRented());
    }

    private double amountForChildrensRentalAfterDaysRented(int daysRented) {
        if (daysRented > 3) {
            return (daysRented - 3) * 1.5;
        }
        return 0;
    }

    private int amountForNewReleaseRental(Rental rentedMovie) {
        return rentedMovie.getDaysRented() * 3;
    }

    private double amountForRegularRental(Rental rentedMovie) {
        return 2 + amountForRegularRentalAfterDaysRented(rentedMovie.getDaysRented());
    }

    private double amountForRegularRentalAfterDaysRented(int daysRented) {
        if (daysRented > 2) {
            return (daysRented - 2) * 1.5;
        }
        return 0;
    }

    private int calculateFrequentRenterPoints(Rental rentedMovie) {
        if (rentedMovie.getMovie().getPriceCode() == Movie.NEW_RELEASE && rentedMovie.getDaysRented() > 1) {
            return 2;
        }
        return 1;
    }

    // TODO extract to delegate
    private String createStatement(double totalAmount, int frequentRenterPoints, Map<Rental, Double> amountPerRental) {
        String singleMovieRentalStatement = amountPerRental.entrySet().stream()
                .map(e -> "\t" + e.getKey().getMovie().getTitle() + "\t" + e.getValue())
                .collect(joining("\n"));
        final StringBuilder customerStatement = new StringBuilder("Rental Record for " + this.customerName + "\n")
                .append(singleMovieRentalStatement);
        if (moviesRented.size() > 0) customerStatement.append("\n");
        return customerStatement
                .append("You owed ").append(totalAmount).append("\n")
                .append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n")
                .toString();
    }

}
