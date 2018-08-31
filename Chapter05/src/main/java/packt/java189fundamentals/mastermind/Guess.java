//START SNIPPET Guess_ch05_head
package packt.java189fundamentals.mastermind;

import java.util.Arrays;
import java.util.HashSet;

public class Guess {
    public final static Guess none = new Guess(new Color[0]);
    private final Color[] colors;
    private boolean uniquenessWasNotCalculated = true;
    private boolean unique;

    public Guess(Color[] colors) {
        this.colors = Arrays.copyOf(colors, colors.length);
    }

    //END SNIPPET
//START SNIPPET Guess_ch05_getters
    public Color getColor(int i) {
        return colors[i];
    }

    public int nrOfColumns() {
        return colors.length;
    }
//END SNIPPET

//START SNIPPET Guess_ch05_nextGuess

    /**
     * Calculate the next guess and return a new Guess object.
     * The guesses are ordered in the order of the colors as
     * specified by the color manager.
     *
     * @param manager that specifies the order of the colors
     *                an can return the next color after one color.
     * @return the guess that comes after this guess.
     */
    public Guess nextGuess(ColorManager manager) {
        final var colors = Arrays.copyOf(this.colors, nrOfColumns());

        int i = 0;
        var guessFound = false;
        while (i < colors.length && !guessFound) {
            if (manager.thereIsNextColor(getColor(i))) {
                colors[i] = manager.nextColor(colors[i]);
                guessFound = true;
            } else {
                colors[i] = manager.firstColor();
                i++;
            }
        }
        if (guessFound) {
            return new Guess(colors);
        } else {
            return Guess.none;
        }
    }
//END SNIPPET

    //START SNIPPET Guess_ch05_assertCompatibility
    private void assertCompatibility(Guess guess) {
        if (nrOfColumns() != guess.nrOfColumns()) {
            throw new IllegalArgumentException("Can not compare different length guesses");
        }
    }
//END SNIPPET

    /**
     * Count the number of colors that are present on the guess
     * but not on the pos where they are in the other guess.
     * If the same color is on multiple pos it is counte
     * for each pos once. For example the secret is
     * <pre>
     *     RGRB
     * </pre>
     * and the guess is
     * <pre>
     *     YRPR
     * </pre>
     * then this method will return 2.
     *
     * @param guess is the actual guess that we evaluate
     * @return the number of good colors not in pos
     */
//START SNIPPET Guess_ch05_nrOfPartialMatches
    public int nrOfPartialMatches(Guess guess) {
        assertCompatibility(guess);
        int count = 0;
        for (int i = 0; i < nrOfColumns(); i++) {
            for (int j = 0; j < nrOfColumns(); j++) {
                if (i != j &&
                        guess.getColor(i) == this.getColor(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Count the number of colors that are correct and are in pos.
     *
     * @param guess is the actual guess that we evaluate
     * @return the number of colors that match in pos
     */
    public int nrOfFullMatches(Guess guess) {
        assertCompatibility(guess);
        int count = 0;
        for (int i = 0; i < nrOfColumns(); i++) {
            if (guess.getColor(i) == this.getColor(i)) {
                count++;
            }
        }
        return count;
    }
//END SNIPPET

//START SNIPPET Guess_ch05_isUnique

    /**
     * @return true if the guess does not contain any color more than once
     */
    public boolean isUnique() {
        if (uniquenessWasNotCalculated) {
            final var alreadyPresent = new HashSet<Color>();
            unique = true;
            for (final var color : colors) {
                if (alreadyPresent.contains(color)) {
                    unique = false;
                    break;
                }
                alreadyPresent.add(color);
            }
            uniquenessWasNotCalculated = false;
        }
        return unique;
    }
//END SNIPPET

    //START SNIPPET Guess_ch05_equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Guess)) return false;
        var guess = (Guess) o;
        return Arrays.equals(colors, guess.colors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(colors);
    }

    @Override
    public String toString() {
        if (this == none) {
            return "none";
        } else {
            String s = "";
            for (int i = colors.length - 1; i >= 0; i--) {
                s += colors[i];
            }
            return s;
        }
    }
    // END SNIPPET
}
