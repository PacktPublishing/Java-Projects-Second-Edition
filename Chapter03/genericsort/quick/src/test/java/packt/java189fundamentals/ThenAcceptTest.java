package packt.java189fundamentals;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptTest {

    private static void println(Object o) {
        System.out.println(Thread.currentThread().getName()+": " + o);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void thenAcceptTest() {
        var future = CompletableFuture.supplyAsync(
                () -> {
                    sleep(1000);
                    println("We slept.");
                    return 1;
                }
        ).thenAccept(i -> println("The value is " + i));
        println("We have the completable future and now we start to wait for it");
        future.join();
        println("The future is here");
    }
}
