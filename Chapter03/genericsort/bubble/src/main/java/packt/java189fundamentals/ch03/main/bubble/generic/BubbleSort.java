// START SNIPPET BubbleSort_generic
package packt.java189fundamentals.ch03.main.bubble.generic;

//SNIPPET SKIP TILL "imports were removed from printout"

import packt.java189fundamentals.ch03.Swapper;
import packt.java189fundamentals.ch03.generic.Sort;
import packt.java189fundamentals.ch03.generic.SortSupport;
import packt.java189fundamentals.ch03.generic.Sortable;

import java.util.Comparator;
// ... imports were removed from printout ...

public class BubbleSort<E> implements Sort<E>, SortSupport<E> {
    private Comparator<E> comparator = null;
    private Swapper swapper = null;

    @Override
    public void sort(Sortable<E> collection) {
        var n = collection.size();
        while (n > 1) {
            for (int j = 0; j < n - 1; j++) {
                if (comparator.compare(collection.get(j),
                        collection.get(j + 1)) > 0) {
                    swapper.swap(j, j + 1);
                }
            }
            n--;
        }
    }

    @Override
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void setSwapper(Swapper swapper) {
        this.swapper = swapper;
    }
}
// END SNIPPET