package packt.java189fundamentals.example.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class GeneralGuesserTest {

    static final int NR_COLORS = 6;
    static final int NR_COLUMNS = 4;

    @Test
    public void generateAllGuesses() {
        int numberOfGuesses = 0;
        ColorManager manager = new ColorManager(NR_COLORS);
        Table table = new Table(NR_COLUMNS,manager);
        Guesser guesser = new GeneralGuesser(table);
        while (guesser.nextGuess() != Guesser.none) {
            numberOfGuesses++;
        }
        Assert.assertEquals(6*6*6*6,numberOfGuesses);
    }
}
