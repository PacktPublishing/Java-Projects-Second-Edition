package packt.java11.mastermind;

/**
 * One row on the table. It contains the guesses at getColor and also the
 * number of matching colors and getColor.
 */
public class Row {
    protected final Guess guess;
    protected int full;

    public void setFull(int full) {
        this.full = full;
    }

    public void setPartial(int partial) {
        this.partial = partial;
    }

    protected int partial;
    public static final Row none = new Row(Guess.none, 0, 0);

    public Row(Guess guess, int full, int partial) {
        this.guess = guess;
        this.full = full;
        this.partial = partial;
    }

    protected Row(Row cloneFrom) {
        this(cloneFrom.guess, cloneFrom.full, cloneFrom.partial);
    }

    public boolean matches(Guess guess) {
        return this.guess.nrOfPartialMatches(guess) == partial &&
                this.guess.nrOfFullMatches(guess) == full;
    }

    public int nrOfColumns() {
        return guess.nrOfColumns();
    }

    public String toString() {
        return guess.toString() + " " + full + "/" + partial;
    }

    public int getFull() {
        return full;
    }

    public int getPartial() {
        return partial;
    }

    public Color getColor(int column) {
        return guess.getColor(column);
    }

}
