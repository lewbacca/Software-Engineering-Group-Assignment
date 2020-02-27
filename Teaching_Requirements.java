import java.util.ArrayList;

public class Teaching_Requirements {
	private static Teaching_Requirements teachingRequirements = null;
	private ArrayList<String> listOfRequirements = new ArrayList<String>();
	
	private Teaching_Requirements() {}
	
	public static Teaching_Requirements getInstance() {
		if(teachingRequirements == null) {
			teachingRequirements=new Teaching_Requirements();
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
