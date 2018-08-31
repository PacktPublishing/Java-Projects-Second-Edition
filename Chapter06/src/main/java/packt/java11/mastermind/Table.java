package packt.java11.mastermind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents the state of the table of the game.
 */
@Singleton
public class Table {
    final ColorManager manager;
    final int nrColumns;
    final List<Row> rows;
    private static final Logger log = LoggerFactory.getLogger(Table.class);

    public Color getColor(int row, int column) {
        log.debug("Size of thetable {}", rows.size());
        log.debug("Fetching color #{} from row {}", column, row);
        return rows.get(row).getColor(column);
    }

    public int getFull(int row) {
        return rows.get(row).getFull();
    }

    public int getPartial(int row) {
        return rows.get(row).getPartial();
    }

    public void setFull(int row, int full) {
        rows.get(row).setFull(full);
    }

    public void setPartial(int row, int partial) {
        rows.get(row).setPartial(partial);
    }

    @Inject
    public Table(@Named("nrColumns") int nrColumns, ColorManager manager) {
        log.debug("table is created with {} columns and {} colorManager", nrColumns, manager);
        this.nrColumns = nrColumns;
        this.rows = new CopyOnWriteArrayList<>();
        this.manager = manager;
    }

    public ColorManager getManager() {
        return manager;
    }

    public int nrOfColumns() {
        return nrColumns;
    }

    public int nrOfRows() {
        return rows.size();
    }

    public void addRow(Row row) {
        rows.add(row);
    }
}
