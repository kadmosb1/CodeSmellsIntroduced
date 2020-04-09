import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class FactuurRegelTest {

    @Test
    public void testKortingBijHoudbaarheidsdatum() throws ParseException {
        FactuurRegel fr = new FactuurRegel("Product 1", 10.0, "08-04-2020");
        Date houdbaarheidsdatum = DatumUtil.sdf.parse ("08-04-2020");
        assertEquals(50.0, fr.bepaalKortingVanwegeHoudbaarheidsdatum(houdbaarheidsdatum), 0.1);
        houdbaarheidsdatum = DatumUtil.sdf.parse ("20-03-2020");
        assertEquals(100.0, fr.bepaalKortingVanwegeHoudbaarheidsdatum(houdbaarheidsdatum), 0.1);
        houdbaarheidsdatum = DatumUtil.sdf.parse ("08-05-2021");
        assertEquals(0.0, fr.bepaalKortingVanwegeHoudbaarheidsdatum(houdbaarheidsdatum), 0.1);
    }
}