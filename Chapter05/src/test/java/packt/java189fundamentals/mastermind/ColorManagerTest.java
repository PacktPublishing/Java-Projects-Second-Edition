package packt.java189fundamentals.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class ColorManagerTest {

    private static final int NR_COLORS = 6;

    //START SNIPPET thereIsAFirstColor_ch05
    @Test
    public void thereIsAFirstColor() {
        var manager = new ColorManager(NR_COLORS, Color::new);
        Assert.assertNotNull(manager.firstColor());
    }
    //END SNIPPET

    @Test
    public void noNextColorIsNullWhenThereIsOne() {
        var manager = new ColorManager(NR_COLORS, Color::new);
        var color = manager.firstColor();
        while (manager.thereIsNextColor(color)) {
            Assert.assertNotNull(color = manager.nextColor(color));
        }
    }

    @Test
    public void noColorHasNoNextColor() {
        var manager = new ColorManager(NR_COLORS, Color::new);
        Assert.assertNull(manager.nextColor(Color.none));
    }
}
