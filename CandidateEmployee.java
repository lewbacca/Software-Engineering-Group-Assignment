import java.util.ArrayList;
import java.io.Serializable;
public class CandidateEmployee implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 private ArrayList<String> skills=new ArrayList<String>();
  private static int totalIDs = 0;
  private int ID;
  private boolean approved;
  private String name;
  private String title;
  
public CandidateEmployee(String name, String title){
	  this.name=name;
	  this.ID=totalIDs+1;
	  this.title=title;
	  approved=false;
	  totalIDs++;
	  Administrator.getInstance().addCandidate(this);
  }

  public ArrayList<String> getSkills() {
	return skills;
  }
  public static int getTotalIDs() {
		return totalIDs;
  }
  public void addSkill(String skill) {
	  skills.add(skill);
  }
  public int getID() {
	  return ID;
  }
  public String getName() {
	  return name;
  }
  public boolean isApproved() {
		return approved;
  }
	
  public void setApproved(boolean approved) {
		this.approved = approved;
  }
  public void setTitle(String title){
	  this.title=title;
  }
	  
  public String toString() {
	  return name+" "+ID+" "+title;
  }
	public String getTitle() {
		return title;
	}
}
