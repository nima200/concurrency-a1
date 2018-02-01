package q2;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TreeModifier extends Thread {

    private Node aRoot;
    private TreeReader[] aReaders;

    public TreeModifier(@NotNull Node pRoot, @NotNull TreeReader[] pReaders) {
        aRoot = pRoot;
        aReaders = pReaders;
    }

    @Override
    public void run() {
        Random random = new Random();
        long currentTime = System.currentTimeMillis();
        float absoluteMax = 100.0f;
        float absoluteMin = 0.0f;
        float localMin = absoluteMin;
        float localMax = absoluteMax;
        Node current = aRoot;
        // Run for 5 seconds
        while (System.currentTimeMillis() <= currentTime + 5000) {
            float decision = random.nextFloat();
            if (decision <= 0.5) { // Randomly decide to go left
                // Check for left child
                if (current.getLeftChild() != null) {
                    // Node has a left child
                    float deleteChance = random.nextFloat();
                    if (deleteChance <= 0.1) { // 10% chance of deleting left child
                        // Delete L-Child and reset from root
                        current.removeLeftChild();
                        current = aRoot;
                        localMin = absoluteMin;
                        localMax = absoluteMax;
                        try {
                            sleep(Util.randInt(1, 5));
                        } catch (InterruptedException pE) {
                            pE.printStackTrace();
                        }
                    } else {
                        // Decided not to delete left child, so go to it
                        localMax = current.getData();
                        current = current.getLeftChild();
                    }
                } else {
                    // Node has no left child
                    float createChance = random.nextFloat();
                    if (createChance <= 0.4) { // 40% chance of creating a left child
                        // Create new node and reset from root
                        localMax = current.getData();
                        Node newLeftChild = new Node(Util.randFloat(localMin, localMax));
                        current.setLeftChild(newLeftChild);
                        current = aRoot;
                        localMin = absoluteMin;
                        localMax = absoluteMax;
                        // Sleep for 1-5 ms
                        try {
                            sleep(Util.randInt(1, 5));
                        } catch (InterruptedException pE) {
                            pE.printStackTrace();
                        }
                    }
                }
            } else { // Randomly decide to go right
                // Check for right child
                if (current.getRightChild() != null) {
                    // Node has a right child
                    float deleteChance = random.nextFloat();
                    if (deleteChance <= 0.1) { // 10% chance of deleting right child
                        // Delete R-Child and reset from root
                        current.removeRightChild();
                        current = aRoot;
                        localMin = absoluteMin;
                        localMax = absoluteMax;
                        // Sleep for 1-5 ms
                        try {
                            sleep(Util.randInt(1, 5));
                        } catch (InterruptedException pE) {
                            pE.printStackTrace();
                        }
                    } else {
                        // Decided not to delete the right child, so go to it
                        localMin = current.getData();
                        current = current.getRightChild();
                    }
                } else {
                    // Node has no right child
                    float createChance = random.nextFloat();
                    if (createChance <= 0.4) { // 40% chance of creating a right child
                        // Create new node and reset from root
                        localMin = current.getData();
                        Node newRightChild = new Node(Util.randFloat(localMin, localMax));
                        current.setRightChild(newRightChild);
                        current = aRoot;
                        localMin = absoluteMin;
                        localMax = absoluteMax;
                        // Sleep for 1-5 ms
                        try {
                            sleep(Util.randInt(1, 5));
                        } catch (InterruptedException pE) {
                            pE.printStackTrace();
                        }
                    }
                }
            }
        }
        // Join all reader threads
        for (TreeReader aReader : aReaders) {
            try {
                aReader.join();
            } catch (InterruptedException pE) {
                pE.printStackTrace();
            }
        }
        System.out.println("A\n" + aReaders[0].getBuilder().toString());
        System.out.println("B\n" + aReaders[1].getBuilder().toString());
    }
}
