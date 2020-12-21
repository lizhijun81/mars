//给定一个链表，判断链表中是否有环。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
//
//
//
// 示例 1：
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
//
//
// 示例 2：
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
//
//
// 示例 3：
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
//
//
//
//
//
//
// 进阶：
//
// 你能用 O(1)（即，常量）内存解决此问题吗？
// Related Topics 链表 双指针


///  1 2 3 4 5 6 7 8 9
///        I
/// 1 2 3 4 5 6
/// 1 2 3 4 5 6 7 8 9 4 5 6
public class HasCycle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                return false;
            }

            if (fast.val == slow.val) {
                return true;
            }

            fast = fast.next;
            if (fast == null) {
                return false;
            }

            if (fast.val == slow.val) {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        HasCycle.ListNode l1_1 = new HasCycle.ListNode(1);
        HasCycle.ListNode l1_2 = new HasCycle.ListNode(2);
        HasCycle.ListNode l1_3 = new HasCycle.ListNode(4);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = l1_2;

        HasCycle hasCycle = new HasCycle();

        System.out.println(hasCycle.hasCycle(l1_1));
    }
}


