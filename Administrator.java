import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Administrator extends Staff {

	private static Administrator admin = null;
	private ArrayList<Candidate_Employee> candidates = new ArrayList<Candidate_Employee>();
	private HashMap<Candidate_Employee, String> proposals = new HashMap<Candidate_Employee, String>();
	private HashMap<Entry<Candidate_Employee, String>, String> trainees = new HashMap<Entry<Candidate_Employee, String>, String>();
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
		proposals.put(candidates.get(indexOfCandidate), Teaching_Requirements.getInstance().getListOfRequirements().get(indexOfRequirement));
	}
	
	public void addCandidate(Candidate_Employee candidate) {
		candidates.add(candidate);
	}
	public void addTrainee(Entry<Candidate_Employee, String> entry, String comment) {
		trainees.put(entry, comment);
	}

	public HashMap<Candidate_Employee, String> getProposals() {
		return proposals;
	}
	
}
