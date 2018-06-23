package version1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {
    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Cell", new NewReleasePrice()), 3));
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\nAmount owed 9.0\nYou earned 2 frequent renter points\n", customer.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Cell", new NewReleasePrice()), 3));
        customer.addRental(new Rental(new Movie("The Tigger Movie", new NewReleasePrice()), 3));
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nAmount owed 18.0\nYou earned 4 frequent renter points\n", customer.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
        customer.addRental(new Rental(new Movie("The Tigger Movie", new ChildrenPrice()), 3));
        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nAmount owed 1.5\nYou earned 1 frequent renter points\n", customer.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(new Movie("Plan 9 from Outer Space", new RegularPrice()), 1));
        customer.addRental(new Rental(new Movie("8 1/2", new RegularPrice()), 2));
        customer.addRental(new Rental(new Movie("Eraserhead", new RegularPrice()), 3));

        assertEquals("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nAmount owed 7.5\nYou earned 3 frequent renter points\n", customer.statement());
    }

    @Before
    public void setUp() {
        customer = new Customer(new Output(), "Fred");
    }

    private Customer customer;
}