//编写一个程序，找到两个单链表相交的起始节点。
//
// 如下面的两个链表：
//
//
//
// 在节点 c1 开始相交。
//
//
//
// 示例 1：
//
//
//
// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, s
//kipB = 3
//输出：Reference of the node with value = 8
//输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
//,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
//
//
//
//
// 示例 2：
//
//
//
// 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
// 1
//输出：Reference of the node with value = 2
//输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
//]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
//
//
//
//
// 示例 3：
//
//
//
// 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
// skipA 和 skipB 可以是任意值。
//解释：这两个链表不相交，因此返回 null。
//
//
//
//
// 注意：
//
//
// 如果两个链表没有交点，返回 null.
// 在返回结果后，两个链表仍须保持原有的结构。
// 可假定整个链表结构中没有循环。
// 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
//
// Related Topics 链表


public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode ca = headA;
        while (ca.next != null) {// a 链表的末尾
            ca = ca.next;
        }

        ListNode tailA = ca;

        tailA.next = headB;// a链表和b链表连接

        ListNode fast = headA;
        ListNode slow = headA;

        while (true) {// 末尾
            fast = fast.next;
            slow = slow.next;

            if (fast == null) {
                tailA.next = null;
                return null;
            }

            fast = fast.next;
            if (fast == null) {
                tailA.next = null;
                return null;
            }

            if (fast == slow) {
                break;
            }
        }

        slow = headA;
        while (true) {
            if (slow == fast) {
                tailA.next = null;
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(3);
        ListNode l1_4 = new ListNode(4);
        ListNode l1_5 = new ListNode(5);
        ListNode l1_6 = new ListNode(6);
        ListNode l1_7 = new ListNode(7);
        ListNode l1_8 = new ListNode(8);
        ListNode l1_9 = new ListNode(9);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = l1_4;
        l1_4.next = l1_5;
        l1_5.next = l1_6;
        l1_6.next = l1_7;
        l1_7.next = l1_8;
        l1_8.next = l1_9;


        ListNode l2_1 = new ListNode(11);
        ListNode l2_2 = new ListNode(22);
        ListNode l2_3 = new ListNode(33);
        ListNode l2_4 = new ListNode(44);
        ListNode l2_5 = new ListNode(55);
        ListNode l2_6 = new ListNode(66);
        ListNode l2_7 = new ListNode(77);
        ListNode l2_8 = new ListNode(88);
        ListNode l2_9 = new ListNode(99);

        l2_1.next = l2_2;
        l2_2.next = l2_3;
        l2_3.next = l2_4;
        l2_4.next = l2_5;
        l2_5.next = l1_6;
        l2_6.next = l2_7;
        l2_7.next = l2_8;
        l2_8.next = l2_9;

        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
        System.out.println(getIntersectionNode.getIntersectionNode(l1_1, l2_1));
    }
}
