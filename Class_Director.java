
public class Class_Director extends Staff{
	
	public Class_Director(String name, int ID) {
		title="Class Director";
		this.name=name;
		this.ID=ID;
	}
	
	public void addRequirement(String requirement) {
		Teaching_Requirements.getInstance().addRequirements(requirement);
	}
}
