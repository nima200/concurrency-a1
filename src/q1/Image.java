package q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Image {
    private int[][] pixels;
    public Image(int width, int height) {
        pixels = new int[height][width];
    }

    public void setPixel(int x, int y, int c) {
        pixels[y][x] = c;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public int[] getRGB() {
        int rgb[] = new int[getWidth() * getHeight()];
        for(int i = 0; i < pixels.length; i++) {
            int[] row = pixels[i];
            System.arraycopy(pixels[i], 0, rgb, i * row.length, row.length);
        }
        return rgb;
    }

    public int getWidth() {
        return pixels[0].length;
    }

    public int getHeight() {
        return pixels.length;
    }
}
