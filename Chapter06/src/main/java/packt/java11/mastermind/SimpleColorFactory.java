package packt.java11.mastermind;

public class SimpleColorFactory implements ColorFactory {
    @Override
    public Color newColor() {
        return new Color();
    }
}
