package bt;

/*
 二叉查找树 binaryTree.BST
   1.所有非叶子结点至多拥有两个儿子（Left和Right）；
   2.所有结点存储一个关键字；
   3.非叶子结点的左指针指向小于其关键字的子树，右指针指向大于其关键字的子树；

 AVL平衡二叉搜索树
    定义：平衡二叉树或为空树,或为如下性质的二叉排序树:
      （1）左右子树深度之差的绝对值不超过1;
      （2）左右子树仍然为平衡二叉树.

    旋转：
        当左右子树的深度之差超过1后，需要进行RR、LL、LR、RL 的选装，使得子树能够保持相对的平衡；
        RR：

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
                n.setParent(node);
            }
        }

        if (v > node.getValue()) {
            Node right = node.getRight();
            Node n = put(right, v);
            if (right == null) {
                node.setRight(n);
                n.setParent(node);
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

    /*
        先序遍历：根节点-左节点-右节点
     */
    public void preOrderPrint() {
        preOrderPrint(root);
    }

    private void preOrderPrint(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.value);
        preOrderPrint(node.getLeft());
        preOrderPrint(node.getRight());
    }

    /*
        中序遍历：左节点-根节点-右节点
     */
    private void middleOrderPrint(Node node) {
        if (node == null) {
            return;
        }

        middleOrderPrint(node.getLeft());
        System.out.println(node.value);
        middleOrderPrint(node.getRight());
    }

    public void middleOrderPrint() {
        middleOrderPrint(root);
    }

    /*
        后序遍历： 左节点-右节点-根节点
     */
    public void backOrderPrint() {
        backOrderPrint(root);
    }

    private void backOrderPrint(Node node) {
        if (node == null) {
            return;
        }

        backOrderPrint(node.getLeft());
        backOrderPrint(node.getRight());
        System.out.println(node.value);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.put(3);
        bst.put(4);
        bst.put(5);
        bst.put(6);
        bst.put(7);

        System.out.println("========");
        bst.preOrderPrint();
        System.out.println("========");
        bst.middleOrderPrint();
        System.out.println("========");
        bst.backOrderPrint();
    }
}
