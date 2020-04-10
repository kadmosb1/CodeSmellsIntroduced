import java.text.ParseException;

public class Factuur {

    /*
     * Een factuur voor een klant bestaat uit een aantal regels met de onderdelen:
     * - Aantal of gewicht
     * - De gebruikte eenheid (bijv. kg of per stuk)
     * - De prijs per eenheid (bijv. per kg of per stuk).
     * - Een eventuele korting op de prijs.
     * - Het totaalbedrag dat voor het aantal producten (incl. korting) betaald
     *   moet worden.
     */
    private Klant klant;
    private FactuurRegel regel1;
    private FactuurRegel regel2;
    private FactuurRegel regel3;
    private FactuurRegel regel4;
    private FactuurRegel regel5;

    /*
     * De factuur opent met de gegevens van een klant.
     */
    private void printKlant (String naam, String adres, String postcode, String woonplaats) {
        System.out.format("%s%n%s%n%s  %s%n%n", naam, adres, postcode, woonplaats);
    }

    /*
     * De gehele factuur wordt op het scherm getoond.
     */
    public void maakFactuur (boolean buitenNederland) throws ParseException {

        /*
         * De factuur moet gemakkelijk voor zowel een Nederlandse als een Belgische klant opgesteld kunnen
         * worden. Dat gebeurt op basis van de eerste twee letters van het BTW-nummer.
         */
        String btwNummer;

        if (buitenNederland) {
            btwNummer = "BE 0826882419";
        }
        else {
            btwNummer = "NL 0826882419";
        }

        /*
         * Het totaalbedrag dat een klant moet betalen wordt op 0.0 geïnitialiseerd,
         * zodat alle bedragen (incl. kortingen) bij elkaar opgeteld kunnen worden.
         */
        double totaalprijs = 0.0;

        klant = new Klant ("De Haagse Hogeschool", "Johanna Westerdijkplein", 75, "", 2521, "EN", "DEN HAAG", btwNummer, Klant.OVERHEID);

        /*
         * Voor elke regel worden nu producten aangemaakt, waarvoor de gegevens voor
         * aantal producten, producten per verpakking (bijv. 6 flessen in een doos),
         * gewicht en eenheid voor dat gewicht (bijv. kg) apart worden ingesteld.
         * Op basis van deze gegevens wordt de totaalprijs bepaald.
         */
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

        /*
         * Bovenaan de factuur worden de gegevens van een klant getoond.
         */
        printKlant(klant.getNaam(), klant.getAdres(), klant.getPostcode(), klant.getWoonplaats());

        /*
         * Na een paar witregels wordt eerst de titelregel getoond met daarin
         * de namen boven de kolommen.
         */
        System.out.format ("Aantal Eenheid  %-30s   %8s  %7s   %8s%n", "Naam product", "prijs/st", "korting", "totaal");

        /*
         * Er wordt gecontroleerd of de BTW verlegd moet worden (wij doen dat als
         * de klant niet in Nederland 'woont'. Consequentie van dat verleggen is
         * dat we bij deze klant geen BTW in rekening brengen. Dat doen we uiteraard
         * voor klanten in Nederland wel. Als een klant buiten Nederland 'woont'
         * wordt als eerste regel in de factuur opgenomen dat de BTW wordt verlegd
         * naar de klant in het buitenland (met zijn BTW-nummer).
         */
        if (klant.btwMoetWordenVerlegd()) {
            System.out.format ("%15s BTW wordt verlegd naar %s%n", "", klant.getBTWNummer());
        }

        /*
         * Vervolgens worden de factuurregels geprint met daaronder een streep
         * om zichtbaar te maken dat we de bedragen voor de factuurregels bij
         * elkaar optellen.
         */
        System.out.print (regel1);
        System.out.print (regel2);
        System.out.print (regel3);
        System.out.print (regel4);
        System.out.format ("       %-39s   %8s  %7s  %9s%n", "", "", "", "_________ +");

        /*
         * Als de BTW niet moet worden verlegd (de klant 'woont' in Nederland),
         * Wordt een BTW-regel toegevoegd en wordt opnieuw zichtbaar gemaakt,
         * dat de BTW bij het totaalbedrag van de factuurregels opgeteld moet
         * worden.
         */
        if (!klant.btwMoetWordenVerlegd()) {
            System.out.format ("       %-39s   %8s  %7s  %9.2f%n", "", "", "21% BTW", 0.21 * totaalprijs);
            System.out.format ("       %-39s   %8s  %7s  %9s%n", "", "", "", "_________ +");
        }

        if (!klant.btwMoetWordenVerlegd()) {
            totaalprijs *= 1.21;
        }

        /*
         * Als een klant onderdeel is van de overheid, dan ontvangt de klant
         * over zijn gehele rekening een korting van 2%.
         */
        double kortingspercentageVanwegeTypeKlant = 0.0;

        if (klant.getTypeKlant() == Klant.OVERHEID) {
            kortingspercentageVanwegeTypeKlant = 2.0;
        }
        else if (klant.getTypeKlant() == Klant.HORECA) {
            kortingspercentageVanwegeTypeKlant = 4.0;
        }

        /*
         * Als de klant geen korting krijgt, volgt hierna de laatste regel van
         * de factuur met het kenmerk "Totaal te betalen". Als wel korting
         * wordt gegeven, is de volgende regel nog maar een subtotaal (de korting
         * moet er nog van af worden getrokken.
         */
        String totaalOfSubtotaal = "Totaal te betalen";

        if (kortingspercentageVanwegeTypeKlant > 0.0) {
            totaalOfSubtotaal = "Subtotaal";
        }

        System.out.format ("       %-39s  %18s  €%8.2f%n", "", totaalOfSubtotaal, totaalprijs);

        /*
         * Als een klant korting ontvangt, worden de laatste regels met de
         * verwerking van deze korting onderaan de factuur toegevoegd.
         */
        if (kortingspercentageVanwegeTypeKlant > 0.0) {
            double korting = kortingspercentageVanwegeTypeKlant / 100.0 * totaalprijs;
            totaalprijs -= korting;

            System.out.format ("       %-39s  %18s  €%8.2f%n", "", String.format ("%2.0f%14s", kortingspercentageVanwegeTypeKlant, "% Klantkorting"), korting);
            System.out.format ("       %-39s   %17s  %9s%n", "", "", "_________ -");
            System.out.format ("       %-39s   %17s  €%8.2f%n", "", "Totaal te betalen", totaalprijs);
        }
    }
}
