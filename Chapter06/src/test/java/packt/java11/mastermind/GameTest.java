package packt.java11.mastermind;

import org.junit.Test;
import packt.java11.mastermind.lettered.LetteredColorFactory;

public class GameTest {

    private static final int NR_COLORS = 6;
    private static final int NR_COLUMNS = 4;
    private Game game;
    private Guess secret;

    private void createTestGame() {
        ColorManager manager = new ColorManager(NR_COLORS, new LetteredColorFactory());
        Table table = new Table(NR_COLUMNS, manager);
        Color[] colors = new Color[NR_COLUMNS];
        Color color = manager.firstColor();
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
