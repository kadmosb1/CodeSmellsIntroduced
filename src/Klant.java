public class Klant {
    private String naam;
    private String straat;
    private int huisnummer;
    private String huisnummerToevoeging;
    private int cijfersPostcode;
    private String lettersPostcode;
    private String woonplaats;

    public Klant (String naam, String straat, int huisnummer, String huisnummerToevoeging, int cijfersPostcode, String lettersPostcode, String woonplaats) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.huisnummerToevoeging = huisnummerToevoeging;
        this.cijfersPostcode = cijfersPostcode;
        this.lettersPostcode = lettersPostcode;
        this.woonplaats = woonplaats;
    }

    public String toString () {
        return String.format ("%s%n%s %3d%s%n%4d %s  %s", naam, straat, huisnummer, huisnummerToevoeging, cijfersPostcode, lettersPostcode, woonplaats);
    }
}
