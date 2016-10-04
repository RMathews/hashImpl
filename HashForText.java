package edu.neu.algos.assgn10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashForText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("H:\\InputTextSearch");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			HashTable<String, Integer> WordCountHashTable = new HashTable<String, Integer>();
			while (sc.hasNext()) {
				String line = sc.nextLine();
				for (String word : line.split(" ")) {
					
					Integer count= WordCountHashTable.find(word);
					count = (count==null)? 0 : ++count;  // or call increase(word) instead of ++count
					
					WordCountHashTable.insert(word, count);
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}

}
