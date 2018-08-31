package packt.java189fundamentals.example.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class ColorManagerTest {

    private static final int NR_COLORS = 6;

    @Test
    public void thereIsAFirstColor() {
        ColorManager manager = new ColorManager(NR_COLORS);
        System.out.println(manager.firstColor());
        Assert.assertNotNull(manager.firstColor());
    }

    @Test
    public void noNextColorIsNullWhenThereIsOne() {
        ColorManager manager = new ColorManager(NR_COLORS);
        Color color = manager.firstColor();
        while ( manager.thereIsNextColor(color)) {
            Assert.assertNotNull(color = manager.nextColor(color));
        }
    }

    @Test
    public void noColorHasNoNextColor() {
        ColorManager manager = new ColorManager(NR_COLORS);
        Assert.assertNull(manager.nextColor(Color.none));
    }
}
