package uebung11;

public abstract class Price {

    abstract int getPriceCode();
    abstract double getCharge (int daysRented);
    abstract double getFrequentRenterPoints(int daysRented);

}
