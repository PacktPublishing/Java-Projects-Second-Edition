//START SNIPPET interface_Sort_generic
package packt.java189fundamentals.ch03.generic;

public interface Sort<E> {
    void sort(Sortable<E> collection);
}
//END SNIPPET