import java.util.Date;

public class Product {
    private String naam;
    private double prijs;
    private Date houdbaarheidsdatum;

    public Product (String naam, double prijs, Date houdbaarheidsdatum) {
        this.naam = naam;
        this.prijs = prijs;
        this.houdbaarheidsdatum = houdbaarheidsdatum;
    }

    public String getNaam () {
        return naam;
    }

    public double getPrijs () {
        return prijs;
    }

    public Date getHoudbaarheidsdatum () {
        return houdbaarheidsdatum;
    }
}
