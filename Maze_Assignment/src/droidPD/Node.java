package droidPD;
import droidPD.Node;

public class Node<E> {
	private E element;
	private Node<E> previous;
	private Node<E> next;
	
	public Node(E element, Node<E> next) {
		this.element = element;
		this.next = next;
	}
	
	//returns:
	//		data of node
	public E getElement() {
		return element;
	}
	
	//returns:
	//		next node
	public Node<E> getNext() {
		return next;
	}
	
	//element	-	data for Node to hold
	public void setElement(E element) {
		this.element = element;
	}
	
	//next		-	next node to set
	public void setNext(Node<E> next) {
		this.next = next;
	}

}
