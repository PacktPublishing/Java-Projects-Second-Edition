//START SNIPPET interface_SortSupport_generic
package packt.java189fundamentals.ch03.generic;


import packt.java189fundamentals.ch03.Swapper;

import java.util.Comparator;

public interface SortSupport<E> {
    void setSwapper(Swapper swap);

    void setComparator(Comparator<E> compare);
}
// END SNIPPET