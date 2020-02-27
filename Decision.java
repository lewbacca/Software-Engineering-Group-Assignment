import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map;



public class Decision {
	private Hashtable<Set<Candidate_Employee, String>, Boolean> approval;
	private static Decision decision = null;
	
	private Decision(Hashtable proposals) {
		approval = new Hashtable<Candidate_Employee, String, Boolean>();
		Enumeration <Candidate_Employee> enumeration = proposals.keys();
		while(enumeration.hasMoreElements()) {
			Candidate_Employee key = enumeration.nextElement();
			approval.put(key, proposals.get(key), false);
		}
		//		for(Candidate_Employee key:keys) {
//			approval.put(key, proposals.get(Candidate_Employee key), false);
//		}
			
		
	}
	public static Decision getinstance() {
		if (decision == null) {
			decision = new Decision();
		}
		return decision;
	}
	
}
