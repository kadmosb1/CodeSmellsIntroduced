public class Factuur {

    FactuurRegel regel1;
    FactuurRegel regel2;
    FactuurRegel regel3;
    FactuurRegel regel4;
    Klant klant;

    public void maakFactuur () {

        double totaalprijs = 0.0;

        regel1 = new FactuurRegel("Product 1", 2.50);
        regel1.setAantalProducten(20);
        totaalprijs += regel1.getTotaalprijs();
        regel2 = new FactuurRegel("Product 2", 10.00);
        regel2.setAantalProducten(1);
        totaalprijs += regel2.getTotaalprijs();
        regel3 = new FactuurRegel("Product 3", 0.22);
        regel3.setAantalProducten(1000);
        totaalprijs += regel3.getTotaalprijs();
        regel4 = new FactuurRegel("Product 4", 0.88);
        regel4.setAantalProducten(100);
        totaalprijs += regel4.getTotaalprijs();

        klant = new Klant ("Haagse Hogeschool", "Johanna Westerdijkplein", 75, "", 2521, "EN", "DEN HAAG");

        System.out.println (klant + "\n\n");
        // "%6d %-30s  €%8.2f     %3.0f%%  €%8.2f"
        System.out.format ("aantal %-30s   %8s  %7s   %8s%n", "Naam product", "prijs/st", "korting", "totaal");
        System.out.println (regel1);
        System.out.println (regel2);
        System.out.println (regel3);
        System.out.println (regel4);
        System.out.format ("       %-30s   %8s  %7s  %9s%n", "", "", "", "_________");
        System.out.format ("       %-30s   %8s  %7s  €%8.2f%n%n", "Totaal te betalen:", "", "", totaalprijs);
    }
}
