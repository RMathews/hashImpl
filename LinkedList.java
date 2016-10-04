package edu.neu.algos.assgn10;

public class LinkedList<E> {
	LinkedListNode<E> head = null;
	
	public void append(LinkedListNode<E> newNode){
		if(head == null){
			head = newNode;
		}
		LinkedListNode<E> node = head;
		while(node.getNextNode() != null){
			node = node.getNextNode();
		}
		node.setNextNode(newNode);
	}
	
	public LinkedListNode<E> get(LinkedListNode<E> entry){
		LinkedListNode<E> node = head;
		while(node.getNextNode() != null){
			if(entry.equals(node)){
				return node;
			}
			node = node.getNextNode();
		}
		return null;
	}
	
	public LinkedListNode<E> getHead(){
		return this.head;
	}	
	
}
