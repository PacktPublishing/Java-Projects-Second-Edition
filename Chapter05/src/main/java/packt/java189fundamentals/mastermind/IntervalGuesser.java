package packt.java189fundamentals.mastermind;

import java.util.concurrent.BlockingQueue;
// START SNIPPET IntervalGuesser_ch05
public class IntervalGuesser extends UniqueGuesser implements Runnable {
    private final Guess start;

    private final Guess end;
    private Guess lastGuess;
    private final BlockingQueue<Guess> guessQueue;

    public IntervalGuesser(Table table,
                           Guess start,
                           Guess end,
                           BlockingQueue<Guess> guessQueue) {
        super(table);
        this.start = start;
        this.end = end;
        this.lastGuess = start;
        this.guessQueue = guessQueue;
        nextGuess = start;
    }

    @Override
    public void run() {
        Thread.currentThread()
            .setName("guesser [" + start + "," + end + "]");
        var guess = guess();
        try {
            while (guess != Guess.none) {
                guessQueue.put(guess);
                guess = guess();
            }
        } catch (InterruptedException ignored) {
        }
    }


    @Override
    protected Guess nextGuess() {
        var guess = super.nextGuess();
        if (guess.equals(end)) {
            guess = Guess.none;
        }
        lastGuess = guess;
        return guess;
    }

    public String toString() {
        return "[" + start + "," + end + "]";
    }
}
//END SNIPPET
