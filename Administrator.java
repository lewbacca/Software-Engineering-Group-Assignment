import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

/*
 * this class allows the user to have certain capabilities like assign training and create proposals, it uses the singleton pattern since there is only one
 */
public class Administrator extends Staff implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Administrator admin = null;
	private ArrayList<CandidateEmployee> candidates; //a list of possible candidate employees to use in proposals
	private HashMap<CandidateEmployee, String> proposals; //hash map of candidates and teaching requirement they are being proposed for
	private String name,title,password; 
	private int ID;
	private Administrator(){
		super();
		name="Brad Pitt"; //we were going for an ocean's eleven thing in the beginning 
		ID=2; //needed for login
		title="Administrator";
		password="iamadministrator"; //password needed for login
		candidates = new ArrayList<CandidateEmployee>();
		proposals = new HashMap<CandidateEmployee, String>();
	}
/*
 * this method is used for serialisation, needs to be here because this is a singleton	
 */
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
	/**
	 * puts entries in the proposals hash map
	 * @param candidate
	 * @param requirement
	 */
	public void requestDecision(CandidateEmployee candidate, String requirement) {
		proposals.put(candidate, requirement); 
	}

	public void addCandidate(CandidateEmployee candidate) {
		candidates.add(candidate);
	}

	public HashMap<CandidateEmployee, String> getProposals() {
		return proposals;
	}

	public ArrayList<CandidateEmployee> getCandidates() {
		return candidates;
	}

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
