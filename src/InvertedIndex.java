public class InvertedIndex {
	private Node root;
	private boolean isCompressed;

	public InvertedIndex() {
		isCompressed = false;
		root = new Node(' ');
	}

	public Node find(String key) {
		return root.find(key);
	}

	public Node make(String key) {
		return root.make(key);
	}

	public void add(String key, int docID, int location) {
		Node target = find(key);
		if (target == null)
			target = make(key);
		target.addData(docID, location);

	}
}
