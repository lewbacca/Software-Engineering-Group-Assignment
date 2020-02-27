import java.util.ArrayList;

public class Model {
	private PTTDirector pttDirector;
	private ClassDirector classDirector1,classDirector2;
	private Administrator admin;
	private ArrayList<Staff> staff;
	public Model(){
		pttDirector=PTTDirector.getInstance();
		classDirector1=new ClassDirector("Robert DeNiro",3,"iamclassdirector1");
		classDirector2=new ClassDirector("Idris Elba",4,"iamclassdirector2");
		admin=Administrator.getInstance();
		staff.add(pttDirector);
		staff.add(admin);
		staff.add(classDirector1);	
		staff.add(classDirector2);
	}
	public PTTDirector getPttDirector() {
		return pttDirector;
	}
	public ClassDirector getClassDirector1() {
		return classDirector1;
	}
	public ClassDirector getClassDirector2() {
		return classDirector2;
	}
	public Administrator getAdmin() {
		return admin;
	}
	public ArrayList<Staff> getStaff() {
		return staff;
	}
	
	
}
