import java.util.ArrayList;

public class Dict {

	private ArrayList<Tuple> tuples;

	public Dict() {
		tuples = new ArrayList<Tuple>();
	}
	
	/*
	 * method: insert
	 * inputs: Two integers docID and location.
	 * outputs: None
	 * 
	 * add a tuple with two parameters to this Dict object.
	 */
	public void insert(int docID, int location) {
		tuples.add(new Tuple(docID, location));
	}

	/*
	 * method: compress
	 * inputs: None
	 * outputs: None
	 * 
	 * Delta-Compress this Dict object
	 */
	public void compress() {
		int sizeDict = tuples.size();
		for (int i = sizeDict - 1; i >= 1; i--) {
			int diff = tuples.get(i).getDocID() - tuples.get(i - 1).getDocID();
			tuples.get(i).setDocID(diff);
		}
	}

	/*
	 * method: mergeCompressedDict
	 * inputs: a Dict object dictionary
	 * outputs: a merged Dict object with this Dict object and dictionary Dict obejct
	 */
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

		int p1 = 0;
		int p2 = 0;
		// pointer variable to track index of d1, d2
		// initialize doc1 and doc2

		while (p1 < this.tuples.size() && p2 < dictionary.tuples.size()) {
			if (doc1 == doc2) {
				// add tuples that exist on d1 and d2
				mergedDict.tuples.add(new Tuple(doc1 - docM, this.tuples
						.get(p1).getLocation()));
				p1++;
				docM = doc1;
				while (p1 < this.tuples.size()
						&& this.tuples.get(p1).getDocID() == 0) {
					mergedDict.tuples.add(new Tuple(0, this.tuples.get(p1)
							.getLocation()));
					p1++;
				}

				mergedDict.tuples.add(new Tuple(0, dictionary.tuples.get(p2)
						.getLocation()));
				p2++;
				while (p2 < dictionary.tuples.size()
						&& dictionary.tuples.get(p2).getDocID() == 0) {
					mergedDict.tuples.add(new Tuple(0, dictionary.tuples
							.get(p2).getLocation()));
					p2++;
				}

				//read next entry
				if (p1 < this.tuples.size()) {
					doc1 += this.tuples.get(p1).getDocID();
				}

				if (p2 < dictionary.tuples.size()) {
					doc2 += dictionary.tuples.get(p2).getDocID();
				}

			} else if (doc1 > doc2) {
				// traverse tuples
				p2++;
				//read next entry
				if (p2 < dictionary.tuples.size()) {
					doc2 += dictionary.tuples.get(p2).getDocID();
				}
			} else if (doc1 < doc2) {
				// we can use just 'else' but for easy-to-read code
				// traverse tuples
				p1++;
				//read next entry
				if (p1 < this.tuples.size()) {
					doc1 += this.tuples.get(p1).getDocID();
				}
			}
		}

		return mergedDict;
	}

	/*
	 * method: excludeCompressedDict
	 * inputs: a Dict object dictionary
	 * outputs: a Dict object which excludes dictionary from this Dict object
	 */
	public Dict excludeCompressedDict(Dict dictionary) {
		// Exception Handling - one of dictionaries is empty
		if (this.tuples.isEmpty() || dictionary.tuples.isEmpty()) {
			return this;
			// it looks weird but right :)
			// if this tuple is empty, return empty (this)
			// if dictionary is empty, return this
		}

		Dict excludedDict = new Dict();

		int doc1;
		// pointer variable to store document id of d1
		int doc2;
		// pointer variable to store document id of d2
		int docE = 0;
		// pointer variable to store document id of excluded dictionary

		doc1 = this.tuples.get(0).getDocID();
		doc2 = dictionary.tuples.get(0).getDocID();
		// initialization

		int p1 = 0;
		int p2 = 0;
		// pointer variable to track index of d1, d2
		// initialize doc1 and doc2

		while (p1 < this.tuples.size() && p2 < dictionary.tuples.size()) {
			if (doc1 == doc2) {
				// pass this document
				p1++;
				while (p1 < this.tuples.size()
						&& this.tuples.get(p1).getDocID() == 0) {
					p1++;
				}
				p2++;
				while (p2 < dictionary.tuples.size()
						&& dictionary.tuples.get(p2).getDocID() == 0) {
					p2++;
				}

				//read next entry
				if (p1 < this.tuples.size()) {
					doc1 += this.tuples.get(p1).getDocID();
				}

				//read next entry
				if (p2 < dictionary.tuples.size()) {
					doc2 += dictionary.tuples.get(p2).getDocID();
				}

			} else if (doc1 > doc2) {
				// traverse tuples
				p2++;
				//read next entry
				if (p2 < dictionary.tuples.size()) {
					doc2 += dictionary.tuples.get(p2).getDocID();
				}
			} else if (doc1 < doc2) {
				// we can use just 'else' but for easy-to-read code
				// traverse tuples and add it to excludedDict
				excludedDict.tuples.add(new Tuple(doc1 - docE, this.tuples.get(
						p1).getLocation()));
				docE = doc1;
				p1++;
				//read next entry
				if (p1 < this.tuples.size()) {
					doc1 += this.tuples.get(p1).getDocID();
				}
			}
		}

		while (p1 < this.tuples.size()) {
			// add everything in this to excludedDict
			if (doc1 != doc2) {
				excludedDict.tuples.add(new Tuple(doc1 - docE, this.tuples.get(
						p1).getLocation()));
				docE = doc1;
				p1++;
			}
			//read next entry
			if (p1 < this.tuples.size()) {
				doc1 += this.tuples.get(p1).getDocID();
			}
		}

		return excludedDict;
	}

	/*
	 * method: printDict
	 * inputs: None
	 * outputs: None
	 * 
	 * prints this Dict object's tuples
	 */
	public void printDict() {
		System.out.println("Search results:");
		for (Tuple t : this.tuples) {
			t.printTuple();
			System.out.print(" ");
		}
		System.out.println();
	}

}
