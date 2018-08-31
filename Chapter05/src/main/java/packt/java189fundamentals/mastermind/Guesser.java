package packt.java189fundamentals.mastermind;

public abstract class Guesser {
    protected final Table table;
    protected final ColorManager manager;

    public Guesser(Table table) {
        this.table = table;
        this.manager = table.manager;
        nextGuess = firstGuess();
    }

    abstract protected Guess firstGuess();

    protected Guess nextGuess;

    /**
     * get the next guess, without checking any matching
     *
     * @return the next guess
     */
    protected Guess nextGuess() {
        var currentGuess = nextGuess;
        nextGuess = currentGuess.nextGuess(manager);
        return currentGuess;
    }

    /**
     * A guess matches if all rows in the table match the guess.
     *
     * @param guess to match against the rows
     * @return true if all rows match
     */
    public boolean guessMatch(Guess guess) {
        for (final var row : table.rows) {
            if (!row.matches(guess)) {
                return false;
            }
        }
        return true;
    }

    private boolean guessDoesNotMatch(Guess guess) {
        return !guessMatch(guess);
    }

    /**
     * Create a new Row object that contains a guess that matches all guesses and the
     * responses to them that are on the table.
     *
     * @return the new Row to be added to the table along with the feedback afterwards.
     */
    public Guess guess() {
        var guess = nextGuess();
        while (guess != Guess.none && guessDoesNotMatch(guess)) {
            guess = nextGuess();
        }
        return guess;
    }

}
