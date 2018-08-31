// START SNIPPET ArrayListSwapper_simple
package packt.java189fundamentals.ch03.main.bubble.simple;

import packt.java189fundamentals.ch03.Swapper;

import java.util.ArrayList;

public class ArrayListSwapper implements Swapper {
    final private ArrayList actualNames;

    ArrayListSwapper(ArrayList actualNames) {
        this.actualNames = actualNames;
    }

    @Override
    public void swap(int i, int j) {
        Object tmp = actualNames.get(i);
        actualNames.set(i, actualNames.get(j));
        actualNames.set(j, tmp);
    }
}
// END SNIPPET