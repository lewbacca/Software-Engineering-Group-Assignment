
public class Model {
	private PTTDirector pttDirector;
	private ClassDirector classDirector1,classDirector2;
	private Administrator admin;
	public Model(){
		pttDirector=PTTDirector.getInstance();
		classDirector1=new ClassDirector("Robert DeNiro",3);
		classDirector2=new ClassDirector("Idris Elba",4);
		admin=Administrator.getInstance();
		
	}
}
