import java.text.ParseException;

public class Factuur {

    private FactuurRegel regel1;
    private FactuurRegel regel2;
    private FactuurRegel regel3;
    private FactuurRegel regel4;
    private FactuurRegel regel5;
    private Klant klant;

    public void printKlant (String naam, String adres, String postcode, String woonplaats) {
        System.out.format("%s%n%s%n%s  %s%n%n", naam, adres, postcode, woonplaats);
    }

    public void maakFactuur () throws ParseException {

        /*
         * De factuur moet gemakkelijk voor zowel een Nederlandse als een Belgische klant opgesteld kunnen
         * worden. Dat gebeurt op basis van de eerste twee letters van het BTW-nummer.
         */
        boolean buitenNederland = false;
        String btwNummer = "";

        if (buitenNederland) {
            btwNummer = "BE 0826882419";
        }
        else {
            btwNummer = "NL 0826882419";
        }

        double totaalprijs = 0.0;

        klant = new Klant ("De Haagse Hogeschool", "Johanna Westerdijkplein", 75, "", 2521, "EN", "DEN HAAG", btwNummer);

        regel1 = new FactuurRegel("Product 1", 2.50, "20-04-2021");
        regel1.setAantal (20, 1, 0.0, "");
        totaalprijs += regel1.getTotaalprijs();
        regel2 = new FactuurRegel("Product 2", 10.00, "20-04-2020");
        regel2.setAantal(1, 250, 0.0, "");
        totaalprijs += regel2.getTotaalprijs();
        regel3 = new FactuurRegel("Product 3", 0.22, "08-04-2020");
        regel3.setAantal(1000, 1, 0.0, "");
        totaalprijs += regel3.getTotaalprijs();
        regel4 = new FactuurRegel("Product 4", 1.50, "08-04-2020");
        regel4.setAantal(0, 0, 2.55, "kg");
        totaalprijs += regel4.getTotaalprijs();
        regel5 = new FactuurRegel("Product 5", 0.88, "21-03-2020");
        regel5.setAantal(1, 1, 0.0, "");
        totaalprijs += regel5.getTotaalprijs();

        printKlant(klant.getNaam(), klant.getAdres(), klant.getPostcode(), klant.getWoonplaats());

        System.out.format ("Aantal Eenheid  %-30s   %8s  %7s   %8s%n", "Naam product", "prijs/st", "korting", "totaal");

        if (klant.btwMoetWordenVerlegd()) {
            System.out.format ("%15s BTW wordt verlegd naar %s%n", "", klant.getBTWNummer());
        }
        System.out.print (regel1);
        System.out.print (regel2);
        System.out.print (regel3);
        System.out.print (regel4);
        System.out.format ("       %-39s   %8s  %7s  %9s%n", "", "", "", "_________");

        if (!klant.btwMoetWordenVerlegd()) {
            System.out.format ("       %-39s   %8s  %7s  %9.2f%n", "", "", "21% BTW", 0.21 * totaalprijs);
            System.out.format ("       %-39s   %8s  %7s  %9s%n", "", "", "", "_________");
        }

        if (!klant.btwMoetWordenVerlegd()) {
            totaalprijs *= 1.21;
        }

        System.out.format ("       %-39s   %8s  %7s  €%8.2f%n%n", "Totaal te betalen:", "", "", totaalprijs);
    }
}
