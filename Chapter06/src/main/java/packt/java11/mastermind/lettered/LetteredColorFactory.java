package packt.java11.mastermind.lettered;

import packt.java11.mastermind.ColorFactory;
import packt.java11.mastermind.Color;

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
