import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class VideoStoreHappyPathTest implements WithAssertions {

	@Test
	public void shouldCalculateTotalAndFrequentRenterPointsForSingleNewReleaseMovieRental() {
		rentalStore.addRental(rentalForNewReleaseMovieOne);

		rentalStore.createStatement();

		assertThat(rentalStore.calculateTotalOwed()).isEqualTo(3.0);
		assertThat(rentalStore.calculateFrequentRenterPoints()).isEqualTo(1);
	}

	@Test
	public void shouldCalculateTotalAndFrequentRenterPointsForMultipleNewReleaseMovieRental() {
		rentalStore.addRental(rentalForNewReleaseMovieOne);
		rentalStore.addRental(rentalForNewReleaseMovieTwo);

		rentalStore.createStatement();

		assertThatTotalOwedAndFrequentRenterPointsIs(12.0, 3);
		assertThat(rentalStore.calculateTotalOwed()).isEqualTo(12.0);
		assertThat(rentalStore.calculateFrequentRenterPoints()).isEqualTo(3);
	}

	@Test
	public void shouldCalculateTotalAndFrequentRenterPointsForSingleChildrenMovieRental() {
		rentalStore.addRental(rentalForChildrenMovieOne);
		rentalStore.addRental(rentalForChildrenMovieTwo);

		rentalStore.createStatement();

		assertThat(rentalStore.calculateTotalOwed()).isEqualTo(4.5);
		assertThat(rentalStore.calculateFrequentRenterPoints()).isEqualTo(2);
	}

	@Test
	public void shouldCalculateTotalAndFrequentRenterPointsForMultipleRegularMovieRental() {
		rentalStore.addRental(rentalForRegularMovieOne);
		rentalStore.addRental(rentalForRegularMovieTwo);
		rentalStore.addRental(rentalForRegularMovieThree);

		rentalStore.createStatement();

		assertThat(rentalStore.calculateTotalOwed()).isEqualTo(7.5);
		assertThat(rentalStore.calculateFrequentRenterPoints()).isEqualTo(3);
	}

	@Test
	public void shouldOutputStatementForMultipleRegularMovieRentals() {
		rentalStore.addRental(rentalForRegularMovieOne);
		rentalStore.addRental(rentalForChildrenMovieOne);
		rentalStore.addRental(rentalForNewReleaseMovieOne);

		String regularStatement = rentalStore.createStatement();

		String expectedRegularStatement =
				"Rental Record for A Customer\n" +
						"\tPlan 9 from Outer Space\t2.0\n" +
						"\tThe Tigger Movie\t1.5\n" +
						"\tThe Cell\t3.0\n" +
						"You owed 6.5\n" +
						"You earned 3 frequent renter points\n";
		assertThat(regularStatement).isEqualTo(expectedRegularStatement);
	}

	// Todo test null or edge case inputs sad path

	private void assertThatTotalOwedAndFrequentRenterPointsIs(double totalOwed, int frequentRenterPoints) {
		assertThat(rentalStore.calculateTotalOwed()).isEqualTo(totalOwed);
		assertThat(rentalStore.calculateFrequentRenterPoints()).isEqualTo(frequentRenterPoints);
	}

	private final StatementPresenter statementPresenter = new StatementPresenter();
	private final TotalsCalculator totalsCalculator = new TotalsCalculator();

	private final MovieRentalCalculator regularMovieCalculator = new RegularMovieRentalCalculator();
	private final MovieRentalCalculator childrenMovieCalculator = new ChildrenMovieRentalCalculator();
	private final MovieRentalCalculator newReleaseMovieCalculator = new NewReleaseMovieRentalCalculator();

	private final Movie regularMovieOne = new Movie ("Plan 9 from Outer Space");
	private final Movie regularMovieTwo = new Movie ("8 1/2");
	private final Movie regularMovieThree = new Movie ("Eraserhead");
	private final Movie childrenMovieOne = new Movie ("The Tigger Movie");
	private final Movie newReleaseMovieOne = new Movie ("The Cell");
	private final Movie newReleaseMovieTwo = new Movie ("The Tigger Movie");

	private final Rental rentalForRegularMovieOne = new Rental(regularMovieOne, 1, regularMovieCalculator);
	private final Rental rentalForRegularMovieTwo = new Rental(regularMovieTwo, 2, regularMovieCalculator);
	private final Rental rentalForRegularMovieThree = new Rental(regularMovieThree, 3, regularMovieCalculator);
	private final Rental rentalForChildrenMovieOne = new Rental(childrenMovieOne, 3, childrenMovieCalculator);
	private final Rental rentalForChildrenMovieTwo = new Rental(childrenMovieOne, 4, childrenMovieCalculator);
	private final Rental rentalForNewReleaseMovieOne = new Rental(newReleaseMovieOne, 1, newReleaseMovieCalculator);
	private final Rental rentalForNewReleaseMovieTwo = new Rental(newReleaseMovieTwo, 3, newReleaseMovieCalculator);

	private final RentalStore rentalStore = new RentalStore("A Customer", statementPresenter, totalsCalculator);
}