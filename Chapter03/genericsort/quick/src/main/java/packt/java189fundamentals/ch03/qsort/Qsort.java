// START SNIPPET Qsort
package packt.java189fundamentals.ch03.qsort;

// SNIPPET SKIP TILL "^//"
import packt.java189fundamentals.ch03.generic.Sortable;
import packt.java189fundamentals.ch03.Swapper;

import java.util.Comparator;
// ... imports are deleted from print ...
public class Qsort<E> {
    final private Comparator<E> comparator;
    final private Swapper swapper;
// SNIPPET SKIP TILL "^//"

    public Qsort(Comparator<E> comparator, Swapper swapper) {
        this.comparator = comparator;
        this.swapper = swapper;
    }
// ... constructor setting fields deleted from print ...
    public void qsort(Sortable<E> sortable, int start, int end) {
        if (start < end) {
            final var pivot = sortable.get(start);
            final var partitioner = new Partitioner<E>(comparator, swapper);
            var cutIndex = partitioner.partition(sortable, start, end, pivot);
            if (cutIndex == start) {
                cutIndex++;
            }
            qsort(sortable, start, cutIndex - 1);
            qsort(sortable, cutIndex, end);
        }
    }
}
//END SNIPPET