package q2;

public class Node {
    private float aData;
    private volatile Node aParent, aLeftChild, aRightChild;

    Node(float pData) {
        aData = pData;
    }

    public float getData() {
        return aData;
    }

    public void setLeftChild(Node pLeftChild) {
        this.aLeftChild = pLeftChild;
        this.aLeftChild.aParent = this;
    }

    public void setRightChild(Node pRightChild) {
        this.aRightChild = pRightChild;
        this.aRightChild.aParent = this;
    }

    public void removeLeftChild() {
        if (this.aLeftChild != null) {
            this.aLeftChild = null;
        }
    }

    public void removeRightChild() {
        if (this.aRightChild != null) {
            this.aRightChild = null;
        }
    }

    public Node getLeftChild() {
        return aLeftChild;
    }

    public Node getRightChild() {
        return aRightChild;
    }

    public Node getParent() {
        return aParent;
    }
}
