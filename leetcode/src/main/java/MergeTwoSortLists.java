//将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 示例：
//
// 输入：1->2->4, 1->3->4
// 输出：1->1->2->3->4->4
//
//

public class MergeTwoSortLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);

        ListNode hr = result;
        ListNode h1 = l1;
        ListNode h2 = l2;

        boolean isEnd = false;
        while (!isEnd) {
            if (h1 != null && h2 != null) {
                if (h1.val <= h2.val) {
                    result.next = h1;
                    h1 = h1.next;
                } else {
                    result.next = h2;
                    h2 = h2.next;
                }
                result = result.next;
            }

            if (h1 == null) {
                result.next = h2;
                isEnd = true;
            }

            if (h2 == null) {
                result.next = h1;
                isEnd = true;
            }
        }
        return hr.next;
    }

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(4);

//        l1_1.next = l1_2;
//        l1_2.next = l1_3;

        ListNode l2_1 = new ListNode(1);
        ListNode l2_2 = new ListNode(3);
        ListNode l2_3 = new ListNode(4);

//        l2_1.next = l2_2;
//        l2_2.next = l2_3;

        ListNode node = mergeTwoLists(l1_1, l2_1);

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "this is val: " + this.val;
    }
}