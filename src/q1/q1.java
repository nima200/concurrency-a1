package q1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class q1 {

    // The image constructed
    public static BufferedImage img;

    // Image dimensions; you can modify these for bigger/smaller images
    public static int width = 1920;
    public static int height = 1080;

    public static int r;
    public static int c;
    public static boolean multithreaded;

    public static void main(String[] args) {
        try {
            if (args.length<3)
                throw new Exception("Missing arguments, only "+args.length+" were specified!");
            // arg 0 is the max radius
            r = Integer.parseInt(args[0]);
            // arg 1 is count
            c = Integer.parseInt(args[1]);
            // arg 2 is a boolean
            multithreaded = Boolean.parseBoolean(args[2]);

            // create an image and initialize it to all 0's
            img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            for (int i=0;i<width;i++) {
                for (int j=0;j<height;j++) {
                    img.setRGB(i,j,0);
                }
            }

            long averageExecution = 0;
            for (int i = 0; i < 6; i++) {
                if (multithreaded) {
                    final long start = System.currentTimeMillis();
                    twoThreads();
                    final long end = System.currentTimeMillis();
                    System.out.println("Multithreaded execution: " + (end - start));
                    if (i > 0) averageExecution += (end - start);
                } else {
                    final long start = System.currentTimeMillis();
                    oneThread();
                    final long end = System.currentTimeMillis();
                    System.out.println("Singlethreaded execution: " + (end - start));
                    if (i > 0) averageExecution += (end - start);
                }
            }
            averageExecution /= 5;
            System.out.println("MultiThreaded: " + multithreaded + ", Average runtime: " + averageExecution);

            // Write out the image
            File outputfile = new File("outputimage.png");
            ImageIO.write(img, "png", outputfile);

        } catch (Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
        }
    }

    private static void oneThread() {
        int i = 0;
        Random random = new Random();
        Image image = new Image(width, height);
        while (i < c) {
            int radius = random.nextInt(r);
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Color c = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Circle aCircle = new Circle(x, y, radius, c);
            aCircle.draw(image);
            i++;
        }

        // Dump the image pixel data onto the BufferedImage
        img.setRGB(0, 0, width, height, image.getRGB(), 0, width);
    }

    private static void twoThreads() throws InterruptedException {
        Counter c1 = new Counter(c);
        Lock lock = new Lock();
        Image image = new Image(width, height);
        Drawer d1 = new Drawer(r, 0, image, c1, lock);
        Drawer d2 = new Drawer(r, 1, image, c1, lock);
        d1.setOther(d2);
        d2.setOther(d1);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Dump the image pixel data onto the BufferedImage
        img.setRGB(0, 0, width, height, image.getRGB(), 0, width);
    }
}
