package q2;

import org.jetbrains.annotations.NotNull;

public class TreeReader extends Thread {
    private Node aRoot;
    private StringBuilder aBuilder;

    public TreeReader(@NotNull Node pRoot) {
        aRoot = pRoot;
        aBuilder = new StringBuilder();
    }

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();

        Node previous = null;
        Node current = aRoot;
        // Run for 5 seconds
        while (System.currentTimeMillis() <= currentTime + 5000) {
            // Copy the local neighbors of a node so that through this iteration is is not modified by the other thread
            Node parent = current.getParent();
            Node leftChild = current.getLeftChild();
            Node rightChild = current.getRightChild();
            // Moving down the tree in depth - need to reach a left leaf if possible
            if (previous == parent) {
                // Reached a leaf
                if (leftChild == null && rightChild == null) {
                    aBuilder.append(current.getData()).append(' ');
                    // If leaf was root, it is the edge case where root has no children, end and reset
                    if (current == aRoot) {
                        // Do not need to reset local min and max here
                        aBuilder.append('\n');
                    } else {
                        // If leaf wasn't root, must just go back to parent and continue in-order traversal
                        previous = current;
                        current = parent;
                    }
                // Not at a leaf, so traverse to left child first if it's there
                } else if (leftChild != null) {
                    previous = current;
                    current = leftChild;
                // Not at a leaf and no left child present, so go to right child
                } else {
                    previous = current;
                    current = rightChild;
                }
            }
            // Moving up the tree where a leaf was reached and now we are back at a parent
            // (came from a left side, so now go to right side)
            else if (previous == leftChild) {
                // Record data - since in order traversal
                aBuilder.append(current.getData()).append(' ');
                // We has no right child to go to however
                if (rightChild == null) {
                    // If root was the one without a right child, need to terminate
                    if (current == aRoot) {
                        aBuilder.append('\n');
                        previous = null;
                    // If it wasn't the root, then keep going to parent
                    } else {
                        previous = current;
                        current = parent;
                    }
                // We do have a right child to go to, traverse down to the right
                } else {
                    previous = current;
                    current = rightChild;
                }
            // Moving up the tree where a leaf was reached and now we are back at a parent
            // (came from a right side, so now we either are at root and have to terminate, or have to keep going up)
            } else {
                // We are at root and must terminate
                if (current == aRoot) {
                    aBuilder.append('\n');
                    previous = null;
                // We still have valid parents and can keep going up
                } else {
                    previous = current;
                    current = parent;
                }
            }
            // After visiting each node, sleep for 5-20ms
            try {
                sleep(Util.randInt(5, 20));
            } catch (InterruptedException pE) {
                pE.printStackTrace();
            }
        }
    }

    public StringBuilder getBuilder() {
        return aBuilder;
    }
}
