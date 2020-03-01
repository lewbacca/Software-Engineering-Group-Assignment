import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.Serializable;
public class Administrator extends Staff  implements Serializable{

	private static Administrator admin = null;
	private ArrayList<CandidateEmployee> candidates = new ArrayList<CandidateEmployee>();
	private HashMap<CandidateEmployee, String> proposals = new HashMap<CandidateEmployee, String>();
	private HashMap<Entry<CandidateEmployee, String>, String> trainees = new HashMap<Entry<CandidateEmployee, String>, String>();

	private Administrator(){
		name="Brad Pitt";
		ID=2;
		title="Administrator";
		password="iamadministrator";
	}
	
	public static Administrator getInstance() {
		if (admin == null) {
			admin = new Administrator();
		}
		return admin;
	}
	
	public void requestDecision(CandidateEmployee candidate, String requirement) {
		proposals.put(candidate, requirement);
	}

	public void checkForTrainees() {
		trainees=Decision.getInstance().removeRejects();
	}
	public void addCandidate(CandidateEmployee candidate) {
		candidates.add(candidate);
	}
	public void addTrainee(Entry<CandidateEmployee, String> entry, String comment) {
		trainees.put(entry, comment);
	}

	public HashMap<CandidateEmployee, String> getProposals() {
		return proposals;
	}

	public ArrayList<CandidateEmployee> getCandidates() {
		return candidates;
	}

	public HashMap<Entry<CandidateEmployee, String>, String> getTrainees() {
		return trainees;
	}
	 public Object readResolve() {
	       return getInstance( );
	}
	

	
}
