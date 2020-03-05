package org.vp.prep.ds;

import java.util.Stack;

/**
 * 
 * @author vpathak Linked list problems 1) Add node at tail 2) Add node at head
 *         3) Reverse linked list iterative 4) Reverse linked list recursive 5)
 *         Sorting a linked list using insertion sort 6) Sort merge to sorted
 *         linked lists 7) Create copy of linked list 8) TODO - reverse every k
 *         nodes in linked list. Eg list = 1,2,3,4,5,6,7,8 and k=3, output=
 *         3,2,1,6,5,4,8,7
 */

class LinkList {
	class Node {
		private int info;
		private Node next;

		public Node(int info) {
			super();
			this.info = info;
			this.next = null;
		}

		public Node(int info, Node next) {
			super();
			this.info = info;
			this.next = next;
		}

	}

	Node head = null;

	public LinkList() {
		head = null;
	}

	public void addNode(int info) { // Add at tail
		if (head == null) {
			head = new Node(info);
		} else {
			Node tmp = head;
			while (tmp.next != null) {
				tmp = tmp.next;
			}
			tmp.next = new Node(info);
		}
	}

	public void addNodeHead(int info) { // Add at Head
		if (head == null) {
			head = new Node(info);
		} else {
			Node tmp = new Node(info);
			tmp.next = head;
			head = tmp;
		}
	}

	public void addMultiNode(int a[]) {
		for (int i = 0; i < a.length; i++) {
			addNode(a[i]);
		}
	}

	public void display(Node head) {
		if (head == null) {
			return;
		}
		Node tmp = head;
		while (tmp != null) {
			System.out.print(tmp.info + " ");
			tmp = tmp.next;
		}
	}

	public Node reverseItr(Node a) {
		Node head = null;
		while (a != null) {
			Node tmp = a;
			a = a.next;
			tmp.next = head;
			head = tmp;
		}
		return head;
	}

	public Node reverseRec(Node a) {
		Node ret = null;
		if (a.next == null) {
			ret = a;
		} else {
			ret = reverseRec(a.next);
			a.next.next = a;
			a.next = null;
		}
		return ret;
	}

	public void sort(Node node) {
		while (node != null) {
			Node nextNode = node.next;
			while (nextNode != null) {
				if (node.info >= nextNode.info) {
					int tmp = node.info;
					node.info = nextNode.info;
					nextNode.info = tmp;
				}
				nextNode = nextNode.next;
			}
			node = node.next;
		}
	}

	public void insertSorted(Node n, int info) {
		if (n == null) {
			head = new Node(info);
			return;
		}
		if (info < n.info) {
			head = new Node(info);
			head.next = n;
			return;
		}
		while (n.next != null) {
			if (info > n.info && info < n.next.info) {
				Node tmp = new Node(info);
				tmp.next = n.next;
				n.next = tmp;
				return;
			}
			n = n.next;
		}
		n.next = new Node(info);
	}

	public Node sortMerge(Node n1, Node n2) {

		if (n1 == null) {
			return n2;
		}
		if (n2 == null) {
			return n1;
		}
		if (n1.info < n2.info) {
			n1.next = sortMerge(n1.next, n2);
			return n1;
		} else {
			n2.next = sortMerge(n1, n2.next);
			return n2;
		}
	}

	public Node createCopy(Node a) {
		if (a == null) {
			return null;
		}
		Node head = null;
		Node prev = null;
		while (a != null) {
			Node b = new Node(a.info);
			if (prev == null) {
				head = b;
			} else {
				prev.next = b;
			}
			prev = b;
			b.next = null;
			a = a.next;
		}
		return head;
	}

	/**
	 * 
	 * @param a
	 * @param k
	 * @return
	 * 
	 * 		simply change node values, no links altered Uses O(k) extra memory,
	 *         tn = O(n)
	 * 
	 */
	public Node revKNodes(Node a, int k) {
		if (a == null) {
			return a;
		}
		Stack<Integer> stack = new Stack<Integer>();
		Node b = a;
		while (k > 0 && a != null) {
			stack.push(a.info);
			k--;
			a = a.next;
		}
		while (b != a) {
			b.info = stack.pop();
			b = b.next;
		}

		return a;
	}

	public static void main(String args[]) {
		LinkList ll = new LinkList();
		ll.addMultiNode(new int[] { 1, 5, 7, 9, 11, 13, 14, 15 });
		ll.display(ll.head);
		System.out.println();

		ll.revKNodes(ll.head, 3);
		ll.display(ll.head);
		System.out.println();
		// Node cpy = ll.createCopy(ll.head);
		// ll.display( cpy);

		Node tail = ll.reverseRec(ll.head);
		ll.display(tail);

		// LinkList l2 = new LinkList();
		// l2.addMultiNode(new int[]{0,2,6,10,14,15});
		// l2.display(l2.head);System.out.println();
		// Node l3 = ll.sortMerge(ll.head,l2.head);
		// ll.display(l3);
		// System.out.println("\n");
		// ll.sort(ll.head);
		// ll.display(ll.head);
		// System.out.println();
		// ll.insertSorted(ll.head, 7);
		// ll.display(ll.head);
		// Node tail = ll.reverseItr(ll.head);
		// Node tail = ll.reverseRec(ll.head);
		// ll.display(tail);
	}

}