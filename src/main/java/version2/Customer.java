package version2;

import java.util.Map;

public class Customer {

    private final String customerName;
    private final Statement statement;
    private final RentalsRepository rentalsRepository;
    private final FrequentRenterPointsService frequentRenterPointsService;

    public Customer(String customerName, Statement statement, RentalsRepository rentalsRepository, FrequentRenterPointsService frequentRenterPointsService) {
        this.customerName = customerName;
        this.statement = statement;
        this.rentalsRepository = rentalsRepository;
        this.frequentRenterPointsService = frequentRenterPointsService;
    }

    public void addRental(Rental rentalMovie) {
        this.rentalsRepository.addRental(rentalMovie);
    }

    public String statement() {
        Map<Rental, Double> amountPerRental = this.rentalsRepository.amountPerRental();
        double totalAmount = amountPerRental.values().stream().reduce(0.0, Double::sum);
        int frequentRenterPoints = this.frequentRenterPointsService.calculatePointsForAllRentals(this.rentalsRepository.getRentals());
        return statement.createStatement(totalAmount, frequentRenterPoints, this.customerName, this.rentalsRepository.hasRentedAMovie(), amountPerRental.entrySet());
    }

    public String getName() {
        return this.customerName;
    }
}
