public class Klant {

    private String naam;
    private String straat;
    private int huisnummer;
    private String huisnummerToevoeging;
    private int cijfersPostcode;
    private String lettersPostcode;
    private String woonplaats;
    private boolean landLigtBuitenEuropa;
    private String btwNummer;

    public Klant (String naam, String straat, int huisnummer, String huisnummerToevoeging, int cijfersPostcode, String lettersPostcode, String woonplaats, String btwNummer) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.huisnummerToevoeging = huisnummerToevoeging;
        this.cijfersPostcode = cijfersPostcode;
        this.lettersPostcode = lettersPostcode;
        this.woonplaats = woonplaats;
        this.btwNummer = btwNummer;
    }

    public String getNaam () {
        return naam;
    }

    public String getAdres () {
        return straat + " " + huisnummer + " " + huisnummerToevoeging;
    }

    public String getPostcode () {
        return lettersPostcode + " " + cijfersPostcode;
    }

    public String getWoonplaats () {
        return woonplaats;
    }

    public String getBTWNummer () {
        return btwNummer;
    }

    /*
     * Als Een klant buiten Nederland 'woont', wordt geen BTW gerekend (die BTW wordt
     * verlegd naar de klant in het buitenland die dit zelf moet melden bij zijn/haar
     * eigen belastingdienst.
     */
    public boolean btwMoetWordenVerlegd () {
        return !btwNummer.substring(0, 2).equals("NL");
    }
}
