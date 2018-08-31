// START SNIPPET ch04_PrettyPrintRow_head
package packt.java189fundamentals.example.mastermind;

import java.util.HashMap;
import java.util.Map;

public class PrettyPrintRow {

    private static final Map<Color, Character>
            letterMapping = new HashMap<>();
    private static final String letters = "RGBYWb";
    private static int counter = 0;

    private static char colorToChar(Color color) {
        if (!letterMapping.containsKey(color)) {
            letterMapping.put(color, letters.charAt(counter));
            counter++;

        }
        return letterMapping.get(color);
    }
    // END SNIPPET
    // START SNIPPET ch04_PrettyPrintRow_pprint
    public static String pprint(Row row) {
        var string = "";
        final var pRow = new PrintableRow(row);
        for (int i = 0; i < pRow.nrOfColumns(); i++) {
            string += colorToChar(pRow.position(i));
        }
        string += " ";
        string += pRow.matchedPositions();
        string += "/";
        string += pRow.matchedColors();
        return string;
    }
}
//END SNIPPET