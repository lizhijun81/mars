//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
//
// 示例 1:
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
//
//
// 示例 2:
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
// Related Topics 排序 链表


public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode currentNode = head.next;

        ListNode hNode = head;
        hNode.next = null;

        while (currentNode != null) {
            int currentValue = currentNode.val;
            ListNode cNode = hNode;
            ListNode preNode = null;

            boolean isTrue = false;
            while (cNode != null) {
                if (currentValue > cNode.val) {
                    preNode = cNode;
                    cNode = cNode.next;
                    isTrue = true;
                } else {
                    ListNode newNode = new ListNode(currentValue);
                    newNode.next = cNode;
                    if (preNode == null) {
                        hNode = newNode;
                    } else {
                        preNode.next = newNode;
                    }
                    break;
                }
            }

            if (isTrue) {
                preNode.next = new ListNode(currentValue);
            }
            currentNode = currentNode.next;
        }
        return hNode;
    }

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(-1);
        ListNode l1_2 = new ListNode(9);
        ListNode l1_3 = new ListNode(7);
        ListNode l1_4 = new ListNode(6);
        ListNode l1_5 = new ListNode(5);
        ListNode l1_6 = new ListNode(4);
        ListNode l1_7 = new ListNode(3);
        ListNode l1_8 = new ListNode(2);
        ListNode l1_9 = new ListNode(1);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
//        l1_3.next = l1_4;
//        l1_4.next = l1_5;
//        l1_5.next = l1_6;
//        l1_6.next = l1_7;
//        l1_7.next = l1_8;
//        l1_8.next = l1_9;

        SortList sortList = new SortList();
        ListNode head = sortList.sortList(l1_1);
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}
