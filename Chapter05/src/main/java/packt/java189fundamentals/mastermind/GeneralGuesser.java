package packt.java189fundamentals.mastermind;

public class GeneralGuesser extends Guesser {

    public GeneralGuesser(Table table) {
        super(table);
    }

    @Override
    protected Guess firstGuess() {
        var colors = new Color[table.nrOfColumns()];
        int i = 0;
        for (var color = table.manager.firstColor(); i < colors.length; ) {
            colors[i++] = color;
        }
        return new Guess(colors);
    }

}
