/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Initialize current node to dummy of the returning list.
        ListNode dummy = new ListNode(0);
        // Initialize p and q to head of l1 and l2 respectively.
        ListNode p = l1, q = l2, curr = dummy;

        // Initialize carry to 0.
        int carry = 0;

        // Loop through lists l1 and l2 until you reach both ends.
        while (p != null || q != null) {
            // Set x to node p's value. If p has reached the end of l1, set to 0.
            int x = (p != null) ? p.val : 0;
            // Set y to node q's value. If q has reached the end of l2, set to 0.
            int y = (q != null) ? q.val : 0;
            // Set sum =  x + y + carry
            int sum = x + y + carry;
            // Update carry = sum / 10.
            carry = sum / 10;
            // Create a new node with the digit value of (sum mod 10) and set it to current node's next,
            // then advance current node to next.
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            // Advance both p and q
            if (p != null) p = p.next;
            if (q != null) q = q.next;

        }

        // Check if carry > 0, if so append a new node with digit 1 to the returning list.
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
