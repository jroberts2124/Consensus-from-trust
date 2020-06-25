public class Candidate {
	Transaction tx; //transaction recieved
	int sender; //source of transaction
	
	public Candidate(Transaction tx, int sender) {
		this.tx = tx;  //references to tx
		this.sender = sender; //references to sender node
	}
}
