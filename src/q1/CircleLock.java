package q1;

public class CircleLock {

    public synchronized boolean overlap(Circle myCircle, Circle otherCircle) {
        return myCircle != null && otherCircle != null && myCircle.intersects(otherCircle);
    }
}
