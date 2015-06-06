public class Tuple {
	private int docID;
	private int location;

	public Tuple(int docID, int location) {
		this.docID = docID;
		this.location = location;
	}

	public int getDocID() {
		return docID;
	}

	public int getLocation() {
		return location;
	}
	
	public void setDocID (int n) {
		docID = n;
	}
}
