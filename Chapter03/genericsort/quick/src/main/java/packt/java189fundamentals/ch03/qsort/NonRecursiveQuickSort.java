package packt.java189fundamentals.ch03.qsort;

import packt.java189fundamentals.ch03.generic.Sortable;
import packt.java189fundamentals.ch03.Swapper;

import java.util.Comparator;
import java.util.LinkedList;

// START SNIPPET NonRecursiveQuickSort
public class NonRecursiveQuickSort<E> {
    // SNIPPET SKIP TILL "^//"
    final private Comparator<E> comparator;
    final private Swapper swapper;

    public NonRecursiveQuickSort(Comparator<E> comparator, Swapper swapper) {
        this.comparator = comparator;
        this.swapper = swapper;
    }
// ... same fields and constructor as in Qsort are deleted from print ...

    private static class StackElement {
        final int begin;
        final int fin;

        public StackElement(int begin, int fin) {
            this.begin = begin;
            this.fin = fin;
        }
    }

    public void qsort(Sortable<E> sortable, int start, int end) {
        final var stack = new LinkedList<StackElement>();
        final var partitioner = new Partitioner<E>(comparator, swapper);
        stack.add(new StackElement(start, end));
        var i = 1;
        while (!stack.isEmpty()) {
            var it = stack.remove(0);
            if (it.begin < it.fin) {
                final E pivot = sortable.get(it.begin);
                var cutIndex = partitioner.partition(sortable, it.begin, it.fin, pivot);
                if( cutIndex == it.begin ){
                    cutIndex++;
                }
                stack.add(new StackElement(it.begin, cutIndex - 1));
                stack.add(new StackElement(cutIndex, it.fin));
            }
        }
    }
}
// END SNIPPET