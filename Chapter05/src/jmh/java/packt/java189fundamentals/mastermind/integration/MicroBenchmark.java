package packt.java189fundamentals.mastermind.integration;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

// START SNIPPET ch05_MicroBenchmark
public class MicroBenchmark {

    public static void main(String... args)
        throws RunnerException {
        var opt = new OptionsBuilder()
            .include(MicroBenchmark.class.getSimpleName())
            .forks(1)
            .build();

        new Runner(opt).run();
    }

    @Benchmark
    @Fork(1)
    public void playParallel(ThreadsAndQueueSizes t3qs) {
        int nrThreads = Integer.valueOf(t3qs.nrThreads);
        int queueSize = Integer.valueOf(t3qs.queueSize);
        new ParallelGamePlayer(nrThreads, queueSize).play();
    }

    @Benchmark
    @Fork(1)
    public void playSimple() {
        new SimpleGamePlayer().play();
    }

    @State(Scope.Benchmark)
    public static class ThreadsAndQueueSizes {
        @Param(value = {"1", "4", "8"})
        String nrThreads;
        @Param(value = {"-1", "1", "10", "100", "1000000"})
        String queueSize;
    }
}
// END SNIPPET