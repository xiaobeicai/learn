import datastructure.ListNode;

/**
 Given a linked list, rotate the list to the right by k places, where k is non-negative.

 Example 1:

 Input: 1->2->3->4->5->NULL, k = 2
 Output: 4->5->1->2->3->NULL
 Explanation:
 rotate 1 steps to the right: 5->1->2->3->4->NULL
 rotate 2 steps to the right: 4->5->1->2->3->NULL
 Example 2:

 Input: 0->1->2->NULL, k = 4
 Output: 2->0->1->NULL
 Explanation:
 rotate 1 steps to the right: 2->0->1->NULL
 rotate 2 steps to the right: 1->2->0->NULL
 rotate 3 steps to the right: 0->1->2->NULL
 rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {
    //memory exceed
    public ListNode rotateRight(ListNode head, int k) {
        ListNode rev = reverse(head);
        ListNode cur = rev;
        ListNode pre = null;
        while(k > 0 && cur != null){
            pre = cur;
            cur = cur.next;
            k--;
        }
        pre.next = null;
        ListNode right = cur;
        ListNode left = head;

        ListNode releft = reverse(left);
        ListNode reright = reverse(right);
        releft.next = reright;
        return releft;
    }

    public ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode p = null;
        ListNode cur = head;
        while(cur != null){
            ListNode node = cur.next;
            cur.next = p;
            p = cur;
            cur = node;
        }
        return p;
    }

    public ListNode rotateRight2(ListNode head, int k) {
       if(head == null || head.next == null) return head;
       int size = 0;
       ListNode temp = head;
       while(temp != null){
           temp = temp.next;
           size++;
       }
       k = k % size;
       if(k == 0) return head;
       int count = size - k;
       ListNode right = head;
       ListNode cur = head;
       ListNode pre = null;
       while(count > 0){
           count--;
           pre = cur;
           cur = cur.next;
       }
       pre.next = null;
       ListNode left = cur;
       while(cur.next != null){
           cur = cur.next;
       }
       cur.next = right;
       return left;
    }
}
