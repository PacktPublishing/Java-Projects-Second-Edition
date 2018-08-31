//START SNIPPET LetteredColorFactory_ch05
package packt.java189fundamentals.mastermind.lettered;

import packt.java189fundamentals.mastermind.Color;
import packt.java189fundamentals.mastermind.ColorFactory;

public class LetteredColorFactory implements ColorFactory {

    private static final String letters = "0123456789ABCDEFGHIJKLMNOPQRSTVWXYZabcdefghijklmnopqrstvwxzy";
    private int counter = 0;

    @Override
    public Color newColor() {
        Color color = new LetteredColor(letters.substring(counter, counter + 1));
        counter++;
        return color;
    }
}
//END SNIPPET