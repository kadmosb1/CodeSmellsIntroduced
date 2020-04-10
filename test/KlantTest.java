import org.junit.Test;
import static org.junit.Assert.*;

public class KlantTest {

    @Test
    public void testVerleggingVanBTW () {
        Klant klant = new Klant ("De Haagse Hogeschool", "Johanna Westerdijkplein", 75, "", 2521, "EN", "DEN HAAG", "BE 0826882419", Klant.CONSUMENT);
        assertTrue (klant.btwMoetWordenVerlegd());
        klant = new Klant ("De Haagse Hogeschool", "Johanna Westerdijkplein", 75, "", 2521, "EN", "DEN HAAG", "NL 0826882419", Klant.OVERHEID);
        assertFalse (klant.btwMoetWordenVerlegd());
    }
}