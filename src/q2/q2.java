package q2;

import java.util.Random;

public class q2 {
    private static Node root;
    public static void main(String[] args) {
        Random random = new Random();
        float cap = 10;
        Thread t1 = new Thread(() -> {
            root = new Node(random.nextFloat() * cap);

        });
    }
}
