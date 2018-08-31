package packt.java189fundamentals.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Completable {

    //START SNIPPET CompletableFuture_ch05
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var future = CompletableFuture.supplyAsync(() ->
                {
                    var negative = true;
                    var pi = 0.0;
                    for (int i = 3; i < 100000; i += 2) {
                        if (negative)
                            pi -= (1.0 / i);
                        else
                            pi += (1.0 / i);
                        negative = !negative;
                    }
                    pi += 1.0;
                    pi *= 4.0;
                    return pi;
                }
        ).thenAcceptAsync(piCalculated -> System.out.println("pi is " + piCalculated));
        System.out.println("All is scheduled");
        future.get();
    }
// END SNIPPET
}
