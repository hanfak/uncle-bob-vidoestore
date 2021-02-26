package version2;

import java.util.Map;

import static version2.PriceCode.NEW_RELEASE;

public class Customer {

    private final String customerName;
    private final Statement statement;
    private final RentalsRepository rentalsRepository;

    public Customer(String customerName, Statement statement, RentalsRepository rentalsRepository) {
        this.customerName = customerName;
        this.statement = statement;
        this.rentalsRepository = rentalsRepository;
    }

    public void addRental(Rental rentalMovie) {
        this.rentalsRepository.addRental(rentalMovie);
    }

    public String statement() {
        Map<Rental, Double> amountPerRental = this.rentalsRepository.amountPerRental();
        double totalAmount = amountPerRental.values().stream().reduce(0.0, Double::sum);
        int frequentRenterPoints = this.rentalsRepository.getRentals().stream().mapToInt(this::calculateFrequentRenterPoints).sum();
        return statement.createStatement(totalAmount, frequentRenterPoints, amountPerRental, this);
    }

    public boolean hasRentedAMovie() {
        return this.rentalsRepository.hasRentedAMovie();
    }

    public String getName() {
        return this.customerName;
    }

    // TODO extract to delegate FrequentRentersPointsService
    private int calculateFrequentRenterPoints(Rental rentedMovie) {
       return rentedMovie.isMovieType(NEW_RELEASE) && rentedMovie.getDaysRented() > 1 ?  2 : 1;
    }
}
