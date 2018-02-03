package q1;

public class Counter {
    private int aMax;

    Counter(int pMax) {
        aMax = pMax;
    }

    public synchronized boolean decrement() {
        if (aMax > 0) {
            aMax--;
            return true;
        }
        return false;
    }
}
