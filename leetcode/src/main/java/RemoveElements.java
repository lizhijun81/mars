//删除链表中等于给定值 val 的所有节点。
//
// 示例:
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
//
// Related Topics 链表

public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode vNode = new ListNode(val - 1);
        vNode.next = head;

        ListNode current = vNode;

        while (current.next != null) {
            if (current.next.val == val) {// 当前元素的下一个元素
                current.next = current.next.next;
                continue;
            }
            current = current.next;
        }
        return vNode.next;
    }

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(6);
        ListNode l1_4 = new ListNode(4);
        ListNode l1_5 = new ListNode(5);
        ListNode l1_6 = new ListNode(6);
        ListNode l1_7 = new ListNode(7);
        ListNode l1_8 = new ListNode(8);
        ListNode l1_9 = new ListNode(9);

        l1_1.next = l1_2;
//        l1_2.next = l1_3;
//        l1_3.next = l1_4;
//        l1_4.next = l1_5;
//        l1_5.next = l1_6;
//        l1_6.next = l1_7;
//        l1_7.next = l1_8;
//        l1_8.next = l1_9;

        RemoveElements removeElements = new RemoveElements();
        ListNode head = removeElements.removeElements(l1_1, 2);
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}
