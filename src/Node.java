import java.util.ArrayList;


public class Node {
	private char value;
	private ArrayList<Node> nextNodes;
	private Dict dictonary;
	
	public Node(char character) {
		value = character;
		nextNodes = new ArrayList<Node>();
	}
}
