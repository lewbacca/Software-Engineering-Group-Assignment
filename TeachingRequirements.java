import java.util.ArrayList;

public class TeachingRequirements {
	private static TeachingRequirements teachingRequirements = null;
	private ArrayList<String> listOfRequirements = new ArrayList<String>();
	
	private TeachingRequirements() {}
	
	public static TeachingRequirements getInstance() {
		if(teachingRequirements == null) {
			teachingRequirements=new TeachingRequirements();
		}
		return teachingRequirements;
	}
	
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
	
}
