import java.util.ArrayList;
import java.io.Serializable;
public class CandidateEmployee extends Staff implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private ArrayList<String> skills=new ArrayList<String>();
  
  public CandidateEmployee(String name, int ID, String title){
	  this.name=name;
	  this.ID=ID;
	  this.title=title;
	  Administrator.getInstance().addCandidate(this);
  }

  public ArrayList<String> getSkills() {
	return skills;
  }
  
  public void addSkill(String skill) {
	  skills.add(skill);
  }
  public int getID() {
	  return ID;
  }
}
