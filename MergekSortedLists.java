import datastructure.ListNode;

/**
 * 23 Merge k Sorted Lists
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        ListNode[] list = new ListNode[(lists.length + 1)/2];
        for(int i = 0;i < lists.length;i += 2){
            ListNode node1 = lists[i];
            if(i < lists.length - 1) {
                ListNode node2 = lists[i + 1];
                node1 = merge(node1,node2);
            }
            list[(i + 1) / 2] = node1;
        }
        return mergeKLists(list);
    }

    public ListNode merge(ListNode node1, ListNode node2){
        ListNode res = new ListNode(0);
        ListNode cur = res;

        while(node1 != null || node2 != null){
            ListNode node;
            if(node1 == null){
                node = node2;
                node2 = node2.next;
            }
            else if(node2 == null){
                node = node1;
                node1 = node1.next;
            }
            else{
                if(node1.val < node2.val){
                    node = node1;
                    node1 = node1.next;
                }
                else{
                    node = node2;
                    node2 = node2.next;
                }
            }
            cur.next = node;
            cur = cur.next;
        }
        return res.next;
    }

}
