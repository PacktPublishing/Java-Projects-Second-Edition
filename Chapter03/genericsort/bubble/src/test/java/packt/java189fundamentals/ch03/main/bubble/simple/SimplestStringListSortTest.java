package packt.java189fundamentals.ch03.main.bubble.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
// START SNIPPET SimplestStringListSortTest
public class SimplestStringListSortTest {
    @Test
    public void canSortStrings() {
        var actualNames = new ArrayList(Arrays.asList(
                "Johnson", "Wilson",
                "Wilkinson", "Abraham", "Dagobert"
        ));
        Collections.sort(actualNames);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(
                "Abraham", "Dagobert",
                "Johnson", "Wilkinson", "Wilson")),
                actualNames);
    }
}
//END SNIPPET
