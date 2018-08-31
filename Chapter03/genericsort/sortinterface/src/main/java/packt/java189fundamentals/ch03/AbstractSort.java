package packt.java189fundamentals.ch03;

import java.util.Comparator;

public abstract class AbstractSort implements Sort, SortSupport {
    protected Comparator comparator = null;
    protected Swapper swapper = null;

    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void setSwapper(Swapper swapper) {
        this.swapper = swapper;
    }
}
