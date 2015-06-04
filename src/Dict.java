import java.util.ArrayList;

public class Dict {
	private ArrayList<Tuple> tuples;

	public Dict() {
		tuples = new ArrayList<Tuple>();
	}

	public void insert(int docID, int location) {
		tuples.add(new Tuple(docID, location));
	}
	
	public void deltaCompression() {
		
		int prevIndex = 0;
		
		for (Tuple i : tuples) {
			if (prevIndex == i.getDocID()) {
				
			}
		}
	}
	
}
