
public class InvertedIndex {
	private Node root;
	private boolean isCompressed;
	
	public InvertedIndex() {
		isCompressed = false;
		root = new Node(' ');
	}
	
	public boolean find(String key) {
		return root.find(key);
	}
	
	public void make(String key) {
		root.make(key);
	}
	
}
