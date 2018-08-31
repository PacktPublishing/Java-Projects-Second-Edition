// START SNIPPET PartitionerTest_phase1_head
package packt.java189fundamentals.ch03.qsort.phase1;

// SNIPPET SKIP TILL "^//"

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import packt.java189fundamentals.ch03.Swapper;
import packt.java189fundamentals.ch03.support.ArraySwapper;
import packt.java189fundamentals.ch03.support.ArrayWrapper;
// ... imports deleted from print ...

public class PartitionerTest {
// END SNIPPET

    // START SNIPPET assertSmallElements
    private void assertSmallElements(Integer[] array, int cutIndex, Integer pivot) {
        for (int i = 0; i < cutIndex; i++) {
            Assert.assertTrue(array[i] < pivot);
        }
    }
    // END SNIPPET

    // START SNIPPET assertLargeElements
    private void assertLargeElements(Integer[] array, int cutIndex, Integer pivot) {
        for (int i = cutIndex; i < array.length; i++) {
            Assert.assertTrue(pivot <= array[i]);
        }
    }

    // END SNIPPET
    // START SNIPPET partitionsIntArray_phase1
    @Test
    // SNIPPET SKIP 1 LINE
    @Ignore
    public void partitionsIntArray() {
        final var partitionThis = new Integer[]{0, 7, 6};
        final var swapper = new ArraySwapper<>(partitionThis);
        final var partitioner =
                new Partitioner<Integer>(
                        (a, b) -> a < b ? -1 : a > b ? +1 : 0,
                        swapper);
        final Integer pivot = 6;
        final int cutIndex = partitioner.partition(
                new ArrayWrapper<>(partitionThis), 0, 2, pivot);
        Assert.assertEquals(1, cutIndex);
        assertSmallElements(partitionThis, cutIndex, pivot);
        assertLargeElements(partitionThis, cutIndex, pivot);
    }
    // END SNIPPET

}
