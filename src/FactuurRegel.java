public class FactuurRegel {
    private Product product;
    private int aantal;

    public FactuurRegel (String productnaam, double productprijs, int aantal) {
        product = new Product (productnaam, productprijs);
        this.aantal = aantal;
    }

    public String toString () {
        double totaalprijs = aantal * product.getPrijs();
        return String.format("%4d %40s  %8.2f  %8.2f", aantal, product.getNaam(), product.getPrijs (), totaalprijs);
    }

    public double getTotaalprijs () {
        return aantal * product.getPrijs ();
    }
}
