import java.util.ArrayList;
import java.io.Serializable;
public class CandidateEmployee implements Serializable{
  /**
	 * this class is for candidates that can be paired with requirements 
	 */
		private static final long serialVersionUID = 1L;
		private ArrayList<String> skills=new ArrayList<String>(); //a list of skills for the candidate
		private static int totalIDs = 0; //a static variable which allows us to iterate and assign a unique ID to every candidate employee
		private int ID; //important for identifying and choosing the candidate throughout the program
		private boolean approved; //signifies whether this candidate has been approved to fill a requirement
		private String name;
		private String title; 
		private String training; //stores a comment set by the administrator regarding what training this candidate requires
		
public CandidateEmployee(String name, String title){ 
	  this.name=name;
	  this.ID=totalIDs+1;
	  this.title=title;
	  approved=false;
	  totalIDs++; //iterates the static variable 
	  training="";
	  Administrator.getInstance().addCandidate(this); //automatically added to the candidate list held by administrator
  }

//getters and setters

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

  public String getTraining() {
	  return training;
  }

  public void setTraining(String training) {
	  this.training = training;
  }
	
}
