
import java.util.ArrayList;
import java.util.List;

public class RentalStore {

	private final String name;
	private final StatementPresenter statementPresenter;
	private final TotalsCalculator totalsCalculator;

	private final List<Rental> rentals = new ArrayList<>();

	public RentalStore(String name, StatementPresenter statementPresenter, TotalsCalculator totalsCalculator) {
		this.name = name;
		this.statementPresenter = statementPresenter;
		this.totalsCalculator = totalsCalculator;
	}
	
	public void addRental(Rental rental) {
		rentals.add(rental);
	}
	
	public String createStatement() {
		checkNameExists();
		checkRentalsExists();
		return statementPresenter.outputer(name, rentals, calculateTotalOwed(), calculateFrequentRenterPoints());
	}

	private void checkRentalsExists() {
		if (rentals.isEmpty())
			throw new IllegalStateException("Must have rentals");
	}

	private void checkNameExists() {
		if (name.length() <= 0)
			throw new IllegalArgumentException("Name should exist");
	}

	// Extract out
	public double calculateTotalOwed() {
		return totalsCalculator.updateTotalAmountOwed(rentals);
	}

	public int calculateFrequentRenterPoints() {
		return totalsCalculator.updateFrequentRenterPointsAmountOwed(rentals);
	}
}