package packt.java189fundamentals.ch03.main.bubble.simple;

import java.util.Comparator;
// START SNIPPET SafeStringComparator
public class SafeStringComparator implements Comparator {

    @Override
    public int compare(Object first, Object second) {
        try {
            final String f = (String) first;
            final String s = (String) second;
            return f.compareTo(s);
        } catch (ClassCastException cce) {
            throw new NonStringElementInCollectionException(
                "There are mixed elements in the collection.", cce);
        }
    }
}
// END SNIPPET