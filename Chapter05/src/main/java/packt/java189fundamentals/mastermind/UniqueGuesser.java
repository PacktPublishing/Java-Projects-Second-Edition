package packt.java189fundamentals.mastermind;

/**
 * A unique guesser creates guesses that do not use a color twice in a row.
 */
public class UniqueGuesser extends Guesser {

    public UniqueGuesser(Table table) {
        super(table);
    }

    @Override
    protected Guess firstGuess() {
        final var colors = new Color[table.nrOfColumns()];
        int i = colors.length - 1;
        for (var color = manager.firstColor();
             i >= 0;
             color = manager.nextColor(color)) {
            colors[i--] = color;
        }
        return new Guess(colors);
    }

    @Override
    protected Guess nextGuess() {
        var guess = super.nextGuess();
        while (!guess.isUnique()) {
            guess = super.nextGuess();
        }
        return guess;
    }

}
