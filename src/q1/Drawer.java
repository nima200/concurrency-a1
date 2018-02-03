package q1;

import java.awt.*;
import java.util.Random;

public class Drawer implements Runnable {

    private int aMaxRadius;
    private int aId;
    private volatile Circle aCircle;
    private Image aImg;
    private Counter aCounter;
    private Drawer aOther;
    private Lock aLock;

    Drawer(int pMaxRadius, int pId, Image pImg, Counter pCounter, Lock pLock) {
        assert pImg != null;
        assert pCounter != null;
        assert pLock != null;
        aId = pId;
        aMaxRadius = pMaxRadius;
        aImg = pImg;
        aCounter = pCounter;
        aLock = pLock;
    }

    @Override
    public void run() {
        int width = aImg.getWidth();
        int height = aImg.getHeight();
        Random random = new Random();
        while (aCounter.decrement()) {
            int r = random.nextInt(aMaxRadius);
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Color c = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            // Create a circle out of the random parameters
            aCircle = new Circle(x, y, r, c);
            // Show intention of drawing circle - Peterson's Algorithm
            aLock.flagUp(aId);
            aLock.setTurn(aId);
            // Spin if someone else is already drawing a circle that overlaps with your circle
            while (aLock.busy(aId) && overlap(aOther)) {
            }
            aCircle.draw(aImg);
            // Exit protocol - remove intentions of drawing
            aLock.flagDown(aId);
        }
    }

    public void setOther(Drawer pOther) {
        aOther = pOther;
    }

    /**
     * Checks to see if two drawers are drawing circles that overlap with one another
     * @param other The other drawer
     * @return True if there is an overlap, false otherwise
     */
    private boolean overlap(Drawer other) {
        return this.aCircle != null && other.aCircle != null && this.aCircle.intersects(other.aCircle, aImg);
    }
}
