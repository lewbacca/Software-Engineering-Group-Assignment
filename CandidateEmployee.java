import java.util.ArrayList;

public class CandidateEmployee extends Staff {
  private ArrayList<String> skills=new ArrayList<String>();
  private static int totalIDs = 0;
  private int ID;
  private boolean approved;
  
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

  public boolean isApproved() {
		return approved;
  }
	
  public void setApproved(boolean approved) {
		this.approved = approved;
  }
	  
}
