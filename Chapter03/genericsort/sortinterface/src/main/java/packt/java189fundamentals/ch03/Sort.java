package packt.java189fundamentals.ch03;

/**
 * Sort a collection that contains elements. The primitive operations 'swap' and 'compare' have to be
 * implemented by the caller and provided by means of class implementations or lambda to the actual sorter
 * via the setters before calling the method {@code sort()}.
 *
 */
//START SNIPPET interface_Sort
public interface Sort {
    void sort(Sortable collection);
}
//END SNIPPET