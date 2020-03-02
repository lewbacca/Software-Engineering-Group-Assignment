import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Model model = new Model();
		Controller controller = new Controller(model);

			
			TeachingRequirements.getInstance().addRequirements("java");
			TeachingRequirements.getInstance().addRequirements("lava");
			TeachingRequirements.getInstance().addRequirements("hubava");
//			Administrator.getInstance().requestDecision(model.getEmployee(1),TeachingRequirements.getInstance().getListOfRequirements().get(0));
//			Administrator.getInstance().requestDecision(model.getEmployee(3),TeachingRequirements.getInstance().getListOfRequirements().get(1));
//			Administrator.getInstance().requestDecision(model.getEmployee(4),TeachingRequirements.getInstance().getListOfRequirements().get(2));
		Decision.getInstance();
		controller.userDeference();		
	}

}
