// START SNIPPET LetteredColor_ch05
package packt.java189fundamentals.mastermind.lettered;

import packt.java189fundamentals.mastermind.Color;

public class LetteredColor extends Color {

    private final String letter;
    public LetteredColor(String letter){
        this.letter = letter;
    }

    @Override
    public String toString(){
        return letter;
    }
}
//END SNIPPET