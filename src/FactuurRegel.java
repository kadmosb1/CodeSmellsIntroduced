public class FactuurRegel {
    private Product product;
    private int aantalProducten;
    private double kortingspercentage;

    public FactuurRegel (String productnaam, double productprijs) {
        product = new Product (productnaam, productprijs);
        kortingspercentage = 0.0;
    }

    public void setAantalProducten (int aantal) {
        aantalProducten = aantal;
    }

    public String toString () {

        double prijsMetKorting = aantalProducten * product.getPrijs ();

        /*
         * Vanaf 100 stuks van een product geldt een kortingspercentage van 2%.
         * Vanaf 1.000 stuks geldt een kortingspercentage van 3%.
         */
        if (aantalProducten >= 1000) {
            kortingspercentage = 3.0;
        }
        else if (aantalProducten >= 100) {
            kortingspercentage = 2.0;
        }

        prijsMetKorting *= (100.0 - kortingspercentage) / 100.0;
        return String.format("%6d %-30s  €%8.2f     %3.0f%%  €%8.2f", aantalProducten, product.getNaam(), product.getPrijs (), kortingspercentage, prijsMetKorting);
    }

    public double getTotaalprijs () {

        double prijsMetKorting = aantalProducten * product.getPrijs ();

        /*
         * Vanaf 100 stuks van een product geldt een kortingspercentage van 2%.
         * Vanaf 1.000 stuks geldt een kortingspercentage van 3%.
         */
        if (aantalProducten >= 1000) {
            kortingspercentage = 3.0;
        }
        else if (aantalProducten >= 100) {
            kortingspercentage = 2.0;
        }

        prijsMetKorting *= (100.0 - kortingspercentage) / 100.0;

        return prijsMetKorting;
    }
}