package uebung11;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    private Movie THE_HULK;
    private Movie IRON_MAN;
    private Movie SPIDER_MAN;

    private Customer customer;

    @Before
    public void initCustomer() {
        customer = new Customer("fred");

        THE_HULK = new Movie("The Hulk", Movie.CHILDREN);
        IRON_MAN = new Movie("Iron Man 4", Movie.NEW_RELEASE);
        SPIDER_MAN = new Movie("Spiderman", Movie.REGULAR);
    }

    @Test
    public void basicChildrenRental() {
        customer.addRental(new Rental(THE_HULK, 2));
        Assert.assertEquals(customer.statement(), expectedMessageFor("The Hulk", 1.5, 1.5, 1));
    }

    @Test
    public void shouldDiscountChildrensRentals() {
        customer.addRental(new Rental(THE_HULK, 4));
        Assert.assertEquals(customer.statement(), expectedMessageFor("The Hulk", 3.0, 3.0, 1));
    }

    @Test
    public void basicNewReleaseRental() {
        customer.addRental(new Rental(IRON_MAN, 1));
        Assert.assertEquals(customer.statement(), expectedMessageFor("Iron Man 4", 3.0, 3.0, 1));
    }

    @Test
    public void shouldNotDiscountNewReleaseRentalsButBonusFrequentRenterPoints() {
        customer.addRental(new Rental(IRON_MAN, 4));
        Assert.assertEquals(customer.statement(), expectedMessageFor("Iron Man 4", 12.0, 12.0, 2));
    }

    @Test
    public void basicRegularRental() {
        customer.addRental(new Rental(SPIDER_MAN, 2));
        Assert.assertEquals(customer.statement(), expectedMessageFor("Spiderman", 2.0, 2.0, 1));
    }

    @Test
    public void shouldDiscountRegularRental() {
        customer.addRental(new Rental(SPIDER_MAN, 4));
        Assert.assertEquals(customer.statement(), expectedMessageFor("Spiderman", 5.0, 5.0, 1));
    }

    @Test
    public void shouldSumVariousRentals() {
        customer.addRental(new Rental(THE_HULK, 2));
        customer.addRental(new Rental(SPIDER_MAN, 1));
        customer.addRental(new Rental(IRON_MAN, 3));
        Assert.assertEquals(customer.statement(), "Rental record for fred\n\tThe Hulk\t1.5\n\tSpiderman\t2.0\n\tIron Man 4\t9.0\nAmount owed is 12.5\nYou earned 4 frequent renter points");
    }

    private static String expectedMessageFor(String rental, double price, double total, int renterPointsEarned) {
        return "Rental record for fred\n\t" + rental + "\t" + price + "\nAmount owed is " + total + "\nYou earned " + renterPointsEarned + " frequent renter points";
    }

}
