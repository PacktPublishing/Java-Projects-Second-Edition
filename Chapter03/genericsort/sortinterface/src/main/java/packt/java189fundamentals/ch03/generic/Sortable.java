// START SNIPPET interface_Sortable_generic
package packt.java189fundamentals.ch03.generic;

public interface Sortable<E> {
    E get(int i);
    int size();
}
//END SNIPPET