import java.util.ArrayList;

public class Dict {
	private ArrayList<Tuple> tuples;

	public Dict() {
		tuples = new ArrayList<Tuple>();
	}

	public void insert(int docID, int location) {
		tuples.add(new Tuple(docID, location));
	}
	
	public void compress() {
		int sizeDict = tuples.size();
		for (int i=sizeDict-1; i>=1; i--) {
			int diff = tuples.get(i).getDocID() - tuples.get(i-1).getDocID();
			tuples.get(i).setDocID(diff);
		}
	}
	
	public Dict mergeCompressedDict(Dict d1, Dict d2) {
		
		// Exception Handling - one of dictionaries is empty
		if (d1.tuples.isEmpty() || d2.tuples.isEmpty()) {
			return new Dict();
			// return empty dictionary
		}
		
		Dict mergedDict = new Dict();
		
		int doc1;
		// pointer variable to store document id of d1
		int doc2;
		// pointer variable to store document id of d2
		int docM=0;
		// pointer variable to store document id of merged dictionary
		
		doc1 = d1.tuples.get(0).getDocID();
		doc2 = d2.tuples.get(0).getDocID();
		// initialization
		
		int p1 = 1;
		int p2 = 1;
		// pointer variable to track index of d1, d2 (next to read)
		// these are initialized to 1 because we already read the first entry to initialize doc1 and doc2
		
		while (p1 < d1.tuples.size() && p2 < d2.tuples.size()) {
			if (doc1 == doc2) {
				// add tuples that exist on d1 and d2
				mergedDict.tuples.add(new Tuple(doc1-docM, d1.tuples.get(p1-1).getLocation()));
				docM=doc1;
				while (p1 < d1.tuples.size() && d1.tuples.get(p1).getDocID()==0) {
					mergedDict.tuples.add(new Tuple(0,d1.tuples.get(p1).getLocation()));
					p1++;
				}
				
				mergedDict.tuples.add(new Tuple(0, d2.tuples.get(p2-1).getLocation()));
				while (p2 < d2.tuples.size() && d2.tuples.get(p2).getDocID()==0) {
					mergedDict.tuples.add(new Tuple(0,d2.tuples.get(p2).getLocation()));
					p2++;
				}
			} else if (doc1 > doc2) {
				// traverse tuples
				doc2 += d2.tuples.get(p2).getDocID();
				p2++;
			} else if (doc1 < doc2) {
				// we can use just 'else' but for easy-to-read code
				// traverse tuples
				doc1 += d1.tuples.get(p1).getDocID();
				p1++;
			}
		}
		
		return mergedDict;
	}
	
}
