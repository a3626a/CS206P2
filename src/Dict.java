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
		for (int i = sizeDict - 1; i >= 1; i--) {
			int diff = tuples.get(i).getDocID() - tuples.get(i - 1).getDocID();
			tuples.get(i).setDocID(diff);
		}
	}

	public Dict mergeCompressedDict(Dict dictionary) {
		// Exception Handling - one of dictionaries is empty
		if (this.tuples.isEmpty() || dictionary.tuples.isEmpty()) {
			return new Dict();
			// return empty dictionary
		}

		Dict mergedDict = new Dict();

		int doc1;
		// pointer variable to store document id of d1
		int doc2;
		// pointer variable to store document id of d2
		int docM = 0;
		// pointer variable to store document id of merged dictionary

		doc1 = this.tuples.get(0).getDocID();
		doc2 = dictionary.tuples.get(0).getDocID();
		// initialization

		int p1 = 1;
		int p2 = 1;
		// pointer variable to track index of d1, d2 (next to read)
		// these are initialized to 1 because we already read the first entry to
		// initialize doc1 and doc2

		while (p1 < this.tuples.size() && p2 < dictionary.tuples.size()) {
			if (doc1 == doc2) {
				// add tuples that exist on d1 and d2
				mergedDict.tuples.add(new Tuple(doc1 - docM, this.tuples.get(
						p1 - 1).getLocation()));
				docM = doc1;
				while (p1 < this.tuples.size()
						&& this.tuples.get(p1).getDocID() == 0) {
					mergedDict.tuples.add(new Tuple(0, this.tuples.get(p1)
							.getLocation()));
					p1++;
				}

				mergedDict.tuples.add(new Tuple(0, dictionary.tuples
						.get(p2 - 1).getLocation()));
				while (p2 < dictionary.tuples.size()
						&& dictionary.tuples.get(p2).getDocID() == 0) {
					mergedDict.tuples.add(new Tuple(0, dictionary.tuples
							.get(p2).getLocation()));
					p2++;
				}
				
				if (p1 < this.tuples.size()) {
					doc1 += this.tuples.get(p1).getDocID();
					p1++;
				}
				
				if (p2 < this.tuples.size()) {
					doc2 += this.tuples.get(p2).getDocID();
					p2++;
				}
				
			} else if (doc1 > doc2) {
				// traverse tuples
				doc2 += dictionary.tuples.get(p2).getDocID();
				p2++;
			} else if (doc1 < doc2) {
				// we can use just 'else' but for easy-to-read code
				// traverse tuples
				doc1 += this.tuples.get(p1).getDocID();
				p1++;
			}
		}

		return mergedDict;
	}

	public Dict excludeCompressedDict(Dict dictionary) {
		// Exception Handling - one of dictionaries is empty
		if (this.tuples.isEmpty() || dictionary.tuples.isEmpty()) {
			return this;
			// this code looks weird but right :)
			// if this.tuples.isEmpty()==true, return empty dict(this)
			// if dictionary.tuples.isEmpty()==true, return same dict(this)
		}

		Dict excludeDict = new Dict();

		int doc1;
		// pointer variable to store document id of d1
		int doc2;
		// pointer variable to store document id of d2
		int docM = 0;
		// pointer variable to store document id of merged dictionary

		doc1 = this.tuples.get(0).getDocID();
		doc2 = dictionary.tuples.get(0).getDocID();
		// initialization

		int p1 = 1;
		int p2 = 1;
		// pointer variable to track index of d1, d2 (next to read)
		// these are initialized to 1 because we already read the first entry to
		// initialize doc1 and doc2

		while (p1 < this.tuples.size() && p2 < dictionary.tuples.size()) {
			if (doc1 == doc2) {
				// add tuples that exist on d1 and d2
				excludeDict.tuples.add(new Tuple(doc1 - docM, this.tuples.get(
						p1 - 1).getLocation()));
				docM = doc1;
				while (p1 < this.tuples.size()
						&& this.tuples.get(p1).getDocID() == 0) {
					excludeDict.tuples.add(new Tuple(0, this.tuples.get(p1)
							.getLocation()));
					p1++;
				}

				excludeDict.tuples.add(new Tuple(0, dictionary.tuples
						.get(p2 - 1).getLocation()));
				while (p2 < dictionary.tuples.size()
						&& dictionary.tuples.get(p2).getDocID() == 0) {
					excludeDict.tuples.add(new Tuple(0, dictionary.tuples
							.get(p2).getLocation()));
					p2++;
				}
			} else if (doc1 > doc2) {
				// traverse tuples
				doc2 += dictionary.tuples.get(p2).getDocID();
				p2++;
			} else if (doc1 < doc2) {
				// we can use just 'else' but for easy-to-read code
				// traverse tuples
				doc1 += this.tuples.get(p1).getDocID();
				p1++;
			}
		}

		return excludeDict;
	}

	public void printDict() {
		System.out.println("Search results:");
		for (Tuple t : this.tuples) {
			t.printTuple();
			System.out.print(" ");
		}
		System.out.println();
	}

}
