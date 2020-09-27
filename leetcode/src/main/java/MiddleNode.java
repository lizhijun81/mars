//给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
//
// 如果有两个中间结点，则返回第二个中间结点。
//
//
//
// 示例 1：
//
// 输入：[1,2,3,4,5]
//输出：此列表中的结点 3 (序列化形式：[3,4,5])
//返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
//注意，我们返回了一个 ListNode 类型的对象 ans，这样：
//ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next =
//NULL.
//
//
// 示例 2：
//
// 输入：[1,2,3,4,5,6]
//输出：此列表中的结点 4 (序列化形式：[4,5,6])
//由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
//
//
//
//
// 提示：
//
//
// 给定链表的结点数介于 1 和 100 之间。
//
// Related Topics 链表


public class MiddleNode {

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

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            fast = fast.next;
            if (fast == null) {
                return slow;
            }

            slow = slow.next;
            fast = fast.next;

            if (fast == null) {
                return slow;
            }
        }
    }

    public static void main(String[] args) {
        MiddleNode.ListNode l1_1 = new MiddleNode.ListNode(1);
        MiddleNode.ListNode l1_2 = new MiddleNode.ListNode(2);
        MiddleNode.ListNode l1_3 = new MiddleNode.ListNode(3);
        MiddleNode.ListNode l1_4 = new MiddleNode.ListNode(4);
        MiddleNode.ListNode l1_5 = new MiddleNode.ListNode(5);
        MiddleNode.ListNode l1_6 = new MiddleNode.ListNode(6);
//        MiddleNode.ListNode l1_7 = new MiddleNode.ListNode(7);
//        MiddleNode.ListNode l1_8 = new MiddleNode.ListNode(8);
//        MiddleNode.ListNode l1_9 = new MiddleNode.ListNode(9);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = l1_4;
        l1_4.next = l1_5;
        l1_5.next = l1_6;
//        l1_6.next = l1_7;
//        l1_7.next = l1_8;
//        l1_8.next = l1_9;
//        l1_9.next = l1_5;

        MiddleNode middleNode = new MiddleNode();
        System.out.println(middleNode.middleNode(l1_1));
    }
}
