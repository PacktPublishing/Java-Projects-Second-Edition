package packt.java189fundamentals.mastermind;

/**
 * A Game maintains a secret and a table and when there is a new guess it stores it setting the
 * getColor and colors matched.
 */
public class Game {

    final Table table;
    final private Guess secret;
    boolean finished = false;
    final int nrOfColumns;

    public Game(Table table, Guess secret) {
        this.table = table;
        this.secret = secret;
        this.nrOfColumns = this.secret.nrOfColumns();
    }

    private boolean itWasAWinningGuess(int positionMatch) {
        return positionMatch == nrOfColumns;
    }


    public Row addNewGuess(Guess guess) {
        assertNotFinished();
        final int full = secret.nrOfFullMatches(guess);
        final int partial = secret.nrOfPartialMatches(guess);
        final var row = new Row(guess,full,partial);
        table.addRow(row);
        if (itWasAWinningGuess(full)) {
            finished = true;
        }
        return row;
    }

    private void assertNotFinished() {
        if (isFinished()) {
            throw new IllegalArgumentException("You can not guess on a finished game.");
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
