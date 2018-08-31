package packt.java189fundamentals.mastermind.integration;

import packt.java189fundamentals.mastermind.Guess;

public interface Secret {

    Guess createSecret(int nrColumns);
}
