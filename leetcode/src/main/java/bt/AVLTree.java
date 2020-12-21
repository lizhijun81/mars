package bt;

/*
    AVL平衡二叉搜索树
    定义：平衡二叉树或为空树,或为如下性质的二叉排序树:
      （1）左右子树深度之差的绝对值不超过1;
      （2）左右子树仍然为平衡二叉树.
 */
public class AVLTree {

    private Node root;

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


    public static void main(String[] args) {

    }

}
