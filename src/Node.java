import java.util.ArrayList;

public class Node {
	private char value;
	private ArrayList<Node> nextNodes;
	private Dict dictonary;

	public Node(char character) {
		value = character;
		nextNodes = new ArrayList<Node>();
	}

	/**
	 * Don't be confused! This method finds the target node among the childs of
	 * this node It's means the first letter of key doesn't match with this
	 * node.
	 * 
	 * @param key
	 * @return
	 */
	public boolean find(String key) {
		if (key.length() > 1) {
			char first = key.charAt(0);
			String nextInput = key.substring(1, key.length());
			for (Node i : nextNodes) {
				if (i.getValue() == first)
					return i.find(nextInput);
			}
			return false;
		} else {
			char first = key.charAt(0);
			for (Node i : nextNodes) {
				if (i.getValue() == first)
					return true;
			}
			return false;
		}
	}

	public void make(String key) {
		if (key.length() > 1) {
			char first = key.charAt(0);
			String nextInput = key.substring(1, key.length());
			
			boolean exist = false;
			
			for (Node i : nextNodes) {
				if (i.getValue() == first) {
					i.make(nextInput);
					exist = true;
				}
			}
			
			if (!exist) {
				Node nextNode = new Node(first);
				nextNodes.add(nextNode);
				nextNode.make(nextInput);
			}
			
		} else {
			char first = key.charAt(0);
			
			boolean exist = false;
			
			for (Node i : nextNodes) {
				if (i.getValue() == first) {
					exist = true;
				}
			}

			if (!exist) {
				Node nextNode = new Node(first);
				nextNodes.add(nextNode);
			}
		}
	}
	
	public char getValue() {
		return this.value;
	}

}
