import java.text.ParseException;

public class Factuur {

    private FactuurRegel regel1;
    private FactuurRegel regel2;
    private FactuurRegel regel3;
    private FactuurRegel regel4;
    private Klant klant;

    public void printKlant (String naam, String adres, String postcode, String woonplaats) {
        System.out.format("%s%n%s%n%s  %s%n%n", naam, adres, postcode, woonplaats);
    }

    public void maakFactuur () throws ParseException {

        double totaalprijs = 0.0;

        regel1 = new FactuurRegel("Product 1", 2.50, "20-04-2021");
        regel1.setAantalProducten(20);
        totaalprijs += regel1.getTotaalprijs();
        regel2 = new FactuurRegel("Product 2", 10.00, "20-04-2020");
        regel2.setAantalProducten(1);
        totaalprijs += regel2.getTotaalprijs();
        regel3 = new FactuurRegel("Product 3", 0.22, "08-04-2020");
        regel3.setAantalProducten(1000);
        totaalprijs += regel3.getTotaalprijs();
        regel4 = new FactuurRegel("Product 4", 0.88, "21-03-2020");
        regel4.setAantalProducten(100);
        totaalprijs += regel4.getTotaalprijs();

        klant = new Klant ("De Haagse Hogeschool", "Johanna Westerdijkplein", 75, "", 2521, "EN", "DEN HAAG", "BE 0826882419");

        printKlant(klant.getNaam(), klant.getAdres(), klant.getPostcode(), klant.getWoonplaats());

        System.out.format ("aantal %-30s   %8s  %7s   %8s%n", "Naam product", "prijs/st", "korting", "totaal");

        if (klant.btwMoetWordenVerlegd()) {
            System.out.format ("       BTW wordt verlegd naar %s%n", klant.getBTWNummer());
        }
        System.out.println (regel1);
        System.out.println (regel2);
        System.out.println (regel3);
        System.out.println (regel4);
        System.out.format ("       %-30s   %8s  %7s  %9s%n", "", "", "", "_________");

        if (!klant.btwMoetWordenVerlegd()) {
            System.out.format ("       %-30s   %8s  %7s  %9.2f%n", "", "", "21% BTW", 0.21 * totaalprijs);
            System.out.format ("       %-30s   %8s  %7s  %9s%n", "", "", "", "_________");
        }

        if (!klant.btwMoetWordenVerlegd()) {
            totaalprijs *= 1.21;
        }

        System.out.format ("       %-30s   %8s  %7s  €%8.2f%n%n", "Totaal te betalen:", "", "", totaalprijs);
    }
}
