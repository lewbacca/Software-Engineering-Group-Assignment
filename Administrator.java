import java.util.ArrayList;
import java.util.Hashtable;

public class Administrator extends Staff {

	private static Administrator admin = null;
	private ArrayList<Candidate_Employee> candidates = new ArrayList<Candidate_Employee>();
	private Hashtable<Candidate_Employee, String> proposals = new Hashtable<Candidate_Employee, String>();
	private Administrator(){
		name="Brad Pitt";
		ID=2;
		title="Administrator";
	}
	
	public static Administrator getinstance() {
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

	public Hashtable<Candidate_Employee, String> getProposals() {
		return proposals;
	}
	
}
