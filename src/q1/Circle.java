package q1;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Circle {
    private int x;
    private int y;
    private int aRadius;
    private Color aColor;

    public Circle(int x, int y, int radius, Color pColor) {
        this.x = x;
        this.y = y;
        this.aRadius = radius;
        this.aColor = pColor;
    }

    public boolean intersects(Circle other, Image pImage) {
        assert other != null;
        assert pImage != null;
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        if (dx > pImage.getWidth() / 2)
            dx = pImage.getWidth() - dx;
        if (dy > pImage.getHeight() / 2)
            dy = pImage.getHeight() / 2;
        double sqrtDist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        int r2r1 = this.aRadius + other.aRadius;
        return sqrtDist - r2r1 <= 0;
    }

    public void draw(Image pImage) {
        int rgb = aColor.getRGB();
        for (int i = -aRadius; i < aRadius; i++) {
            for (int j = -aRadius; j < aRadius; j++) {
                double xx = Math.pow(i, 2);
                double yy = Math.pow(j, 2);
                double rr = Math.pow(aRadius, 2);
                if (xx + yy <= rr) {
                    int pixelX = (x + i) % pImage.getWidth() < 0 ? (x + i) % pImage.getWidth() + pImage.getWidth() : (x + i) % pImage.getWidth();
                    int pixelY = (y + j) % pImage.getHeight() < 0 ? (y + j) % pImage.getHeight() + pImage.getHeight() : (y + j) % pImage.getHeight();
                    pImage.setPixel(pixelX, pixelY, rgb);
                }
            }
        }
    }

    public void fixBounds(int width, int height) {
        if (x + aRadius >= width) {
            x = width - aRadius;
        }
        if (x < aRadius) {
            x = aRadius;
        }
        if (y + aRadius >= height) {
            y = height - aRadius;
        }
        if (y < aRadius) {
            y = aRadius;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return aRadius;
    }
}
