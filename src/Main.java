import java.text.ParseException;

public class Main {

    /*
     * Er wordt een factuur op het scherm getoond.
     */
    public static void main(String[] args) throws ParseException {
        Factuur factuur = new Factuur ();
        factuur.maakFactuur (true);
    }
}
