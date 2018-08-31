package tryfinally;

public class Dancsecz {
    public static void main(String[] args) {
        var mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook( new Thread(
                () -> mainThread.stop()
        ));
        try {
            System.out.println(Thread.currentThread().getName() + " try running...");
            //Runtime.getRuntime().halt(0);
            System.exit(0);
        } finally {
            System.out.println("finally running...");
        }
    }
}
