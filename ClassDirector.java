
public class ClassDirector extends Staff{
	
	public ClassDirector(String name, int ID) {
		title="Class Director";
		this.name=name;
		this.ID=ID;
	}
	
	public void addRequirement(String requirement) {
		TeachingRequirements.getInstance().addRequirements(requirement);
	}
}
