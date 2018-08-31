package packt.java189fundamentals.ch03.quick;

import packt.java189fundamentals.ch03.generic.AbstractSort;
import packt.java189fundamentals.ch03.generic.Sortable;
import packt.java189fundamentals.ch03.qsort.FJQuickSort;
// START SNIPPET FQuickSort
public class FQuickSort<E> extends AbstractSort<E> {
    public void sort(Sortable<E> sortable) {
        int n = sortable.size();
        FJQuickSort<E> qsort = new FJQuickSort<>(comparator,swapper);
        qsort.qsort(sortable, 0, n-1);
    }
}
// END SNIPPET
