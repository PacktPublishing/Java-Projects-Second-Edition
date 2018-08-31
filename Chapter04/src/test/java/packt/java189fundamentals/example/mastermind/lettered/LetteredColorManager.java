package packt.java189fundamentals.example.mastermind.lettered;

import packt.java189fundamentals.example.mastermind.Color;
import packt.java189fundamentals.example.mastermind.ColorManager;

public class LetteredColorManager extends ColorManager {
    public LetteredColorManager(int nrColors) {
        super(nrColors);
    }

    protected Color newColor() {
        return new LetteredColor();
    }
}
