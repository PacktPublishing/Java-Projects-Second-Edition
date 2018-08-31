// START SNIPPET ArrayListSortable_generic
package packt.java189fundamentals.ch03.main.bubble.generic;


import packt.java189fundamentals.ch03.generic.Sortable;

import java.util.ArrayList;

public class ArrayListSortable<E> implements Sortable {
    final private ArrayList<E> actualNames;

    ArrayListSortable(ArrayList<E> actualNames) {
        this.actualNames = actualNames;
    }

    @Override
    public E get(int i) {
        return actualNames.get(i);
    }

    @Override
    public int size() {
        return actualNames.size();
    }
}
//END SNIPPET