package packt.java11.mastermind;

public class GeneralGuesser extends Guesser {

    public GeneralGuesser(Table table) {
        super(table);
    }

    @Override
    protected Guess firstGuess() {
        Color[] colors = new Color[table.nrOfColumns()];
        int i = 0;
        for (Color color = table.manager.firstColor(); i < colors.length; ) {
            colors[i++] = color;
        }
        return new Guess(colors);
    }

}
