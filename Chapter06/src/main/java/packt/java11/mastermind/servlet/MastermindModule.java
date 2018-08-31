package packt.java11.mastermind.servlet;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import packt.java11.mastermind.ColorFactory;
import packt.java11.mastermind.Guesser;
import packt.java11.mastermind.lettered.LetteredColorFactory;
import packt.java11.mastermind.UniqueGuesser;
// START SNIPPET MastermindModule
public class MastermindModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(int.class)
                .annotatedWith(Names.named("nrColors"))
                .toInstance(6);
        bind(int.class)
                .annotatedWith(Names.named("nrColumns"))
                .toInstance(4);
        bind(ColorFactory.class)
                .to(LetteredColorFactory.class);
        bind(Guesser.class)
                .to(UniqueGuesser.class);
    }
// END SNIPPET
}
