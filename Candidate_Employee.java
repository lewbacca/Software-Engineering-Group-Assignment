import java.util.ArrayList;

public class Candidate_Employee extends Staff {
  private ArrayList<String> skills=new ArrayList<String>();
  
  public Candidate_Employee(String name, int ID, String title){
	  this.name=name;
	  this.ID=ID;
	  this.title=title;
	  Administrator.getinstance().addCandidate(this);
  }
  
  public void addSkill(String skill) {
	skills.add(skill);  
  }
  
}
