//START SNIPPET ch04_Guesser_head
package packt.java189fundamentals.example.mastermind;

public abstract class Guesser {
    protected final Table table;
    private final ColorManager manager;
    protected final Color[] lastGuess;
    public static final Color[] none = new Color[]{Color.none};

    public Guesser(Table table) {
        this.table = table;
        this.lastGuess = new Color[table.nrColumns];
        this.manager = table.manager;
    }
// END SNIPPET
// START SNIPPET ch04_Guesser_setFirstGuess
    abstract protected void setFirstGuess();
//END SNIPPET



    /**
     * get the next guess, without checking any matching
     *
     * @return the next guess
     */
    // START SNIPPET ch04_Guesser_nextGuess
    protected Color[] nextGuess() {
        if (lastGuess[0] == null) {
            setFirstGuess();
            return lastGuess;
        } else {
            return nextNonFirstGuess();
        }
    }
    // END SNIPPET

    // START SNIPPET ch04_Guesser_nextNonFirstGuess
    private Color[] nextNonFirstGuess() {
        int i = 0;
        boolean guessFound = false;
        while (i < table.nrColumns && !guessFound) {
            if (manager.thereIsNextColor(lastGuess[i])) {
                lastGuess[i] = manager.nextColor(lastGuess[i]);
                guessFound = true;
            } else {
                lastGuess[i] = manager.firstColor();
                i++;
            }
        }
        if (guessFound) {
            return lastGuess;
        } else {
            return none;
        }
    }
    // END SNIPPET

    // START SNIPPET guessMatch_ch04

    /**
     * A guess matches if all rows in the table matches the guess.
     *
     * @param guess to match against the rows
     * @return true if all rows match
     */
    private boolean guessMatch(Color[] guess) {
        for (Row row : table.rows) {
            if (!row.guessMatches(guess)) {
                return false;
            }
        }
        return true;
    }
    //END SNIPPET

    private boolean guessDoesNotMatch(Color[] guess) {
        return !guessMatch(guess);
    }

    /**
     * Create a new Row object that contains a guess that matches all guesses and the
     * responses to them that are on the table.
     *
     * @return the new Row to be added to the table along with the feedback afterwards.
     */
    // START SNIPPET ch04_Guesser_guess
    public Row guess() {
        Color[] guess = nextGuess();
        while (guess != none && guessDoesNotMatch(guess)) {
            guess = nextGuess();
        }
        if (guess == none) {
            return Row.none;
        } else {
            return new Row(guess);
        }
    }
    // END SNIPPET
}
