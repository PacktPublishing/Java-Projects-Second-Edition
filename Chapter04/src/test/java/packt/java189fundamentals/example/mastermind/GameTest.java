package packt.java189fundamentals.example.mastermind;

import org.junit.Test;

public class GameTest {

    private Game game;
    private Color[] secret;
    private void createTestGame(){
        final int nrColors = 6;
        ColorManager manager = new ColorManager(nrColors);
        final int nrColumns = 4;
        Table table = new Table(nrColumns, manager);
        secret = new Color[nrColumns];
        Color color = manager.firstColor();
        for (int i = 0; i < nrColumns; i++) {
            secret[i] = color;
            color = manager.nextColor(color);
        }
        game = new Game(table, secret);
    }

    @Test
    public void canAddNewGuess() {
        createTestGame();
        game.addNewGuess(new Row(secret));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionForFinishedGame() {
        createTestGame();
        game.addNewGuess(new Row(secret));
        game.addNewGuess(new Row(secret));
    }
}
