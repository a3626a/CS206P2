import java.util.ArrayList;

public class InvertedIndex {
	private Node root;
	private boolean isCompressed;

	public InvertedIndex() {
		isCompressed = false;
		root = new Node(' ');
	}

	public void add(String key, int docID, int location) {
		Node target = find(key);
		if (target == null)
			target = make(key);
		target.addData(docID, location);
	}
	
	public Node find(String key) {
		return root.find(key);
	}

	public Node make(String key) {
		return root.make(key);
	}

	public void deltaCompression () {
		traverseAndCompress (root);
		isCompressed = true;
	}
	
	public void traverseAndCompress (Node now) {
		Dict now_list = now.getList();
		if (now_list != null) now_list.compress();
		
		ArrayList<Node> nextNodes = now.getNextNodes();
		for (Node nextElement : nextNodes) {
			traverseAndCompress (nextElement);
		}
	}
}
