package packt.java189fundamentals.ch03.qsort;

import packt.java189fundamentals.ch03.Swapper;
import packt.java189fundamentals.ch03.generic.Sortable;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FJQuickSort<E> {

    final Partitioner<E> partitioner;
    final private Comparator<E> comparator;
    final private Swapper swapper;

    public FJQuickSort(Comparator<E> comparator, Swapper swapper) {
        this.comparator = comparator;
        this.swapper = swapper;
        partitioner = new Partitioner<>(comparator, swapper);
    }

//START SNIPPET FJQuickSort_ch03
    public void qsort(Sortable<E> sortable, int start, int end) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new RASort(sortable, start, end));
    }

    private class RASort extends RecursiveAction {

        final Sortable<E> sortable;
        final int start, end;

        public RASort(Sortable<E> sortable, int start, int end) {
            this.sortable = sortable;
            this.start = start;
            this.end = end;
        }

        public void compute() {
            if (start < end) {
                final E pivot = sortable.get(start);
                int cutIndex = partitioner.partition(sortable, start, end, pivot);
                if (cutIndex == start) {
                    cutIndex++;
                }
                RecursiveAction left = new RASort(sortable, start, cutIndex - 1);
                RecursiveAction right = new RASort(sortable, cutIndex, end);
                invokeAll(left, right);
                left.join();
                right.join();
            }
        }
    }
//END SNIPPET
}
