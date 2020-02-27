import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Administrator extends Staff {

	private static Administrator admin = null;
	private ArrayList<CandidateEmployee> candidates = new ArrayList<CandidateEmployee>();
	private HashMap<CandidateEmployee, String> proposals = new HashMap<CandidateEmployee, String>();
	private HashMap<Entry<CandidateEmployee, String>, String> trainees = new HashMap<Entry<CandidateEmployee, String>, String>();
	private Administrator(){
		name="Brad Pitt";
		ID=2;
		title="Administrator";
	}
	
	public static Administrator getInstance() {
		if (admin == null) {
			admin = new Administrator();
		}
		return admin;
	}
	
	public void requestDecision(int indexOfCandidate, int indexOfRequirement) {
		proposals.put(candidates.get(indexOfCandidate), TeachingRequirements.getInstance().getListOfRequirements().get(indexOfRequirement));
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
	
}
