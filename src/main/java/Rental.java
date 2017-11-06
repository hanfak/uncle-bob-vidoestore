public class Rental {

	private final Movie movie;
	private final int daysRented;
	private final MovieRentalCalculator movieRentalCalculator;

	public Rental(Movie movie, int daysRented, MovieRentalCalculator movieRentalCalculator) {
		this.movie = movie;
		this.daysRented = daysRented;
		this.movieRentalCalculator = movieRentalCalculator;
	}

	public double calculateTotal() {
		return movieRentalCalculator.determineAmount(daysRented);
	}

	public int calculateFrequentRenterPoints() {
		return movieRentalCalculator.determineFrequentRenterPoints(daysRented);
	}

	public String getTitle() {
		return movie.getTitle();
	}
}