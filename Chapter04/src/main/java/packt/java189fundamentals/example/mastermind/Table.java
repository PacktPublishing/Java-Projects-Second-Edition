package packt.java189fundamentals.example.mastermind;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the state of the table of the game.
 */
// START SNIPPET Table_ch04
public class Table {
    final ColorManager manager;
    /**
     * The number of columns in the table.
     */
    final int nrColumns;
    /**
     * The rows of the table.
     */

    final List<Row> rows;

    public Table(int nrColumns, ColorManager manager) {
        this.nrColumns = nrColumns;
        this.rows = new LinkedList<>();
        this.manager = manager;
    }

    public void addRow(Row row) {
        rows.add(row);
    }
}
//END