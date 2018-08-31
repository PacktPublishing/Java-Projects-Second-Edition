package packt.java189fundamentals.mastermind.integration;


import packt.java189fundamentals.mastermind.*;
import packt.java189fundamentals.mastermind.lettered.LetteredColorFactory;

import java.util.LinkedList;
import java.util.concurrent.*;

public class ParallelGamePlayer implements Player {

    private static final int NR_COLORS = 10;
    private static final int NR_COLUMNS = 6;
    final ColorManager colorManager = new ColorManager(NR_COLORS, new LetteredColorFactory());
    private final int nrThreads;
    private final BlockingQueue<Guess> guessQueue;
    private ExecutorService executorService;

    public ParallelGamePlayer(int nrThreads, int queueSize) {
        if (queueSize == -1) {
            guessQueue = new LinkedBlockingDeque<Guess>();
        } else {
            guessQueue = new ArrayBlockingQueue<Guess>(nrThreads * queueSize);
        }
        this.nrThreads = nrThreads;
    }

    // START SNIPPET ParallelGamePlayer_play_ch05
    @Override
    public void play() {
        final var table = new Table(NR_COLUMNS, colorManager);
        final var secret = new RandomSecret(colorManager);
        final var secretGuess = secret.createSecret(NR_COLUMNS);
        final var game = new Game(table, secretGuess);
        final var guessers = createGuessers(table);
        final var finalCheckGuesser = new UniqueGuesser(table);
        startAsynchronousGuessers(guessers);
        try {
            while (!game.isFinished()) {
                final var guess = guessQueue.take();
                if (finalCheckGuesser.guessMatch(guess)) {
                    game.addNewGuess(guess);
                }
            }
        } catch (InterruptedException ie) {

        } finally {
            stopAsynchronousGuessers(guessers);
        }
    }
    //END SNIPPET

    // START SNIPPET ParallelGamePlayer_startAsynchronousGuessers_ch05
    private void startAsynchronousGuessers(IntervalGuesser[] guessers) {
        executorService = Executors.newFixedThreadPool(nrThreads);
        for (IntervalGuesser guesser : guessers) {
            executorService.execute(guesser);
        }
    }

    private void stopAsynchronousGuessers(IntervalGuesser[] guessers) {
        executorService.shutdown();
        guessQueue.drainTo(new LinkedList<>());
    }
    //END SNIPPET

    // START SNIPPET ParallelGamePlayer_createGuessers_ch05
    private IntervalGuesser[] createGuessers(Table table) {
        final var colors = new Color[NR_COLUMNS];
        var start = firstIntervalStart(colors);
        final IntervalGuesser[] guessers = new IntervalGuesser[nrThreads];
        for (int i = 0; i < nrThreads - 1; i++) {
            Guess end = nextIntervalStart(colors);
            guessers[i] = new IntervalGuesser(table, start, end, guessQueue);
            start = end;
        }
        guessers[nrThreads - 1] = new IntervalGuesser(table, start, Guess.none, guessQueue);
        return guessers;
    }


    private Guess firstIntervalStart(Color[] colors) {
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorManager.firstColor();
        }
        return new Guess(colors);
    }

    private Guess nextIntervalStart(Color[] colors) {
        final int index = colors.length - 1;
        int step = NR_COLORS / nrThreads;
        if (step == 0) {
            step = 1;
        }
        while (step > 0) {
            if (colorManager.thereIsNextColor(colors[index])) {
                colors[index] = colorManager.nextColor(colors[index]);
                step--;
            } else {
                return Guess.none;
            }
        }
        Guess guess = new Guess(colors);
        while (!guess.isUnique()) {
            guess = guess.nextGuess(colorManager);
        }
        return guess;
    }
    //END SNIPPET
}
