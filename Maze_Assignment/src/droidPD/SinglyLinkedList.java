package droidPD;

public class SinglyLinkedList<E> {
	
	private int size; //size of linked list
	private Node<E> head; //head of list
	private Node<E> tail; //tail of list
	
	public SinglyLinkedList() {
		size = 0;
		
		head = new Node<E>(null, null);
		tail = new Node<E>(null, null);
	}

	public int size() {
		return size;
	}
	
	//returns:
	//		boolean if the list is empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E first() {
		if (isEmpty())
			return null;
		else
			return head.getElement();
	}
	
	public E last() {
		if (isEmpty())
			return null;
		else
			return tail.getElement();
	}
	
	public void addFirst(E element) {
		head = new Node<E>(element, head);
		
		if (size == 0)
			tail = head;
		
		size++;
	}
	
	public void addLast(E element) {
		Node<E> newNode = new Node<E>(element, null);
		
		if (isEmpty())
			head = newNode;	
		else
			tail.setNext(newNode);
		
		tail = newNode;
		size++;	
	}
	
	public E removeFirst() {
		if (isEmpty())
			return null;
		
		E element = head.getElement();
		head = head.getNext();
		size--;
		
		if(size == 0)
			tail = null;
		
		return element;
	}
}
