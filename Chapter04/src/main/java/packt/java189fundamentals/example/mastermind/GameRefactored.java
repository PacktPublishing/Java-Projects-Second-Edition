package packt.java189fundamentals.example.mastermind;

/**
 * A Game maintains a secret and a table and when there is a new guess it stores it setting the
 * position and colors matched.
 */
public class GameRefactored {

    final Table table;
    final private Row secretRow;
    boolean finished = false;
    final int nrOfColumns;

    public GameRefactored(Table table, Color[] secret) {
        this.table = table;
        this.secretRow = new Row(secret);
        this.nrOfColumns = secretRow.nrOfColumns();
    }

    private boolean itWasAWinningGuess(int positionMatch) {
        return positionMatch == nrOfColumns;
    }

    public void addNewGuess(Row row) {
        if (isFinished()) {
            throw new IllegalArgumentException(
                "You can not guess on a finished game.");
        }
        final int positionMatch =
            secretRow.nrMatchingPositions(row.positions);
        final int colorMatch =
            secretRow.nrMatchingColors(row.positions);
        row.setMatch(positionMatch, colorMatch);
        table.addRow(row);
        if (itWasAWinningGuess(positionMatch)) {
            finished = true;
        }
    }

    public boolean isFinished() {
        return finished;
    }
}