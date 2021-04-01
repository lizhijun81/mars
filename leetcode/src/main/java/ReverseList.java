//反转一个单链表。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//
// 进阶:
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
// Related Topics 链表


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode currentNode = head;

        List<Integer> list = new ArrayList<>();
        while (currentNode != null) {
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }

        ListNode h = new ListNode(-1);
        for (int i = list.size() - 1; i >= 0; i--) {
            h.next = new ListNode(list.get(i));
        }

        return h.next;
    }

    public ListNode reverseList_1(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode h = reverseList_1(head.next);
        ListNode c = h;
        while(c.next != null) {
            c = c.next;
        }
        head.next = null;
        c.next = head;

        return h;
    }

    public static ListNode reverseList_2(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode h = null;

        while (head != null) {

            ListNode hTemp = head.next;

            head.next = null;

            if (h == null) {
                h = head;
            } else {
                head.next = h;
                h = head;
            }
            head = hTemp;
        }

        return h;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        ListNode h = reverseList_2(n1);
        while (h != null) {
            System.out.println(h);
            h = h.next;
        }
    }
}
