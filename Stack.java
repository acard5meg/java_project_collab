/**
 * Build parameterized Stack data structure to allow calculator to perform 
 * undo method
 */
public class Stack <E> {
	private class Node {
		private E data;
		private Node next;
		
		public Node() {
			data = null;
			next = null;
		}
		
		public Node(E inpData,Node inpNext) {
			data = inpData;
			next = inpNext; 
		}
	}
	
	private Node head;
	private int length;
	
	public Stack() {
		head = null;
	}

	public void push(E inpData) {
		head = new Node(inpData,head);
		length++;
	}
	
	public E pop() {
		E retVal = null;
		if (length == 0) {
			System.out.println("No numbers left");
			System.exit(0);
		}
		else {
			retVal = head.data;
			head = head.next;
			length--;
		}
		return retVal;
	}
	
	public E peek() {
		E retVal = null;
		if (length != 0) {
			retVal = head.data;
		}
		else {
			System.out.println("No numbers");
			System.exit(0);
		}
		return retVal;
	}
	
	public void print() {
		Node cont = head;
		while (cont.next != null) {
			System.out.print(cont.data + " -> ");
			cont = cont.next;
		}
		System.out.println(cont.data);
	}
	
	
	
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.print();
		test.pop();
		test.pop();
		test.print();
		test.pop();
		test.pop();
	}
}
