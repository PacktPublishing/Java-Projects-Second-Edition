package packt.java189fundamentals.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class UniqueGuesserTest {

    private static final int NR_COLORS = 6;
    private static final int NR_COLUMNS = 4;

    @Test
    public void generateAllGuesses() {
        int numberOfGuesses = 0;
        final var manager = new ColorManager(NR_COLORS, Color::new);
        final var table = new Table(NR_COLUMNS, manager);
        final var guesser = new UniqueGuesser(table);
        while (guesser.nextGuess() != Guess.none) {
            numberOfGuesses++;
        }
        Assert.assertEquals(6 * 5 * 4 * 3, numberOfGuesses);
    }

}
