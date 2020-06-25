import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import Candidate.java

/* CompliantNode refers to a node that follows the rules (not malicious)*/
public class CompliantNode implements Node {

    private double p_graph;  /* sets double for graph */
    private double p_malicious; /* sets double for malicious nodes */
    private double p_tXDistribution; /* sets double for transaction distributioin */
    private int numRounds; /* sets integer for number of consensus rounds */
    private int currentRound; /* sets int for current rouns */
    private boolean[] followees; /* sets boolean for followers */
    private Set<Transaction> pendingTransactions;  /* set for pending transations */
    private boolean[] blackListed; /* sets boolean value for blacklisted followers */
    private Set<Transaction> consensusTrans; //current transaction in process consensus  
    private int oldRound; /* set int for old rounds*/

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.p_tXDistribution = p_txDistribution; // assigns _p_txDistribution to the p_txDistribtion node probablity
        this.p_malicious = p_malicious; // assigns malicious to prob of neing malicious
        this.p_graph = p_graph; // assigns _p_graphs to graph
        this.numRounds = numRounds; // assigns numrounds

    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees; // assigns followees 
        this.blackListed = new boolean[followees.length]; // set blacklisted followees into an array
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        
        this.pendingTransactions = pendingTransactions; // set pending transactions
    }

    public Set<Transaction> sendToFollowers() {
        Set<Transaction>  chosenCandidate = new HashSet<>(pendingTransactions); // set transaction to new set
        
        pendingTransactions.clear(); //to prevent duplicate transaction by erasing it once the transaction is chosen
        
        return chosenCandidate; // return chosen candidate for consensus  
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {
        Set<Integer> senders = candidates.stream().map(c -> c.sender).collect(toSet());
        // locates the sender nodes of a pending transaction in the trust graph
        

       

        for (int i = 0; i <followees.length; i++) { // for followees in set
            for (Candidate c : candidates) { // for any candidate in candidate class Candidate.java

                if (!blackListed[c.sender]) { // if a candaidate is not in the  blacklist
    
                    pendingTransactions.add(c.tx); // add transaction to valid 
                    
                }

            if (followees[i] && !senders.contains(i)) // if followees not blacklisted
                blackListed[i] = true; // followee is added to the blacklist list
        }
        
        }
    }

    @Override
    public Set<Transaction> getProposals() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void receiveCandidates(ArrayList<Integer[]> candidates) {
        // TODO Auto-generated method stub

    }
}
