package packt.java189fundamentals.mastermind.integration;

import packt.java189fundamentals.mastermind.Color;
import packt.java189fundamentals.mastermind.ColorManager;
import packt.java189fundamentals.mastermind.Guess;

public class SimpleSecret implements Secret {
    private final ColorManager manager;

    public SimpleSecret(ColorManager manager) {
        this.manager = manager;
    }

    @Override
    public Guess createSecret( int nrColumns) {
        final var nrColors = manager.getNrColors();
        final var colors = new Color[nrColumns];
        var color = manager.firstColor();
        var count = 0;
        while (count < nrColors - nrColumns) {
            color = manager.nextColor(color);
            count++;
        }
        for (int i = 0; i < nrColumns; i++) {
            colors[i] = color;
            color = manager.nextColor(color);
        }
        return new Guess(colors);
    }
}
