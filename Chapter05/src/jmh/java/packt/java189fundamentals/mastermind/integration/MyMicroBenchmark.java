package packt.java189fundamentals.mastermind.integration;

import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;


public class MyMicroBenchmark {

    public static void main(String... args) throws IOException, RunnerException {
        final var N = 50;
        final var start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            new SimpleGamePlayer().play();
            //new ParallelGamePlayer(1, 1).play();
        }
        var d_16_1 = System.nanoTime() - start;
        System.out.println(d_16_1);
        System.out.println(((double) 1_000_000_000 * N) / d_16_1);
    }
}