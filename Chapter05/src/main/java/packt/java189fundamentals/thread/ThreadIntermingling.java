package packt.java189fundamentals.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//START SNIPPET ThreadIntermingling_ch05
public class ThreadIntermingling {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final var es = Executors.newFixedThreadPool(2);
        final var t1 = new MyRunnable("t1");
        final var t2 = new MyRunnable("t2");
        final Future<?> f1 = es.submit(t1);
        final Future<?> f2 = es.submit(t2);
        System.out.print("started ");
        var o = f1.get();
        System.out.println("object returned " + o);
        f2.get();
        System.out.println();
        es.submit(t1);
        es.shutdown();
    }

    static class MyRunnable implements Runnable {
        private final String name;

        MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i < 10; i++) {
                System.out.print(name + " " + i + ", ");
            }
        }
    }
}
// END SNIPPET