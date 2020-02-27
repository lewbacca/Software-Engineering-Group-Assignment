import java.util.Scanner;

public class Controller {
	private Model model;
	private View view;
	public Controller(Model model) {
	this.model = model;
	view=new View(model);
	}
	public void login(String name, String password) {
		Scanner sc= new Scanner(System.in);
		boolean match=false;
		while(!match) {
			view.loginName();
			int input = sc.nextInt();
			for(Staff a: model.getStaff()) {
				if(a.getID()==input) {
					match=true;
				}
			}
		}
	}
	
}
