package packt.java189fundamentals.mastermind;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents the state of the table of the game.
 */
public class Table {
    final ColorManager manager;
    final int nrColumns;
    final List<Row> rows;

    public Table(int nrColumns, ColorManager manager) {
        this.nrColumns = nrColumns;
        this.rows = new CopyOnWriteArrayList<>();
        this.manager = manager;
    }

    public int nrOfColumns() {
        return nrColumns;
    }

    public void addRow(Row row) {
        rows.add(row);
    }
}
