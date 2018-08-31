package packt.java189fundamentals.ch03.main.bubble.generic;

public class NonStringElementInCollectionException extends RuntimeException {
    public NonStringElementInCollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
