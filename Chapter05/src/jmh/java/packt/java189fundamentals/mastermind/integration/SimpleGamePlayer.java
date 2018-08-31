package packt.java189fundamentals.mastermind.integration;

import packt.java189fundamentals.mastermind.*;
import packt.java189fundamentals.mastermind.lettered.LetteredColorFactory;

public class SimpleGamePlayer implements Player {
    final int nrColors = 10;
    final int nrColumns = 6;

    @Override
    public void play() {
        final var manager = new ColorManager(nrColors,
                new LetteredColorFactory());
        final var table = new Table(nrColumns, manager);
        final var secret = new RandomSecret(manager);
        final var secretGuess = secret.createSecret(nrColumns);
        final var game = new Game(table, secretGuess);
        final var guesser = new UniqueGuesser(table);
        while (!game.isFinished()) {
            Guess guess = guesser.guess();
            game.addNewGuess(guess);
        }
    }
}
