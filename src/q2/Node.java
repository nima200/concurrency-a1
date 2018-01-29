package q2;

public class Node {
    private float aData;
    public Node parent, leftChild, rightChild;

    public Node(float pData) {
        aData = pData;
    }

    public float getData() {
        return aData;
    }
}
