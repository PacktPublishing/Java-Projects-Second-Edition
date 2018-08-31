//START SNIPPET SynchronizedDemo_ch05
package packt.java189fundamentals.thread;

public class SynchronizedDemo implements Runnable {
    public static final int N = 1000;
    public static final int MAX_TRY = 1_000_000;

    private final char threadChar;
    private final StringBuffer sb;

    public SynchronizedDemo(char threadChar, StringBuffer sb) {
        this.threadChar = threadChar;
        this.sb = sb;
    }

    @Override
    public void run() {
        for (int i = 0; i < N; i++) {
            synchronized (sb) {
                sb.append(threadChar);
                sleep();
                sb.append(threadChar);
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        boolean failed = false;
        int tries = 0;
        while (!failed && tries < MAX_TRY) {
            tries++;
            StringBuffer sb = new StringBuffer(4 * N);
            new Thread(new SynchronizedDemo('a', sb)).start();
            new Thread(new SynchronizedDemo('b', sb)).start();
            failed = sb.indexOf("aba") != -1 || sb.indexOf("bab") != -1;
        }
        System.out.println(failed ? "failed after " + tries + " tries" : "not failed");
    }
}
//END SNIPPET