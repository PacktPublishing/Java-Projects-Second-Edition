//START SNIPPET VolatileDemonstration_ch05
package packt.java189fundamentals.thread;

public class VolatileDemonstration implements Runnable {
    private final Object o;
    private static final Object NON_NULL = new Object();
    @Override
    public void run() {
        while( o == null );
        System.out.println("o is not null");
    }

    public VolatileDemonstration() throws InterruptedException {
        new Thread(this).start();
        Thread.sleep(1000);
        this.o = NON_NULL;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemonstration me = new VolatileDemonstration();
    }
}
//END SNIPPET