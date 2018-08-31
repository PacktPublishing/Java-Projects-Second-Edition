package packt.java189fundamentals.ch03.quick;

import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;
import packt.java189fundamentals.ch03.qsort.FJQuickSort;
import packt.java189fundamentals.ch03.qsort.NonRecursiveQuickSort;
import packt.java189fundamentals.ch03.qsort.Qsort;
import packt.java189fundamentals.ch03.support.ArraySwapper;
import packt.java189fundamentals.ch03.support.ArrayWrapper;

import java.util.Random;

public class SimpleSpeedTest {
    final static int N = 10_000_000;
    final Double[] testData = new Double[N];

    @Before
    public void createRandomData() {
        Random rnd = new Random(0);
        for (int i = 0; i < N; i++) {
            testData[i] = rnd.nextDouble();
        }
    }

    private void assertSorted() {
        for (int i = 1; i < N; i++) {
            var failed = false;
            double a, b;
            if ((a = testData[i - 1]) > (b = testData[i])) {
                System.out.println(i - 1 + ". " + a);
                System.out.println(i + ". " + b);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(i - 1 + ". " + testData[i - 1]);
                System.out.println(i + ". " + testData[i]);
                System.out.println("------------------------------");
                failed = true;
            }
            if (failed)
                throw new AssertionFailedError();
        }
    }

    @Test
    public void measureTimeWithRecursiveQsort() {
        long start = System.nanoTime();
        Qsort<Double> qsort = new Qsort<>(Double::compareTo, new ArraySwapper<Double>(testData));
        qsort.qsort(new ArrayWrapper<>(testData), 0, N - 1);
        long runTime = System.nanoTime() - start;
        System.out.println("qs run time " + runTime / 1000000);
        assertSorted();
    }

    @Test
    public void measureTimeWithNonRecursiveQsort() {
        long start = System.nanoTime();
        NonRecursiveQuickSort<Double> qsort = new NonRecursiveQuickSort<>(Double::compareTo, new ArraySwapper<Double>(testData));
        qsort.qsort(new ArrayWrapper<>(testData), 0, N - 1);
        long runTime = System.nanoTime() - start;
        System.out.println("nr run time " + runTime / 1000000);
        assertSorted();
    }

    @Test
    public void measureTimeWithForkJoinQsort() {
        long start = System.nanoTime();
        var qsort = new FJQuickSort<>(Double::compareTo, new ArraySwapper<>(testData));
        qsort.qsort(new ArrayWrapper<>(testData), 0, N - 1);
        long runTime = System.nanoTime() - start;
        System.out.println("fj run time " + runTime / 1000000);
        assertSorted();
    }
}
