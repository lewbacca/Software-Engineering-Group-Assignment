import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;



public class Decision {
	private HashMap<CandidateEmployee, String> approvals;
	private HashMap<CandidateEmployee, String> proposals;
	private static Decision decision = null;
	
	private Decision() {
		approvals = new HashMap<CandidateEmployee , String>();
		proposals = Administrator.getInstance().getProposals();
	}
	
	public static Decision getInstance() {
		if (decision == null) {
			decision = new Decision();
		}
		return decision;
	}
	public HashMap<CandidateEmployee, String> getApprovals() {
		return approvals;
	}
	
	public void updateApprovals() {
		for(Entry<CandidateEmployee, String> entry: proposals.entrySet()) {
			if (entry.getKey().isApproved()) {
				approvals.put(entry.getKey(), entry.getValue());
				
			}
		}
	}
	
	public void updateProposals() {
		for(Entry<CandidateEmployee, String> entry: approvals.entrySet()) {
			if (entry.getKey().isApproved()) {
				proposals.remove(entry.getKey(), entry.getValue()); //assures only this tuple is removed, in case the candidate was proposed for another position
			}
		}
	}
	public HashMap<CandidateEmployee, String> getProposals() {
		return proposals;
	}

	public void removeProposal(int index) {
		ArrayList<CandidateEmployee> toBeRemoved=new ArrayList<CandidateEmployee>();
		for(CandidateEmployee c: proposals.keySet()) {
			if(c.getID()==index) {
				toBeRemoved.add(c);
			}
		}
		for(CandidateEmployee c:toBeRemoved) {
			proposals.remove(c);
		}
	}

	public void approve(int index) {
		for(CandidateEmployee c: proposals.keySet()) {
			if(c.getID()==index) {
				c.setApproved(true);
			}
		}
		
	}
}
