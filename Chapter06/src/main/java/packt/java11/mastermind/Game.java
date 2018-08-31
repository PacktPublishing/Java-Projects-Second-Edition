package packt.java11.mastermind;

/**
 * A Game maintains a secret and a table and when there is a new guess it stores it setting the
 * getColor and colors matched.
 */
public class Game {

    final Table table;
    final private Guess secret;

    public void setFinished() {
        this.finished = true;
    }

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
// START SNIPPET addGuess
    public Row addGuess(Guess guess, int full, int partial) {
        assertNotFinished();
        final Row row = new Row(guess, full, partial);
        table.addRow(row);
        if (itWasAWinningGuess(full)) {
            finished = true;
        }
        return row;
    }
// END SNIPPET
    public Row addNewGuess(Guess guess) {
        final int full = secret.nrOfFullMatches(guess);
        final int partial = secret.nrOfPartialMatches(guess);
        return addGuess(guess, full, partial);
    }

    private void assertNotFinished() {
        if (isFinished()) {
            throw new IllegalArgumentException("You can not guess on a finished game.");
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public Table getTable() {
        return table;
    }
}
