package version1;

class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getAmount(int daysRented) {
        return 0;
    }
}