import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;



public class Decision {
	private HashMap<Entry<Candidate_Employee, String>, Boolean> approvals;
	private static Decision decision = null;
	
	private Decision() {
		
		approvals = new HashMap<Entry<Candidate_Employee , String>, Boolean>();
		Set<Entry<Candidate_Employee, String>> entries = Administrator.getInstance().getProposals().entrySet();
		
		for(Entry<Candidate_Employee, String> entry: entries) {
			approvals.put(entry, null);
		}

	}
	public static Decision getInstance() {
		if (decision == null) {
			decision = new Decision();
		}
		return decision;
	}
	public HashMap<Entry<Candidate_Employee, String>, Boolean> getApprovals() {
		return approvals;
	}
	public void setApprovals(Entry<Candidate_Employee, String> key, boolean approved) {
		approvals.put(key, approved);
	}
	
}
