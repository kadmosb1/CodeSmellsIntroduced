import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FactuurRegel {

    private Product product;
    private int aantalProducten;
    private double kortingspercentage;

    public FactuurRegel (String productnaam, double prijsPerStukOfKilo, String datum) throws ParseException {
        product = new Product (productnaam, prijsPerStukOfKilo, DatumUtil.sdf.parse(datum));
        kortingspercentage = 0.0;
        aantalProducten = 1;
    }

    public void setAantal (int aantal, int aantalProductenInVerpakking, double gewicht, String eenheid) {
        if (aantal < 1) {
            this.aantalProducten = 0;
        }
        else if (aantal > 1) {
            this.aantalProducten = aantal;
        }

        if (aantalProductenInVerpakking < 1) {
            this.aantalProducten = 0;
        }
        else if (aantalProductenInVerpakking > 1) {
            product.setAantalProductenInVerpakking(aantalProductenInVerpakking);
        }

        if (gewicht > 0.0) {
            product.setGewicht(gewicht, eenheid);
        }
    }

    public void setGewicht (double gewicht, String eenheid) {
        product.setGewicht(gewicht, eenheid);
    }

    /*
     * Als een product minder dan 10 dagen over datum is, wordt een korting gegeven van 50%.
     * Vanaf 10 dagen over datum wordt een product gratis weg gegeven.
     */
    protected double bepaalKortingVanwegeHoudbaarheidsdatum (Date vandaag, long verschilInDagen, Date houdbaarheidsdatum) {
        if (vandaag.before (houdbaarheidsdatum)) {
            return 0.0;
        }
        else if (verschilInDagen < 10) {
            return 50.0;
        }
        else {
            return 100.0;
        }
    }

    public String toString () {

        double prijsMetKorting;
        double kortingVanwegeAantalProducten = 0.0;
        double kortingVanwegeHoudbaarheidsdatum = 0.0;

        if (product.getGewicht() > 0.0) {
            prijsMetKorting = product.getTotaalPrijs();
        }
        else {
            prijsMetKorting = product.getEenheidsPrijs() * aantalProducten;
        }

        /*
         * Vanaf 100 stuks van een product geldt een korting van 2%.
         * Vanaf 1.000 stuks geldt een korting van 3%.
         */
        int totaalAantalProducten = aantalProducten * product.getAantalProductenInVerpakking();

        if (totaalAantalProducten >= 1000) {
            kortingVanwegeAantalProducten = 3.0;
            prijsMetKorting *= (100.0 - kortingVanwegeAantalProducten) / 100.0;
        }
        else if (totaalAantalProducten >= 100) {
            kortingVanwegeAantalProducten = 2.0;
            prijsMetKorting *= (100.0 - kortingVanwegeAantalProducten) / 100.0;
        }

        Date vandaag = new Date ();
        long verschilInMillisecondes = Math.abs(vandaag.getTime() - product.getHoudbaarheidsdatum().getTime ());
        long verschilInDagen = TimeUnit.DAYS.convert(verschilInMillisecondes, TimeUnit.MILLISECONDS);
        kortingVanwegeHoudbaarheidsdatum = bepaalKortingVanwegeHoudbaarheidsdatum (vandaag, verschilInDagen, product.getHoudbaarheidsdatum());

        kortingspercentage = 100.0 - (100.0 - kortingVanwegeAantalProducten) / 100.0 * (100.0 - kortingVanwegeHoudbaarheidsdatum);
        prijsMetKorting *= (100.0 - kortingVanwegeHoudbaarheidsdatum) / 100.0;

        if ((aantalProducten == 0) && (product.getGewicht() <= 0.0)) {
            return "";
        }
        else if (kortingVanwegeHoudbaarheidsdatum == 100.0) {
            return "";
        }
        else {
            if (product.getAantalProductenInVerpakking() > 1) {
                return String.format("%6d %-8s %-30s  €%8.2f     %3.0f%%  €%8.2f%n", aantalProducten, "per " + product.getAantalProductenInVerpakking(), product.getNaam(), product.getEenheidsPrijs(), kortingspercentage, prijsMetKorting);
            }
            else if (product.getGewicht() > 0.0) {
                return String.format("%6.1f %-8s %-30s  €%8.2f     %3.0f%%  €%8.2f%n", product.getGewicht(), product.getEenheid(), product.getNaam(), product.getEenheidsPrijs(), kortingspercentage, prijsMetKorting);
            }
            else {
                return String.format("%6d %-8s %-30s  €%8.2f     %3.0f%%  €%8.2f%n", aantalProducten, "per stuk", product.getNaam(), product.getEenheidsPrijs(), kortingspercentage, prijsMetKorting);
            }
        }
    }

    public double getTotaalprijs () {

        double prijsMetKorting;
        double kortingVanwegeAantalProducten = 0.0;
        double kortingVanwegeHoudbaarheidsdatum = 0.0;

        if (product.getGewicht() > 0.0) {
            prijsMetKorting = product.getTotaalPrijs();
        }
        else {
            prijsMetKorting = product.getEenheidsPrijs() * aantalProducten;
        }

        /*
         * Vanaf 100 stuks van een product geldt een korting van 2%.
         * Vanaf 1.000 stuks geldt een korting van 3%.
         */
        int totaalAantalProducten = aantalProducten * product.getAantalProductenInVerpakking();

        if (totaalAantalProducten >= 1000) {
            kortingVanwegeAantalProducten = 3.0;
            prijsMetKorting *= (100.0 - kortingVanwegeAantalProducten) / 100.0;
        }
        else if (totaalAantalProducten >= 100) {
            kortingVanwegeAantalProducten = 2.0;
            prijsMetKorting *= (100.0 - kortingVanwegeAantalProducten) / 100.0;
        }

        Date vandaag = new Date ();
        long verschilInMillisecondes = Math.abs(vandaag.getTime() - product.getHoudbaarheidsdatum().getTime ());
        long verschilInDagen = TimeUnit.DAYS.convert(verschilInMillisecondes, TimeUnit.MILLISECONDS);
        kortingVanwegeHoudbaarheidsdatum = bepaalKortingVanwegeHoudbaarheidsdatum (vandaag, verschilInDagen, product.getHoudbaarheidsdatum());

        kortingspercentage = 100.0 - (100.0 - kortingVanwegeAantalProducten) / 100.0 * (100.0 - kortingVanwegeHoudbaarheidsdatum);
        prijsMetKorting *= (100.0 - kortingVanwegeHoudbaarheidsdatum) / 100.0;

        return prijsMetKorting;
    }
}