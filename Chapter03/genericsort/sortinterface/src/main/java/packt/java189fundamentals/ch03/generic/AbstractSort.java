package packt.java189fundamentals.ch03.generic;

import packt.java189fundamentals.ch03.Swapper;

import java.util.Comparator;

public abstract class AbstractSort<E> implements Sort<E>, SortSupport<E> {
    protected Comparator<E> comparator = null;
    protected Swapper swapper = null;

    @Override
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void setSwapper(Swapper swapper) {
        this.swapper = swapper;
    }
}
