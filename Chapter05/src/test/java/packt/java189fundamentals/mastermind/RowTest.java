package packt.java189fundamentals.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class RowTest {
    private static final int NR_COLORS = 6;
    private static final int NR_COLUMNS = 4;
    private static final ColorManager manager = new ColorManager(NR_COLORS, Color::new);

    private Color[] firstColors() {
        final var colors = new Color[NR_COLUMNS];
        int i = 0;
        for (var color = manager.firstColor();
             i < colors.length;
             color = manager.nextColor(color)) {
            colors[i++] = color;
        }
        return colors;
    }

    private void stepTheLastColor(Color[] colors) {
        colors[NR_COLUMNS - 1] = manager.nextColor(colors[NR_COLUMNS - 1]);
    }

    @Test
    public void allColorsAndPositionsMatch() {
        final var colors = firstColors();
        final var guess = new Guess(colors);
        final var row = new Row(guess, NR_COLUMNS, 0);
        Assert.assertTrue(row.matches(guess));
    }

    @Test
    public void allButOneColorsAndPositionsMatch() {
        final var colors = firstColors();
        final var row = new Row(new Guess(colors), NR_COLUMNS - 1, 0);
        stepTheLastColor(colors);
        Assert.assertTrue(row.matches(new Guess(colors)));
    }

    @Test
    public void twoColorsWrongPositionOtherGoodPosition() {
        ColorManager manager = new ColorManager(NR_COLORS, Color::new);
        Color[] colors = firstColors();
        Row row = new Row(new Guess(colors), NR_COLUMNS - 2, 2);
        var swap = colors[0];
        colors[0] = colors[1];
        colors[1] = swap;
        Assert.assertTrue(row.matches(new Guess(colors)));
    }
}
