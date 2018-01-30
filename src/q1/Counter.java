package q1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger aMax;

    public Counter(int pMax) {
        aMax = new AtomicInteger(pMax);
    }

    public boolean decrement() {
        return aMax.getAndDecrement() > 0;
    }

    public void increment() {
        aMax.incrementAndGet();
    }

    public int getValue() {
        return aMax.get();
    }
}
