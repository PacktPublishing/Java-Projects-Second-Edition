package smallexamples;

import org.junit.Test;

/**
 * Created by verhasp on 2016. 09. 17..
 */
public class ThreadTest {

    @Test
    public void printMainThreadName() {
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void printAllThreads() {
        var threads = new Thread[100];
        Thread.enumerate(threads);
        for (Thread t : threads) {
            if (t != null)
                System.out.println(t.getName());
        }
    }
}
