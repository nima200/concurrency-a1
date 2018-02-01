package q2;

public class q2 {
    private static Node root;
    public static void main(String[] args) throws InterruptedException {

        Thread treeConstructor = new Thread(() -> {
            // Construct nodes backwards
            Node n = new Node(Util.randFloat(0, 100));
            Node m = new Node(Util.randFloat(0, n.getData()));
            Node l = new Node(Util.randFloat(0, m.getData()));
            Node k = new Node(Util.randFloat(0, l.getData()));
            Node j = new Node(Util.randFloat(0, k.getData()));
            Node i = new Node(Util.randFloat(0, j.getData()));
            Node h = new Node(Util.randFloat(0, i.getData()));
            root = new Node(Util.randFloat(0, h.getData()));
            Node f = new Node(Util.randFloat(0, root.getData()));
            Node e = new Node(Util.randFloat(0, f.getData()));
            Node d = new Node(Util.randFloat(0, e.getData()));
            Node c = new Node(Util.randFloat(0, d.getData()));
            Node b = new Node(Util.randFloat(0, c.getData()));
            Node a = new Node(Util.randFloat(0, b.getData()));

            // Place nodes in a tree
            root.setLeftChild(b);
            root.setRightChild(i);

            b.setLeftChild(a);
            b.setRightChild(d);

            d.setLeftChild(c);
            d.setRightChild(f);

            f.setLeftChild(e);

            i.setLeftChild(h);
            i.setRightChild(m);

            m.setLeftChild(k);
            m.setRightChild(n);

            k.setLeftChild(j);
            k.setRightChild(l);
        });
        treeConstructor.run();
        treeConstructor.join();

        TreeReader reader1 = new TreeReader(root);
        TreeReader reader2 = new TreeReader(root);
        TreeModifier modifier = new TreeModifier(root, new TreeReader[]{reader1, reader2});

        reader1.start();
        reader2.start();
        modifier.start();
    }
}
