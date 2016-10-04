package edu.neu.algos.assgn10;

public class LinkedListNode<E> {
	private E data = null;
	private LinkedListNode<E> nextNode = null;
	
	public LinkedListNode(E data){
		this.data = data;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public LinkedListNode<E> getNextNode() {
		return nextNode;
	}

	public void setNextNode(LinkedListNode<E> nextNode) {
		this.nextNode = nextNode;
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof LinkedListNode<?>){
			@SuppressWarnings("unchecked")
			LinkedListNode<E> oAsNode = (LinkedListNode<E>) o;
			return oAsNode.data.equals(this.data);
		}
		return false;
	}
		
}
