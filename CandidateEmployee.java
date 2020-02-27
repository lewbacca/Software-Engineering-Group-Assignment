import java.util.ArrayList;

public class CandidateEmployee extends Staff {
  private ArrayList<String> skills=new ArrayList<String>();
  
  public CandidateEmployee(String name, int ID, String title){
	  this.name=name;
	  this.ID=ID;
	  this.title=title;
	  Administrator.getInstance().addCandidate(this);
  }
  
  public void addSkill(String skill) {
	skills.add(skill);  
  }
  
}
