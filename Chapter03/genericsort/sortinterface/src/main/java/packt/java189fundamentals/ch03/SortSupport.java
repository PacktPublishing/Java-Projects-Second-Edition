package packt.java189fundamentals.ch03;

import java.util.Comparator;

//START SNIPPET interface_SortSupport
public interface SortSupport {
    void setSwapper(Swapper swap);

    void setComparator(Comparator compare);
}
// END SNIPPET