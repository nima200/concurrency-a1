package q3;

public class q3 {
    private static final Object object = new Object();
    private static final double x = 5;
    private static Object dummy = new Object();
    private static int staticInt = 0;
    private static int staticSyncedInt = 0;
    private static volatile int volatileStaticInt = 0;
    private static volatile int threadedVolatileInt = 0;
    private static int threadedInt = 0;

    public static void main(String[] args) throws InterruptedException {
        // Question 3 (a)
        int avgStaticRuntime = 0;
        long staticStart;
        long staticEnd;
        for (int trial = 0; trial < 7; trial++) {
            staticStart = System.currentTimeMillis();
            for (int i = 0; i < Integer.MAX_VALUE / x; i++) {
                staticInt++;
                dummy = new Object();
            }
            staticEnd = System.currentTimeMillis();
            if (trial > 0) avgStaticRuntime += (staticEnd - staticStart);
        }
        avgStaticRuntime /= 6;
        System.out.println("3a) Static int: " + avgStaticRuntime);

        // Question 3 (b)
        long staticVolatileStart;
        long staticVolatileEnd;
        int avgStaticVolatileRuntime = 0;
        for (int trial = 0; trial < 7; trial++) {
            staticVolatileStart = System.currentTimeMillis();
            for (int i = 0; i < Integer.MAX_VALUE / x; i++) {
                volatileStaticInt++;
                dummy = new Object();
            }
            staticVolatileEnd = System.currentTimeMillis();
            if (trial > 0) avgStaticVolatileRuntime += (staticVolatileEnd - staticVolatileStart);
        }
        avgStaticVolatileRuntime /= 6;
        System.out.println("3b) Static volatile int: " + avgStaticVolatileRuntime);

        // Question 3(c)
        int avgStaticSyncedRuntime = 0;
        long syncedStaticStart;
        long syncedStaticEnd;
        for (int trial = 0; trial < 7; trial++) {
            syncedStaticStart = System.currentTimeMillis();
            for (int i = 0; i < Integer.MAX_VALUE / x; i++) {
                synchronized (object) {
                    staticSyncedInt++;
                }
                dummy = new Object();
            }
            syncedStaticEnd = System.currentTimeMillis();
            if (trial > 0) avgStaticSyncedRuntime += (syncedStaticEnd - syncedStaticStart);
        }
        avgStaticSyncedRuntime /= 6;
        System.out.println("3c) Static synchronized int: " + avgStaticSyncedRuntime);

        // Question 3(d)
        int avgThreadedVolatileRuntime = 0;
        long threadedVolatileStart;
        long threadedVolatileEnd;
        for (int trial = 0; trial < 7; trial++) {
            Runnable volatileIncrementer = () -> {
                for (int i = 0; i < Integer.MAX_VALUE / x; i++) {
                    threadedVolatileInt++;
                    dummy = new Object();
                }
            };

            Thread inc1 = new Thread(volatileIncrementer);
            Thread inc2 = new Thread(volatileIncrementer);
            threadedVolatileStart = System.currentTimeMillis();
            inc1.start();
            inc2.start();
            inc1.join();
            inc2.join();
            threadedVolatileEnd = System.currentTimeMillis();
            if (trial > 0) avgThreadedVolatileRuntime += (threadedVolatileEnd - threadedVolatileStart);
        }
        avgThreadedVolatileRuntime /= 6;
        System.out.println("3d) 2 Threaded static volatile int: " + avgThreadedVolatileRuntime);

        // Question 3(e)
        int avgThreadedStaticRuntime = 0;
        long threadedStart;
        long threadedEnd;
        for (int trial = 0; trial < 7; trial++) {
            Runnable incrementer = () -> {
                for (int i = 0; i < Integer.MAX_VALUE / (2 * x); i++) {
                    threadedInt++;
                    dummy = new Object();
                }
            };

            Thread inc3 = new Thread(incrementer);
            Thread inc4 = new Thread(incrementer);
            threadedStart = System.currentTimeMillis();
            inc3.start();
            inc4.start();
            inc3.join();
            inc4.join();
            threadedEnd = System.currentTimeMillis();
            if (trial > 0) avgThreadedStaticRuntime += (threadedEnd - threadedStart);
        }
        avgThreadedStaticRuntime /= 6;
        System.out.println("3e) 2 Threaded static int: " + avgThreadedStaticRuntime);
    }
}
