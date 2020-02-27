import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;



public class Decision {
	private HashMap<Entry<CandidateEmployee, String>, Boolean> approvals;
	private static Decision decision = null;
	
	private Decision() {
		
		approvals = new HashMap<Entry<CandidateEmployee , String>, Boolean>();
		Set<Entry<CandidateEmployee, String>> entries = Administrator.getInstance().getProposals().entrySet();
		
		for(Entry<CandidateEmployee, String> entry: entries) {
			approvals.put(entry, null);
		}

	}
	public static Decision getInstance() {
		if (decision == null) {
			decision = new Decision();
		}
		return decision;
	}
	public HashMap<Entry<CandidateEmployee, String>, Boolean> getApprovals() {
		return approvals;
	}
	public void setApprovals(Entry<CandidateEmployee, String> key, boolean approved) {
		approvals.put(key, approved);
	}
	
	public HashMap<Entry<CandidateEmployee, String>, String> removeRejects() {
		HashMap<Entry<CandidateEmployee, String>, String> winners = new HashMap<Entry<CandidateEmployee, String>, String>();
		Set<Entry<CandidateEmployee, String>> all= approvals.keySet();
		for(Entry<CandidateEmployee, String> entry: all) {
			if (approvals.get(entry)==true) {
				winners.put(entry, null);
			}
		}
		return winners;
	}
	
}
