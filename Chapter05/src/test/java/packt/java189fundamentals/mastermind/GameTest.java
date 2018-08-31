package packt.java189fundamentals.mastermind;

import org.junit.Test;
import packt.java189fundamentals.mastermind.lettered.LetteredColorFactory;

public class GameTest {

    private static final int NR_COLORS = 6;
    private static final int NR_COLUMNS = 4;
    private Game game;
    private Guess secret;

    private void createTestGame() {
        final var manager = new ColorManager(NR_COLORS, new LetteredColorFactory());
        final var table = new Table(NR_COLUMNS, manager);
        final var colors = new Color[NR_COLUMNS];
        var color = manager.firstColor();
        for (int i = 0; i < NR_COLUMNS; i++) {
            colors[i] = color;
            color = manager.nextColor(color);
        }
        secret = new Guess(colors);
        game = new Game(table, secret);
    }

    @Test
    public void canAddNewGuess() {
        createTestGame();
        game.addNewGuess(secret);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionForFinishedGame() {
        createTestGame();
        game.addNewGuess(secret);
        game.addNewGuess(secret);
    }
}
