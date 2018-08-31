package packt.java189fundamentals.ch03.quick;

import org.junit.Assert;
import org.junit.Test;
import packt.java189fundamentals.ch03.support.ArraySwapper;
import packt.java189fundamentals.ch03.support.ArrayWrapper;

public class QuickSortTest {

    // START SNIPPET QuickSortTest_canSortStrings
    @Test
    public void canSortStrings() {
        final var actualNames = new String[]{
                "Johnson", "Wilson",
                "Wilkinson", "Abraham", "Dagobert"
        };
        final var expected = new String[]{"Abraham",
                "Dagobert", "Johnson", "Wilkinson", "Wilson"};
        var sort = new QuickSort<String>();
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArraySwapper<>(actualNames));
        sort.sort(new ArrayWrapper<>(actualNames));
        Assert.assertArrayEquals(expected, actualNames);
    }
    // END SNIPPET

    @Test
    public void canSortOne() {
        final String[] actualNames = new String[]{"Abraham"};
        final String[] expected = new String[]{"Abraham"};
        var sort = new FQuickSort<>();
        sort.setComparator((a, b) -> ((String) a).compareTo((String) b));
        sort.setSwapper(new ArraySwapper<String>(actualNames));
        sort.sort(new ArrayWrapper<>(actualNames));
        Assert.assertArrayEquals(expected, actualNames);
    }

    @Test
    public void canSortAlreadySorted() {
        final String[] actualNames = new String[]{"Abraham", "Dagobert"};
        final String[] expected = new String[]{"Abraham", "Dagobert"};
        var sort = new FQuickSort<>();
        sort.setComparator((a, b) -> ((String) a).compareTo((String) b));
        sort.setSwapper(new ArraySwapper<String>(actualNames));
        sort.sort(new ArrayWrapper<>(actualNames));
        Assert.assertArrayEquals(expected, actualNames);
    }
}
