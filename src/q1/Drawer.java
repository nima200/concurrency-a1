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

    public Drawer(int pMaxRadius, int pId, Image pImg, Counter pCounter, Lock pLock) {
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
            aCircle = new Circle(x, y, r, c);
//            aCircle.fixBounds(width, height);
            aLock.flagUp(aId);
            aLock.setTurn(aId);
            while (aLock.busy(aId) && overlap(aOther)) {
            }
            aCircle.draw(aImg);
            aLock.flagDown(aId);
        }
    }

    public Circle getCircle() {
        return aCircle;
    }

    public void setOther(Drawer pOther) {
        aOther = pOther;
    }

    /**
     * Checks to see if two drawers are drawing circles that overlap with one another
     * @param other The other drawer
     * @return True if there is an overlap, false otherwise
     */
    public boolean overlap(Drawer other) {
        return this.aCircle != null && other.aCircle != null && this.aCircle.intersects(other.aCircle, aImg);
    }
}
