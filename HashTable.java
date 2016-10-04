package edu.neu.algos.assgn10;

import java.util.ArrayList;
import java.util.List;

public class HashTable<KeyType, ValueType> {

	static final int MAXIMUM_CAPACITY = 1 << 30;
	private ArrayList<LinkedList<HashTableEntry<KeyType, ValueType>>> myArr = new ArrayList<LinkedList<HashTableEntry<KeyType, ValueType>>>();

	private List<KeyType> keyList = new ArrayList<KeyType>();

	public static void main(String args[]) {
		HashTable<String, String> ht = new HashTable<String, String>();
		System.out.println(ht.computeHash("ab"));
	}

	private int computeHash(KeyType key) {
		int hashCode = 7;
		if (key instanceof String) {
			String k = (String) key;
			for (int idx = 0; idx < k.length(); idx++) {
				hashCode = hashCode * 31 + k.charAt(idx);
			}
		} else {
			// TODO: Alternatively we call the hashCode method built in to the
			// class itself?
			System.err.println("Unsupported key type");
		}
		return hashCode % MAXIMUM_CAPACITY;
	}

	public void insert(KeyType key, ValueType value) {

		int hashCode = computeHash(key);
		myArr.ensureCapacity(hashCode+1);
		HashTableEntry<KeyType, ValueType> hte = new HashTableEntry<KeyType, ValueType>(
				key, value);
		LinkedListNode<HashTableEntry<KeyType, ValueType>> lln = new LinkedListNode<HashTableEntry<KeyType, ValueType>>(hte);
		LinkedList<HashTableEntry<KeyType, ValueType>> hteList = new LinkedList<HashTableEntry<KeyType, ValueType>>();		
		
		hteList = myArr.get(hashCode);
		
		// First check if hteList is not null. If it is, lets create a new linked list.
		if (hteList == null) {
			hteList = new LinkedList<HashTableEntry<KeyType, ValueType>>();
			myArr.add(hashCode, hteList);
		}
		// Now check that the given key is not already in the list
		LinkedListNode<HashTableEntry<KeyType, ValueType>> hteListHead = hteList.getHead();
		while(hteListHead != null){
			if(hteListHead.getData().getKey().equals(key)){
				// It is already in the linked list. Either replace original value
				// or don't.
				hteListHead.getData().setValue(value);
				hteListHead.getNextNode();
				return;
			}
		}
		// If we haven't returned yet, means key was not found.
		// So, lets add it
		hteList.append(lln);
		keyList.add(key);
	}

	public ValueType find(KeyType key) {
		int hashCode = computeHash(key);
		if(myArr.size()<hashCode)
			return null;
		LinkedList<HashTableEntry<KeyType, ValueType>> hteList = myArr.get(hashCode);
		if (hteList == null) {
			return null;
		} else {
			LinkedListNode<HashTableEntry<KeyType, ValueType>> hteNode = hteList
					.getHead();
			while (hteNode != null) {
				if (key.equals(hteNode.getData().getKey())) {
					return hteNode.getData().getValue();
				}
				hteNode = hteNode.getNextNode();
			}
		}
		return null;
	}

	public void increase(KeyType key) {
		ValueType value = find(key);
		if (value != null) {
			if (value instanceof Integer) {
				Integer valAsInt = (Integer) value;
				valAsInt += 1;
			} else {
				System.err
						.println("Value is not an integer type. Can't increment");
			}
		}
	}

	public void delete(KeyType key) {

		int hashCode = computeHash(key);
		LinkedList<HashTableEntry<KeyType, ValueType>> hteList = myArr
				.get(hashCode);
		LinkedListNode<HashTableEntry<KeyType, ValueType>> hteNode = hteList
				.getHead();
		while (hteNode != null) {
			if (key.equals(hteNode.getNextNode().getData().getKey())) {
				hteNode.setNextNode(hteNode.getNextNode().getNextNode());
				return;
			}
		}
		System.err.println("Could not locate the key " + key);
	}

	public void listAllKeys() {
		for (KeyType key : keyList) {
			System.out.println(key);
		}

	}

}
