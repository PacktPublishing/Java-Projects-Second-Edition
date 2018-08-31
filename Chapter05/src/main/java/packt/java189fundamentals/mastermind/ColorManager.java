//START SNIPPET ColorManager_ch05
package packt.java189fundamentals.mastermind;

import java.util.HashMap;
import java.util.Map;

public class ColorManager {
    protected final int nrColors;
    protected final Map<Color, Color> successor = new HashMap<>();
    private final ColorFactory factory;
    private Color first;

    public ColorManager(int nrColors, ColorFactory factory) {
        this.nrColors = nrColors;
        this.factory = factory;
        createOrdering();
    }

    private Color[] createColors() {
        var colors = new Color[nrColors];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = factory.newColor();
        }
        return colors;
    }

    private void createOrdering() {
        var colors = createColors();
        first = colors[0];
        for (int i = 0; i < nrColors - 1; i++) {
            successor.put(colors[i], colors[i + 1]);
        }
    }

    public Color firstColor() {
        return first;
    }

    public boolean thereIsNextColor(Color color) {
        return successor.containsKey(color);
    }

    public Color nextColor(Color color) {
        return successor.get(color);
    }

    public int getNrColors() {
        return nrColors;
    }
}
//END SNIPPET