package packt.java189fundamentals.ch03.main.bubble;

import packt.java189fundamentals.ch03.Sort;
import packt.java189fundamentals.ch03.SortSupport;
import packt.java189fundamentals.ch03.Sortable;
import packt.java189fundamentals.ch03.Swapper;
// START SNIPPET BubbleSort
// ...
import java.util.Comparator;

public class BubbleSort implements Sort, SortSupport {
    @Override
    public void sort(Sortable collection) {
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
// ...
// END SNIPPET

// START SNIPPET BubbleSortSetters
    private Comparator comparator = null;

    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    private Swapper swapper = null;

    @Override
    public void setSwapper(Swapper swapper) {
        this.swapper = swapper;
    }
// END SNIPPET
}
