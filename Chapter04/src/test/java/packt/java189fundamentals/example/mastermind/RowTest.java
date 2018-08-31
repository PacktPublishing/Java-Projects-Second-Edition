package packt.java189fundamentals.example.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class RowTest {
    static final int NR_COLORS = 6;
    static final int NR_COLUMNS = 4;


    @Test
    public void allColorsAndPositionsMatch() {
        ColorManager manager = new ColorManager(NR_COLORS);
        Color[] positions = new Color[NR_COLUMNS];
        int i = 0;
        for (Color color = manager.firstColor(); i < positions.length; color = manager.nextColor(color)) {
            positions[i++] = color;
        }
        Row row = new Row(positions);
        row.setMatch(NR_COLUMNS, 0);
        Assert.assertTrue(row.guessMatches(positions));
    }

    @Test
    public void allButOneColorsAndPositionsMatch() {
        ColorManager manager = new ColorManager(NR_COLORS);
        Color[] positions = new Color[NR_COLUMNS];
        int i = 0;
        for (Color color = manager.firstColor(); i < positions.length; color = manager.nextColor(color)) {
            positions[i++] = color;
        }
        Row row = new Row(positions);
        positions[NR_COLUMNS - 1] = manager.nextColor(positions[NR_COLUMNS - 1]);
        row.setMatch(NR_COLUMNS - 1, 0);
        Assert.assertTrue(row.guessMatches(positions));
    }

    @Test
    public void twoColorsWrongPositionOtherGoodPosition() {
        ColorManager manager = new ColorManager(NR_COLORS);
        Color[] positions = new Color[NR_COLUMNS];
        int i = 0;
        for (Color color = manager.firstColor(); i < positions.length; color = manager.nextColor(color)) {
            positions[i++] = color;
        }
        Row row = new Row(positions);
        Color swap = positions[0];
        positions[0] = positions[1];
        positions[1] = swap;
        row.setMatch(NR_COLUMNS - 2, 2);
        Assert.assertTrue(row.guessMatches(positions));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIAEForInvalidMatchParameters(){
        Row row = new Row(new Color[NR_COLUMNS]);
        row.setMatch(NR_COLUMNS,1);
    }
}
