// START SNIPPET SimpleColorFactory_ch05
package packt.java189fundamentals.mastermind;

public class SimpleColorFactory implements ColorFactory {
    @Override
    public Color newColor() {
        return new Color();
    }
}
// END SNIPPET