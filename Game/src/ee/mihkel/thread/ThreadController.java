package ee.mihkel.thread;

public class ThreadController {
    public static void startNewThreads() {
        Threads thread1 = new Threads(1);
        Threads thread2 = new Threads(2);
    }
}
