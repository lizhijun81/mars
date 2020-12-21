//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
//
// 示例：
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
//
//
// 说明：
//
// 给定的 n 保证是有效的。
//
// 进阶：
//
// 你能尝试使用一趟扫描实现吗？
// Related Topics 链表 双指针


public class RemoveNthFromEnd {
    static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "this val is " + this.val;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode right = head;
        ListNode left = head;

        while (right != null && n > 0) {// 先将有指针向前移动n个节点
            right = right.next;
            n--;
        }

        if (n > 0) {// n 的值比 链表的长度大
            return null;
        }

        if (right == null) {// 链表刚好是n各节点长，相当于删除第一个节点
            return head.next;
        }

        while (true) {
            right = right.next;

            if (right == null) {// 最后一个元素
                left.next = left.next.next;
                return head;
            }

            left = left.next;
        }
    }

    public static void main(String[] args) {
        RemoveNthFromEnd.ListNode l1_1 = new RemoveNthFromEnd.ListNode(1);
        RemoveNthFromEnd.ListNode l1_2 = new RemoveNthFromEnd.ListNode(2);
        RemoveNthFromEnd.ListNode l1_3 = new RemoveNthFromEnd.ListNode(3);
        RemoveNthFromEnd.ListNode l1_4 = new RemoveNthFromEnd.ListNode(4);
        RemoveNthFromEnd.ListNode l1_5 = new RemoveNthFromEnd.ListNode(5);
        RemoveNthFromEnd.ListNode l1_6 = new RemoveNthFromEnd.ListNode(6);

        l1_1.next = l1_2;
//        l1_2.next = l1_3;
//        l1_3.next = l1_4;
//        l1_4.next = l1_5;
//        l1_5.next = l1_6;

        RemoveNthFromEnd middleNode = new RemoveNthFromEnd();
        ListNode head = middleNode.removeNthFromEnd(l1_1, 2);
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}
