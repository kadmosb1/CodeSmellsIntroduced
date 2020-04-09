import java.text.SimpleDateFormat;
import java.util.Locale;

public class DatumUtil {
    /*
     * Deze variabele wordt gebruikt om new Date-objects aan te kunnen maken op basis van de gegeven format
     */
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy", new Locale("nl_NL"));
}
