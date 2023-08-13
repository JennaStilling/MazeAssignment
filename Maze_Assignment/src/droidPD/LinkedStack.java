package droidPD;
import droidPD.LinkedStack;

public class LinkedStack <E> {
	private SinglyLinkedList<E> list;
	
	public LinkedStack() {
		list = new SinglyLinkedList<>();
	}
	
	public int size() {return list.size();}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void push(E element) {
		//System.out.println("Added " + element.toString() + " to stack at position " + size());
		list.addFirst (element);
	}
	
	public E top() {
		return list.first();
		}
	
	public E pop() {
		return list.removeFirst();
		}
}
