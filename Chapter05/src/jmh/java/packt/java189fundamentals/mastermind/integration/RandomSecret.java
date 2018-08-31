package packt.java189fundamentals.mastermind.integration;

import packt.java189fundamentals.mastermind.Color;
import packt.java189fundamentals.mastermind.ColorManager;
import packt.java189fundamentals.mastermind.Guess;

import java.util.ArrayList;

public class RandomSecret implements Secret {
    private final ColorManager manager;

    public RandomSecret(ColorManager manager) {
        this.manager = manager;
    }

    @Override
    public Guess createSecret(int nrColumns) {
        final int nrColors = manager.getNrColors();
        final var colors = new Color[nrColumns];
        final var allColors = new ArrayList<Color>(nrColors);
        var color = manager.firstColor();
        for (int count = 0; count < nrColors; count++) {
            allColors.add(color);
            color = manager.nextColor(color);
        }
        for (int i = 0; i < nrColumns; i++) {
            int index = (int) Math.floor(Math.random() * (allColors.size() + 1));
            if( index >= allColors.size() ){
                index --;
            }
            colors[i] = allColors.remove(index);
        }
        return new Guess(colors);
    }
}
