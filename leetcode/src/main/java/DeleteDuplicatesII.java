//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
//
// 示例 1:
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
//
//
// 示例 2:
//
// 输入: 1->1->1->2->3
//输出: 2->3
// Related Topics 链表


public class DeleteDuplicatesII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode vNode = new ListNode(-1);
        vNode.next = head;

        ListNode currentNode = vNode;
        while (currentNode.next != null && currentNode.next.next != null) {
            if (currentNode.next.val == currentNode.next.next.val) {// 重复开始 包含 当前节点：currentNode
                ListNode e = currentNode.next.next;
                while (e != null) {
                    if (e.val != currentNode.next.val) {// 重复结束 不包含当前节点： e
                        break;
                    }
                    e = e.next;
                }
                currentNode.next = e;
                continue;
            }
            currentNode = currentNode.next;
        }
        return vNode.next;
    }

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(3);
        ListNode l1_4 = new ListNode(3);
        ListNode l1_5 = new ListNode(4);
        ListNode l1_6 = new ListNode(4);
        ListNode l1_7 = new ListNode(5);
        ListNode l1_8 = new ListNode(8);
        ListNode l1_9 = new ListNode(9);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = l1_4;
        l1_4.next = l1_5;
//        l1_5.next = l1_6;
//        l1_6.next = l1_7;
//        l1_7.next = l1_8;
//        l1_8.next = l1_9;

        DeleteDuplicatesII deleteDuplicatesII = new DeleteDuplicatesII();
        ListNode head = deleteDuplicatesII.deleteDuplicates(l1_1);
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}
