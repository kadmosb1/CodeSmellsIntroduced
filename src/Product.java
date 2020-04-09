public class Product {
    private String naam;
    private double prijs;

    public Product (String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    public String getNaam () {
        return naam;
    }

    public double getPrijs () {
        return prijs;
    }
}
