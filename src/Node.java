import java.util.ArrayList;

public class Node {
	
	private char value;
	private ArrayList<Node> nextNodes;
	private Dict dictionary;

	public Node(char character) {
		value = character;
		nextNodes = new ArrayList<Node>();
	}
	
	/*
	 *  method: createDictionary
	 *  inputs: none
	 *  outputs: none
	 *  
	 *  Initializing Dict object is separated because this Node object is designed to have
	 *  initialized Dict object when at least one of that key is found. Otherwise it remains
	 *  as null.
	 */
	public void createDictionary() {
		dictionary = new Dict();
	}
	
	/*
	 *  method: find
	 *  inputs: String key
	 *  outputs: A node corresponds to the last char of key.
	 *  
	 *  This method works recursively. It finds a node corresponds to the last character of key.
	 *  If such node does not exist it returns null.
	 */
	public Node find(String key) {
		if (key.length() > 1) {
			char first = key.charAt(0);
			String nextInput = key.substring(1, key.length());
			for (Node i : nextNodes) {
				if (i.getValue() == first)
					return i.find(nextInput);
			}
			return null;
		} else {
			char first = key.charAt(0);
			for (Node i : nextNodes) {
				if (i.getValue() == first && i.dictionary != null)
					return i;
			}
			return null;
		}
	}

	/*
	 *  method: make
	 *  inputs: String key
	 *  outputs: A node corresponds to the last char of key.
	 *  
	 *  This method works recursively. It finds a node corresponds to the last character of key.
	 *  The main difference with find method is, if such node does not exist this method extends
	 *  the tree to contain the key and then return the node of the last character of key, while
	 *  find method returns null.
	 */
	public Node make(String key) {
		if (key.length() > 1) {
			char first = key.charAt(0);
			String nextInput = key.substring(1, key.length());

			for (Node i : nextNodes) {
				if (i.getValue() == first) {
					return i.make(nextInput);
				}
			}

			Node nextNode = new Node(first);
			nextNodes.add(nextNode);
			return nextNode.make(nextInput);
		} else {
			char first = key.charAt(0);

			for (Node i : nextNodes) {
				if (i.getValue() == first) {
					i.createDictionary();
					return i;
				}
			}

			Node nextNode = new Node(first);
			nextNodes.add(nextNode);
			nextNode.createDictionary();
			return nextNode;
		}
	}
	
	/*
	 *  method: addData
	 *  inputs: Two integers docID and location.
	 *  outputs: none
	 *  
	 *  Since the corresponding node is found by make/find methods, this method just simply
	 *  adds the tuple (docID, location) to this Node object's Dict object.
	 */
	public void addData(int docID, int location) {
		this.dictionary.insert(docID, location);
	}
	
	/*
	 *  method: traverseAndCompress
	 *  inputs: none
	 *  outputs: none
	 *  
	 *  This method works recursively. It traverses through the tree, and if a node has
	 *  initialized Dict object, this method delta-compresses that object.
	 */
	public void traverseAndCompress () {
		if (this.dictionary != null) {
			this.dictionary.compress();
		}
		for (Node nextElement : this.nextNodes) {
			nextElement.traverseAndCompress();
		}
	}

	/*
	 *  method: getValue
	 *  inputs: none
	 *  outputs: char value of this node.
	 */
	public char getValue() {
		return this.value;
	}
	
	/*
	 *  method: getList
	 *  inputs: none
	 *  outputs: A Dict object of this Node object.
	 */
	public Dict getList() {
		return this.dictionary;
	}
}
