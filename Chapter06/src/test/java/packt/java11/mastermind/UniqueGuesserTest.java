package packt.java11.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class UniqueGuesserTest {

    private static final int NR_COLORS = 6;
    private static final int NR_COLUMNS = 4;
    private static final ColorManager manager = new ColorManager(NR_COLORS, Color::new);

    @Test
    public void generateAllGuesses() {
        int numberOfGuesses = 0;
        Table table = new Table(NR_COLUMNS, manager);
        Guesser guesser = new UniqueGuesser(table);
        while (guesser.nextGuess() != Guess.none) {
            numberOfGuesses++;
        }
        Assert.assertEquals(6 * 5 * 4 * 3, numberOfGuesses);
    }

    @Test
    public void keepsGettingTillItBleedsOut() {
        int numberOfGuesses = 0;
        ColorManager manager = new ColorManager(NR_COLORS, Color::new);
        Table table = new Table(NR_COLUMNS, manager);
        Guesser guesser = new UniqueGuesser(table);
        while (guesser.guess() != Guess.none) {
            numberOfGuesses++;
        }
    }
}
