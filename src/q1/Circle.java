package q1;

import java.awt.*;

public class Circle {
    private int x;
    private int y;
    private int aRadius;
    private Color aColor;

    Circle(int x, int y, int radius, Color pColor) {
        this.x = x;
        this.y = y;
        this.aRadius = radius;
        this.aColor = pColor;
    }

    /**
     * Takes the wrap around distance between two circles and if less than or equal to zero, decides that circles
     * do intersect.
     *
     * @param other  Other circle to check for intersection against this circle
     * @param pImage Image to extract width from used for wrap around
     * @return True if circles intersect, false otherwise
     */
    public boolean intersects(Circle other, Image pImage) {
        assert other != null;
        assert pImage != null;
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        // Mirror the result if it's bigger than half of the screen
        if (dx > pImage.getWidth() / 2)
            dx = pImage.getWidth() - dx;
        if (dy > pImage.getHeight() / 2)
            dy = pImage.getHeight() / 2;
        // Calculate distance between two circles
        double sqrtDist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        int r2r1 = this.aRadius + other.aRadius;
        return sqrtDist - r2r1 <= 0;
    }

    public void draw(Image pImage) {
        int rgb = aColor.getRGB();
        // Crawl a box and draw points if the pixel falls inside a circle
        for (int i = -aRadius; i < aRadius; i++) {
            for (int j = -aRadius; j < aRadius; j++) {
                double xx = Math.pow(i, 2);
                double yy = Math.pow(j, 2);
                double rr = Math.pow(aRadius, 2);
                if (xx + yy <= rr) {
                    // For the wraparound, calculate the positive modulo remainder of the x and y points with respect
                    // To the width and height of the image
                    int pixelX = (x + i) % pImage.getWidth() < 0 ?
                            (x + i) % pImage.getWidth() + pImage.getWidth() :
                            (x + i) % pImage.getWidth();
                    int pixelY = (y + j) % pImage.getHeight() < 0 ?
                            (y + j) % pImage.getHeight() + pImage.getHeight() :
                            (y + j) % pImage.getHeight();
                    pImage.setPixel(pixelX, pixelY, rgb);
                }
            }
        }
    }
}
