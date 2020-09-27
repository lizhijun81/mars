//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
//
// 说明：不允许修改给定的链表。
//
//
//
// 示例 1：
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：tail connects to node index 1
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
//
//
// 示例 2：
//
// 输入：head = [1,2], pos = 0
//输出：tail connects to node index 0
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
//
//
// 示例 3：
//
// 输入：head = [1], pos = -1
//输出：no cycle
//解释：链表中没有环。
//
//
//
//
//
//
// 进阶：
//你是否可以不用额外空间解决此题？
// Related Topics 链表 双指针


public class HasCycleII {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "this val is " + this.val;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            slow = slow.next;
            fast = fast.next;

            if (fast == null) {
                return null;
            }

            fast = fast.next;
            if (fast == null) {
                return null;
            }

            if (fast == slow) {
                break;
            }
        }

        slow = head;
        while (true) {
            if (slow == fast) {
                return slow;
            }

            slow = slow.next;
            fast = fast.next;
        }
    }

    public static void main(String[] args) {
        HasCycleII.ListNode l1_1 = new HasCycleII.ListNode(1);
        HasCycleII.ListNode l1_2 = new HasCycleII.ListNode(2);
//        HasCycleII.ListNode l1_3 = new HasCycleII.ListNode(3);
//        HasCycleII.ListNode l1_4 = new HasCycleII.ListNode(4);
//        HasCycleII.ListNode l1_5 = new HasCycleII.ListNode(5);
//        HasCycleII.ListNode l1_6 = new HasCycleII.ListNode(6);
//        HasCycleII.ListNode l1_7 = new HasCycleII.ListNode(7);
//        HasCycleII.ListNode l1_8 = new HasCycleII.ListNode(8);
//        HasCycleII.ListNode l1_9 = new HasCycleII.ListNode(9);

        l1_1.next = l1_2;
        l1_2.next = l1_1;
//        l1_3.next = l1_4;
//        l1_4.next = l1_5;
//        l1_5.next = l1_6;
//        l1_6.next = l1_7;
//        l1_7.next = l1_8;
//        l1_8.next = l1_9;
//        l1_9.next = l1_5;

        HasCycleII hasCycle = new HasCycleII();
        System.out.println(hasCycle.detectCycle(l1_1));
    }
}
