import java.io.Serializable;
import java.util.ArrayList;

public class TeachingRequirements implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static TeachingRequirements teachingRequirements = null;
	private ArrayList<String> listOfRequirements;
	
	private TeachingRequirements() {
		listOfRequirements = new ArrayList<String>();
	}
	
//	public static TeachingRequirements getInstance() {
//		if(teachingRequirements == null) {
//			teachingRequirements=new TeachingRequirements();
//		}
//		return teachingRequirements;
//	}
	
	public void addRequirements(String requirement) {
		listOfRequirements.add(requirement);
	}
	
	public void removeRequirement(String requirement) {
		for(String a: listOfRequirements) {
			if (a==requirement) {
				listOfRequirements.remove(a);
			}
		}
	}

	public ArrayList<String> getListOfRequirements() {
		return listOfRequirements;
	}
//	 public Object readResolve() {
//	       return getInstance();
//	}
}
