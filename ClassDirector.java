
public class ClassDirector extends Staff{

	public ClassDirector(String name, int ID, String password) {
		title="Class Director";
		this.name=name;
		this.ID=ID;
		this.password=password;
	}
	
	public void addRequirement(String requirement) {
		TeachingRequirements.getInstance().addRequirements(requirement);
	}

	
	
}
