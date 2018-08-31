package packt.java11.mastermind.lettered;

import packt.java11.mastermind.Color;

public class LetteredColor extends Color {

    private final String letter;
    public LetteredColor(String letter){
        this.letter = letter;
    }

    @Override
    public String toString(){
        return letter;
    }
}
