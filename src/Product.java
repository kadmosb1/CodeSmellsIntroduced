import java.util.Date;

public class Product {
    private String naam;

    /*
     * De eenheidsprijs is de prijs per eenheid:
     * - Bij een gewichtsprijs is dat de prijs per eenheid (bijv. per kg).
     * - Bij een aantal producten per verpakking is dat de prijs per verpakking (bijv. per doos van 6 flessen).
     */
    private double eenheidsprijs;
    private Date houdbaarheidsdatum;
    private int aantalProductenInVerpakking;
    private double gewicht;
    private String eenheid;

    public Product (String naam, double prijsPerStukOfKilo, Date houdbaarheidsdatum) {
        this.naam = naam;
        this.eenheidsprijs = prijsPerStukOfKilo;
        this.houdbaarheidsdatum = houdbaarheidsdatum;
        this.aantalProductenInVerpakking = 1;
        this.gewicht = 0.0;
    }

    public String getNaam () {
        return naam;
    }

    public double getEenheidsPrijs () {
        return eenheidsprijs;
    }

    public double getTotaalPrijs () {

        if (gewicht > 0.0) {
            return gewicht * eenheidsprijs;
        }

        else return eenheidsprijs;
    }

    public Date getHoudbaarheidsdatum () {
        return houdbaarheidsdatum;
    }

    public void setAantalProductenInVerpakking (int aantal) {
        this.aantalProductenInVerpakking = aantal;
    }

    public int getAantalProductenInVerpakking () {
        return aantalProductenInVerpakking;
    }

    public void setGewicht (double gewicht, String eenheid) {
        this.gewicht = gewicht;
        this.eenheid = eenheid;
    }

    public double getGewicht () {
        return gewicht;
    }

    public String getEenheid () {
        return eenheid;
    }
}