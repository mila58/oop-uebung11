package uebung11;

public class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    double getFrequentRenterPoints(int daysRented) {
        if(daysRented > 1) {
            return 2;
        } else{
            return 1;
        }
    }

}
