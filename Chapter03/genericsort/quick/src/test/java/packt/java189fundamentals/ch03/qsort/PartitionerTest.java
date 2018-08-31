package packt.java189fundamentals.ch03.qsort;

import org.junit.Assert;
import org.junit.Test;
import packt.java189fundamentals.ch03.Swapper;
import packt.java189fundamentals.ch03.support.ArraySwapper;
import packt.java189fundamentals.ch03.support.ArrayWrapper;

public class PartitionerTest {

    // START SNIPPET partitionsIntArray_final
    @Test
    public void partitionsIntArray() {
        final var partitionThis = new Integer[]{0, 7, 6, 2};
        final var swapper = new ArraySwapper<>(partitionThis);
        final var partitioner =
                new Partitioner<Integer>(
                        (a, b) -> a < b ? -1 : a > b ? +1 : 0, swapper);
        final var pivot = 6;
        final var cutIndex = partitioner.partition(
                new ArrayWrapper<>(partitionThis),
                0,
                partitionThis.length - 1,
                pivot);
        Assert.assertEquals(2, cutIndex);
        final var expected = new Integer[]{0, 2, 6, 7};
        Assert.assertArrayEquals(expected, partitionThis);
    }
    // END SNIPPET
}
