// START SNIPPET ch04_GeneralGuesser
package packt.java189fundamentals.example.mastermind;

public class GeneralGuesser extends Guesser {

    public GeneralGuesser(Table table) {
        super(table);
    }

    /**
     * Set the first possible guess. This method is used during the construction process.
     * This version just sets all colors to be the first color, since in this guesser
     * position do not need to be unique.
     */
    @Override
    protected void setFirstGuess() {
        int i = 0;
        for (Color color = table.manager.firstColor();
             i < lastGuess.length;
            ) {
            lastGuess[i++] = color;
        }
    }
}
//END SNIPPET