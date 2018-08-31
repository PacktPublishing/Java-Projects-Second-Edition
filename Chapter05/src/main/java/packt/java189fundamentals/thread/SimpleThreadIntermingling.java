// START SNIPPET SimpleThreadIntermingling_ch05
package packt.java189fundamentals.thread;

public class SimpleThreadIntermingling {
    public static void main(String[] args) {
        Thread t1 = new MyThread("t1");
        Thread t2 = new MyThread("t2");
        t1.start();
        t2.start();
        System.out.print("started ");

    }

    static class MyThread extends Thread {
        private final String name;

        MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i < 1000; i++) {
                System.out.print(name + " " + i + ", ");
            }
        }
    }
}
//END SNIPPET