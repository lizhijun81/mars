

public class Test{
    public static void main(String[] args) {
        LinkedNode<Integer> node = LinkedNode.generateLinkedNode(new Integer[]{1, 2, 3, 4, 5});

        LinkedNode.str(node);
    }
}

class LinkedNode<T> {

    private T value;

    private LinkedNode<T> next;

    public static <T> LinkedNode<T> generateLinkedNode(T[] s) {
        LinkedNode<T> header = new LinkedNode<T>();

        LinkedNode<T> top = header;

        for (T t : s) {
            LinkedNode<T> currentNode = new LinkedNode<T>();
            currentNode.setValue(t);
            top.setNext(currentNode);
            top = currentNode;
        }

        return header.next;
    }

    public static <T> void str(LinkedNode<T> t) {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println(t.getValue());
            isTrue = t.getNext() != null;
            t = t.getNext();
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }



}
