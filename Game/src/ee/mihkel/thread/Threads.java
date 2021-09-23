package ee.mihkel.thread;

public class Threads extends Thread {
    int numberOfThreads;

    public Threads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        start();
    }

    @Override
    public void run() {
        if (numberOfThreads == 1) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        } else {
            for (int i = 5; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }
}
