import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class VideoStoreSadPathTest implements WithAssertions {

	// Todo test null or edge case inputs sad path
	@Test
	// Should this be a special string statement returned
	public void shouldThrowExceptionIfNoRentalsAdded() throws Exception {
		assertThatThrownBy(rentalStore::createStatement)
			.hasMessage("Must have rentals")
			.isInstanceOf(IllegalStateException.class);
		}


	@Test
	public void customerNameShouldExist() throws Exception {
		String emptyName = "";

		RentalStore rentalStore = new RentalStore(emptyName, statementPresenter, totalsCalculator);

		assertThatThrownBy(rentalStore::createStatement)
				.hasMessage("Name should exist")
				.isInstanceOf(IllegalArgumentException.class);

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