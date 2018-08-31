// START SNIPPET ColorManager_ch06
package packt.java11.mastermind;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.lang.System.Logger;

import static java.lang.System.Logger.Level.DEBUG;

@Singleton
public class ColorManager {
    protected final int nrColors;
    protected final Map<Color, Color> successor = new HashMap<>();
    private Color first;
    private final ColorFactory factory;
    private static final Logger log
            = System.getLogger(ColorManager.class.getName());

    @Inject
    public ColorManager(@Named("nrColors") int nrColors,
                        ColorFactory factory) {
        log.log(DEBUG, "creating colorManager for {0} colors", nrColors);
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
}
//END SNIPPET