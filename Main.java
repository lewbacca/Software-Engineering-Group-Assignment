import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Model model = new Model();
		Controller controller = new Controller(model);
		try {
			FileInputStream is = new FileInputStream("data.ser");
			ObjectInputStream ois = new ObjectInputStream(is);
			PTTDirector pttDirector=(PTTDirector) ois.readObject();
			Administrator admin=(Administrator) ois.readObject();
			ClassDirector classDirector1=(ClassDirector) ois.readObject();
			ClassDirector classDirector2=(ClassDirector) ois.readObject();
			ClassDirector classDirector3=(ClassDirector) ois.readObject();
			ClassDirector classDirector4=(ClassDirector) ois.readObject();
			CandidateEmployee employee1=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee2=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee3=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee4=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee5=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee6=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee7=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee8=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee9=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee10=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee11=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee12=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee13=(CandidateEmployee) ois.readObject();
			CandidateEmployee employee14=(CandidateEmployee) ois.readObject();
			System.out.println(classDirector1.getName());
			System.out.println(employee2.getSkills());
			
			TeachingRequirements.getInstance().addRequirements("java");
			TeachingRequirements.getInstance().addRequirements("lava");
			TeachingRequirements.getInstance().addRequirements("hubava");
			admin.requestDecision(employee1,TeachingRequirements.getInstance().getListOfRequirements().get(0));
			Administrator.getInstance().requestDecision(employee3,TeachingRequirements.getInstance().getListOfRequirements().get(1));
			Administrator.getInstance().requestDecision(employee4,TeachingRequirements.getInstance().getListOfRequirements().get(2));
			ois.close();
		}catch (IOException e) {
			e.printStackTrace();
		}

		Decision.getInstance();
		controller.userDeference();
		
	}

}
