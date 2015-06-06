import java.util.ArrayList;

public class Node {
	private char value;
	private ArrayList<Node> nextNodes;
	private Dict dictonary;

	public Node(char character) {
		value = character;
		nextNodes = new ArrayList<Node>();
	}

	public void createDictionary() {
		dictonary = new Dict();
	}

	/**
	 * Don't be confused! This method finds the target node among the childs of
	 * this node It's means the first letter of key doesn't match with this
	 * node.
	 * 
	 * @param key
	 * @return
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
				if (i.getValue() == first)
					return i;
			}
			return null;
		}
	}

	public Node make(String key) {
		if (key.length() > 1) {
			char first = key.charAt(0);
			String nextInput = key.substring(1, key.length());

			boolean exist = false;

			for (Node i : nextNodes) {
				if (i.getValue() == first) {
					return i.make(nextInput);
				}
			}

			Node nextNode = new Node(first);
			nextNodes.add(nextNode);
			nextNode.make(nextInput);
			return nextNode;
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

	public char getValue() {
		return this.value;
	}

	public void addData(int docID, int location) {
		this.dictonary.insert(docID, location);
	}

}
