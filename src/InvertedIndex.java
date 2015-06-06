
public class InvertedIndex {
	
	private Node root;
	private boolean isCompressed;
	
	public InvertedIndex() {
		isCompressed = false;
		root = new Node(' ');
	}

	/*
	 *  method: add
	 *  inputs: String key, int docID, int location
	 *  outputs: none
	 *  
	 *  This method adds key with the tuple (docID, location).
	 */
	public void add(String key, int docID, int location) {
		Node target = find(key);
		if (target == null)
			target = make(key);
		target.addData(docID, location);
	}
	
	/*
	 *  method: find
	 *  inputs: String key
	 *  outputs: Node which corresponds to the last digit of the key.
	 *  
	 *  Note that every node corresponds 1-1 to the word, in a way that the node contains
	 *  the last character of the word.
	 *  This method returns such node when a word is given as key.
	 *  In other words, it returns the node that corresponds to the last character of the key. 
	 *  If a given key does not yet extended in the index tree, then it returns NULL.
	 */
	public Node find(String key) {
		return root.find(key);
	}
	
	/*
	 *  method: make
	 *  inputs: String key
	 *  outputs: Node which corresponds to the last digit of the key.
	 *  
	 *  Extended version of find method.
	 *  If key does not exists, then it extends the tree and then returns the corresponding node.
	 */
	public Node make(String key) {
		return root.make(key);
	}
	
	/*
	 *  method: deltaCompression ()
	 *  inputs: none
	 *  outputs: none
	 *  
	 *  Delta-compresses the inverted index.
	 */
	public void deltaCompression () {
		root.traverseAndCompress();
		isCompressed = true;
	}
}
