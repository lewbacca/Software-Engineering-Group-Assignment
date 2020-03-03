import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.Set;

public class Administrator extends Staff implements Serializable{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private static Administrator admin = null;
	private ArrayList<CandidateEmployee> candidates;
	////
	private ArrayList<String> trying;
	////
	private HashMap<CandidateEmployee, String> proposals;
	private HashMap<Entry<CandidateEmployee, String>, String> trainees;
	private String name,title,password;
	private int ID;
	private Administrator(){
		super();
		name="Brad Pitt";
		ID=2;
		title="Administrator";
		password="iamadministrator";
		candidates = new ArrayList<CandidateEmployee>();
		proposals = new HashMap<CandidateEmployee, String>();
		trainees = new HashMap<Entry<CandidateEmployee, String>, String>();
		////
		trying  = new ArrayList<String>();
		////
	}
	
	////
	public void addTrying(String s)
	{
		trying.add(s);
	}
	  
	
	public ArrayList<String> getTrying() {
		return trying;
	}
	////
	
	public static synchronized void read(ObjectInputStream in){

        try{
        	
        	admin = (Administrator)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
		Set<Entry<CandidateEmployee, String>> approvals = Decision.getInstance().getApprovals().entrySet();
		for(Entry<CandidateEmployee, String> entry: approvals) {
			trainees.put(entry, "");
		}
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
	//@Override
	/* protected Object readResolve() {
	       return getInstance();
	}*/
	public String getName() {
		return name;
	}
	public String getTitle() {
		return title;
	}
	public int getID() {
		return ID;
	}
	
	public String getPassword() {
		return password;
	}
	

	
}
