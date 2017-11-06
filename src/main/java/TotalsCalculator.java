import java.util.List;

public class TotalsCalculator {

    public int updateFrequentRenterPointsAmountOwed(List<Rental> rentals) {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    public double updateTotalAmountOwed(List<Rental> rentals) {
        return rentals.stream()
                .mapToDouble(Rental::calculateTotal)
                .sum();    }
}
