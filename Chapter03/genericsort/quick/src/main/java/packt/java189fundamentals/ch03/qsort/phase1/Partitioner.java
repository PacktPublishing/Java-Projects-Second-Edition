package packt.java189fundamentals.ch03.qsort.phase1;

import packt.java189fundamentals.ch03.Swapper;
import packt.java189fundamentals.ch03.generic.Sortable;

import java.util.Comparator;

// START SNIPPET Partitioner_phase1
// SNIPPET SKIP TILL "public int partition"
public class Partitioner<E> {

    private final Comparator<E> comparator;
    private final Swapper swapper;

    public Partitioner(Comparator<E> comparator, Swapper swapper) {
        this.comparator = comparator;
        this.swapper = swapper;
    }

    public int partition(Sortable<E> sortable, int start, int end, E pivot) {
// SNIPPET SKIP 1000 LINES
        return 0;
    }
}
// END SNIPPET