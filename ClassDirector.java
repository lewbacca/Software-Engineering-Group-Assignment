import java.io.Serializable;
public class ClassDirector extends Staff implements Serializable{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String name,title,password;
	protected int ID;
	public ClassDirector(String name, int ID, String password) {
		super();
		title="Class Director";
		this.name=name;
		this.ID=ID;
		this.password=password;
	}
	
	public void addRequirement(String requirement) {
		TeachingRequirements.getInstance().addRequirements(requirement);
	}
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
	public String getTitle() {
		return title;
	}
	public String getPassword() {
		return password;
	}

	
	
}
