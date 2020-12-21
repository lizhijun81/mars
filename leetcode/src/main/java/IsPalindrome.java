//请判断一个链表是否为回文链表。
//
// 示例 1:
//
// 输入: 1->2
// 输出: false
//
// 示例 2:
//
// 输入: 1->2->2->1
// 输出: true
//
//
// 进阶：
// 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 链表 双指针

import java.util.ArrayList;
import java.util.List;

public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode currentNode = head;

        List<Integer> list = new ArrayList<>();
        while (currentNode != null) {
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }

        ListNode vNode = new ListNode(-1);

        ListNode c = vNode;
        for (int i = list.size() - 1; i >= 0; i--) {
            c.next = new ListNode(list.get(i));
            c = c.next;
        }

        ListNode h = vNode.next;

        ListNode c1 = head;
        ListNode c2 = h;

        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                return false;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        if (c1 == null && c2 == null) {// 正序 和 反序 链表都遍历完成
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(1);
        ListNode l1_3 = new ListNode(3);
        ListNode l1_4 = new ListNode(3);
        ListNode l1_5 = new ListNode(2);
        ListNode l1_6 = new ListNode(2);

        l1_1.next = l1_2;
//        l1_2.next = l1_3;
//        l1_3.next = l1_4;
//        l1_4.next = l1_5;
//        l1_5.next = l1_6;

        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.isPalindrome(l1_1));
    }
}
