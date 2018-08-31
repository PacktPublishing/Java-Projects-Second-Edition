// START SNIPPET ch04_UniqueGuesser_head
package packt.java189fundamentals.example.mastermind;

import java.util.HashSet;

/**
 * A unique guesser creates guesses that do not use a color twice in a row.
 */
public class UniqueGuesser extends Guesser {


    public UniqueGuesser(Table table) {
        super(table);
    }

    /**
     * Set the first possible guess. This method is used during the construction process.
     */
    @Override
    protected void setFirstGuess() {
        int i = lastGuess.length - 1;
        for (var color = table.manager.firstColor();
             i >= 0;
             color = table.manager.nextColor(color)) {
            lastGuess[i--] = color;
        }
    }
//END SNIPPET
    /**
     * @param guess that we check for containing one color only once
     * @return true if the guess does not contain any color more than once
     */
    // START SNIPPET ch04_isNotUnique
    private boolean isNotUnique(Color[] guess) {
        final var alreadyPresent = new HashSet<Color>();
        for (final var color : guess) {
            if (alreadyPresent.contains(color)) {
                return true;
            }
            alreadyPresent.add(color);
        }
        return false;
    }
    // END SNIPPET

    // START SNIPPET ch04_UniqueGuesser_nextGuess
    @Override
    protected Color[] nextGuess() {
        Color[] guess = super.nextGuess();
        while (isNotUnique(guess)) {
            guess = super.nextGuess();
        }
        return guess;
    }
    // END SNIPPET
}
