package packt.java189fundamentals.ch03.main.bubble.generic;

import org.junit.Assert;
import org.junit.Test;
import packt.java189fundamentals.ch03.Swapper;
import packt.java189fundamentals.ch03.generic.Sortable;

import java.util.ArrayList;
import java.util.List;

public class BubbleSortTest {
    //START SNIPPET canSortString_generics
    @Test
    public void canSortStrings() {
        var actualNames = new ArrayList<>(List.of(
            "Johnson", "Wilson",
            "Wilkinson", "Abraham", "Dagobert"
        ));
        var expectedResult = List.of(
            "Abraham", "Dagobert",
            "Johnson", "Wilkinson", "Wilson"
        );
        Sortable<String> names =
            new ArrayListSortable<>(actualNames);
        var sort = new BubbleSort<String>();
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArrayListSwapper<>(actualNames));
        sort.sort(names);
        Assert.assertEquals(expectedResult, actualNames);
    }

    //END SNIPPET
/*
// START SNIPPET throwsWhateverComparatorDoes_syntax
    @Test
    public void throwsWhateverComparatorDoes() {
        final ArrayList<String> actualNames =
            new ArrayList<>(List.of(
                42, "Wilson"
            ));
        final var names = new ArrayListSortable<>(actualNames);
        final var sort = new BubbleSort<>();
        final var exception = new RuntimeException();
        sort.setComparator((a, b) -> {
            throw exception;
        });
        final Swapper neverInvoked = null;
        sort.setSwapper(neverInvoked);
        try {
            sort.sort(names);
        } catch (Exception e) {
            Assert.assertSame(exception, e);
            return;
        }
        Assert.fail();
    }
// END SNIPPET
*/
// START SNIPPET throwsWhateverComparatorDoes
    @Test
    public void throwsWhateverComparatorDoes() {
        final var actualNames =
            new ArrayList<>(List.of(
                "", "Wilson"
            ));
        final var names = new ArrayListSortable<>(actualNames);
        final var sort = new BubbleSort<>();
        final var exception = new RuntimeException();
        sort.setComparator((a, b) -> {
            throw exception;
        });
        final Swapper neverInvoked = null;
        sort.setSwapper(neverInvoked);
        try {
            sort.sort(names);
        } catch (Exception e) {
            Assert.assertSame(exception, e);
            return;
        }
        Assert.fail();
    }
// END SNIPPET
}
