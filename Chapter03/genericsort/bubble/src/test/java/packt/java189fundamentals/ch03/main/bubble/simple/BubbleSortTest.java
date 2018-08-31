// START SNIPPET BubbleSortTest_1
package packt.java189fundamentals.ch03.main.bubble.simple;

// import statements are deleted from the print for brevity

import org.junit.Assert;
import org.junit.Test;
import packt.java189fundamentals.ch03.Sortable;
import packt.java189fundamentals.ch03.Swapper;
import packt.java189fundamentals.ch03.main.bubble.BubbleSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BubbleSortTest {
    @Test
    public void canSortStrings() {
        var actualNames = new ArrayList(Arrays.asList(
            "Johnson", "Wilson",
            "Wilkinson", "Abraham", "Dagobert"
        ));
//END SNIPPET

// START SNIPPET BubbleSortTest_2
        var names = new Sortable() {

            @Override
            public Object get(int i) {
                return actualNames.get(i);
            }

            @Override
            public int size() {
                return actualNames.size();
            }
        };
//END SNIPPET
// START SNIPPET BubbleSortTest_3
        class SwapActualNamesArrayElements implements Swapper {
            @Override
            public void swap(int i, int j) {
                final Object tmp = actualNames.get(i);
                actualNames.set(i, actualNames.get(j));
                actualNames.set(j, tmp);
            }
        }
        ;
//END SNIPPET
// START SNIPPET BubbleSortTest_4
        Comparator stringCompare = new Comparator() {
            @Override
            public int compare(Object first, Object second) {
                final String f = (String) first;
                final String s = (String) second;
                return f.compareTo(s);
            }
        };
//END SNIPPET
// START SNIPPET BubbleSortTest_5
        var sort = new BubbleSort();
        sort.setComparator(stringCompare);
        sort.setSwapper(new SwapActualNamesArrayElements());
        sort.sort(names);
//END SNIPPET
// START SNIPPET BubbleSortTest_6
        Assert.assertEquals(List.of(
            "Abraham", "Dagobert",
            "Johnson", "Wilkinson", "Wilson"
        ), actualNames);
//END SNIPPET
    }

    // START SNIPPET canSortStrings2
    @Test
    public void canSortStrings2() {
        var actualNames = new ArrayList(List.of(
            "Johnson", "Wilson",
            "Wilkinson", "Abraham", "Dagobert"
        ));
        var expectedResult = List.of(
            "Abraham", "Dagobert",
            "Johnson", "Wilkinson", "Wilson"
        );
        var names = new ArrayListSortable(actualNames);
        var sort = new BubbleSort();
        sort.setComparator(new StringComparator());
        sort.setSwapper(new ArrayListSwapper(actualNames));
        sort.sort(names);
        Assert.assertEquals(expectedResult, actualNames);
    }
//END SNIPPET

    // START SNIPPET canNotSortMixedElements
    @Test(expected = NonStringElementInCollectionException.class)
    public void canNotSortMixedElements() {
        var actualNames = new ArrayList(Arrays.asList(
            42, "Wilson",
            "Wilkinson", "Abraham", "Dagobert"
        ));
        //... the rest of the code is the same as the previous test
// END SNIPPET
        var names = new ArrayListSortable(actualNames);
        var sort = new BubbleSort();
        sort.setComparator(new StringComparator());
        sort.setSwapper(new ArrayListSwapper(actualNames));
        sort.sort(names);
    }

    @Test(expected = RuntimeException.class)
    public void throwsWhateverComparatorDoes() {
        ArrayList<String> actualNames = new ArrayList(List.of(
            "", "Wilson", "Wilkinson", "Abraham", "Dagobert"
        ));
        Sortable names =
            new ArrayListSortable(actualNames);
        var sort = new BubbleSort();
        sort.setComparator((a, b) -> {
            throw new RuntimeException();
        });
        final Swapper neverInvoked = null;
        sort.setSwapper(neverInvoked);
        sort.sort(names);
    }
}
