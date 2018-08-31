package packt.java189fundamentals.ch03.quick;

import packt.java189fundamentals.ch03.generic.AbstractSort;
import packt.java189fundamentals.ch03.generic.Sortable;
import packt.java189fundamentals.ch03.qsort.FJQuickSort;
import packt.java189fundamentals.ch03.qsort.Qsort;

// START SNIPPET QuickSort
public class QuickSort<E> extends AbstractSort<E> {
    public void sort(Sortable<E> sortable) {
        final var n = sortable.size();
        final var qsort = new Qsort<E>(comparator,swapper);
        qsort.qsort(sortable, 0, n-1);
    }
}
// END SNIPPET
