// START SNIPPET ArrayListSortable_simple
package packt.java189fundamentals.ch03.main.bubble.simple;

import packt.java189fundamentals.ch03.Sortable;

import java.util.ArrayList;

public class ArrayListSortable implements Sortable {
    final private ArrayList actualNames;

    ArrayListSortable(ArrayList actualNames) {
        this.actualNames = actualNames;
    }

    @Override
    public Object get(int i) {
        return actualNames.get(i);
    }

    @Override
    public int size() {
        return actualNames.size();
    }
}
//END SNIPPET