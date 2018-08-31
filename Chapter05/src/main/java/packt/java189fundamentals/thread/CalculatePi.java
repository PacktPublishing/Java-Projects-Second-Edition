package packt.java189fundamentals.thread;
//http://www.javaworld.com/arCompletableFuture_ch05ticle/2074217/java-concurrency/java-101--understanding-java-threads--part-1--introducing-threads-and-runnables.html?page=2
public class CalculatePi {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.start();
        try {
            Thread.sleep(1); // Sleep for 1 milliseconds, 10ms was just enough on my machine for the other thread to finish...
        } catch (InterruptedException e) {
        }
        System.out.println("pi = " + mt.pi);
    }
}

class MyThread extends Thread
{
    boolean negative = true;
    double pi; // Initializes to 0.0, by default
    public void run ()
    {
        for (int i = 3; i < 100000; i += 2)
        {
            if (negative)
                pi -= (1.0 / i);
            else
                pi += (1.0 / i);
            negative = !negative;
        }
        pi += 1.0;
        pi *= 4.0;
        System.out.println ("Finished calculating PI");
    }
}



