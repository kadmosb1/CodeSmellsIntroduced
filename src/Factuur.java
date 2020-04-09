public class Factuur {

    FactuurRegel regel1;
    FactuurRegel regel2;
    FactuurRegel regel3;
    FactuurRegel regel4;
    Klant klant;

    public void maakFactuur () {

        double totaalprijs = 0.0;

        regel1 = new FactuurRegel("Product 1", 2.50, 20);
        totaalprijs += regel1.getTotaalprijs();
        regel2 = new FactuurRegel("Product 2", 10.00, 1);
        totaalprijs += regel2.getTotaalprijs();
        regel3 = new FactuurRegel("Product 3", 0.22, 1000);
        totaalprijs += regel3.getTotaalprijs();
        regel4 = new FactuurRegel("Product 4", 0.88, 10);
        totaalprijs += regel4.getTotaalprijs();

        klant = new Klant ("Haagse Hogeschool", "Johanna Westerdijkplein", 75, "", 2521, "EN", "DEN HAAG");

        System.out.println (klant);
        System.out.println ("\n\n");
        System.out.println (regel1);
        System.out.println (regel2);
        System.out.println (regel3);
        System.out.println (regel4);
        System.out.format ("     %40s            ________%n", "");
        System.out.format ("     %40s           %9.2f%n%n", "Totaal te betalen:", totaalprijs);
    }
}
