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

    public boolean intersects(Circle other) {
        assert other != null;
        double distanceSquared = Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2);
        double r0minR1 = Math.pow((this.aRadius - other.aRadius), 2);
        double r0plusR1 = Math.pow((this.aRadius + other.aRadius), 2);
        return (distanceSquared <= r0plusR1 && distanceSquared >= r0minR1);
    }

    public void draw(BufferedImage pImage) {
        int rgb = aColor.getRGB();
        for (int i = -aRadius; i < aRadius; i++) {
            for (int j = -aRadius; j < aRadius; j++) {
                double xx = Math.pow(i, 2);
                double yy = Math.pow(j, 2);
                double rr = Math.pow(aRadius, 2);
                if (xx + yy <= rr) {
                    pImage.setRGB(x + i, y + j, rgb);
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
