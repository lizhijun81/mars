
/*
 * 二叉查找树 BST
 */
public class BST {

    private static class Node {

        private Node parent;
        private Node left;
        private Node right;

        private int value;

        public Node(int value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void put(int v) {
        if (root == null) {
            root = new Node(v);
            return;
        }

        if (v < root.getValue()) {
            put(root, v);
        }

        if (v > root.getValue()) {
            put(root, v);
        }
    }

    private Node put(Node node, int v) {
        if (node == null) {
            return new Node(v);
        }

        if (v < node.getValue()) {
            Node left = node.getLeft();
            Node n = put(left, v);
            if (left == null) {
                node.setLeft(n);
            }
        }

        if (v > node.getValue()) {
            Node right = node.getRight();
            Node n = put(right, v);
            if (right == null) {
                node.setRight(n);
            }
        }
        return node;
    }

    public Node get(int v) {
        return get(root, v);
    }

    private Node get(Node node, int v) {
        if (node == null) {
            return null;
        }

        get(node.getLeft(), v);
        if (node.getValue() == v) {
            return node;
        }
        get(node.getRight(), v);

        return null;
    }


    private void print(Node node) {
        if (node == null) {
            return;
        }

        print(node.getLeft());
        System.out.println(node.value);
        print(node.getRight());
    }

    public void print() {
        print(root);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.put(3);
        bst.put(4);
        bst.put(5);
        bst.put(6);
        bst.put(7);

        bst.print();
    }
}
