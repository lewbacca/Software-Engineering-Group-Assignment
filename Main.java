import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Model model = new Model();
		Controller controller = new Controller(model);

//		initialize();	

		
		controller.userDeference();		
	}
	
	public static void initialize()
	{
		TeachingRequirements teachingRequirements=TeachingRequirements.getInstance();
		Decision decision=Decision.getInstance();		
		final PTTDirector pttDirector=PTTDirector.getInstance();
		final Administrator admin=Administrator.getInstance();
		////
//		admin.addTrying("Ali");
//		pttDirector.addTrying("Utku");
//		teachingRequirements.addRequirements("Trrryyy");
		////
		ClassDirector classDirector1=new ClassDirector("Matthew McConaughey",3,"iamclassdirector1");
		ClassDirector classDirector2=new ClassDirector("Morgan Freeman",4,"iamclassdirector2");
		ClassDirector classDirector3=new ClassDirector("Samuel Jackson",5,"iamclassdirector3");
		ClassDirector classDirector4=new ClassDirector("Halle Berry",6,"iamclassdirector4");
		CandidateEmployee employee1=new CandidateEmployee("Tom Hanks","Math");
		employee1.addSkill("Algebra");
		employee1.addSkill("Calculus");
		employee1.addSkill("Applied Math");
		CandidateEmployee employee2=new CandidateEmployee("Angelina Jolie","Physics");
		employee2.addSkill("Mechanics");
		employee2.addSkill("Aerodynamics");
		employee2.addSkill("Solid State Physics");
		CandidateEmployee employee3=new CandidateEmployee("Mila Kunis","Biology");
		employee3.addSkill("Molecular Biology");
		employee3.addSkill("Biochemistry");
		employee3.addSkill("Neuroscience");
		CandidateEmployee employee4=new CandidateEmployee("Michael Douglas","Chemistry");
		employee4.addSkill("Organic Chemistry");
		employee4.addSkill("Theoretical Chemistry");
		employee4.addSkill("Quantum Chemistry");
		CandidateEmployee employee5=new CandidateEmployee("Jack Nicholson","Computer Science");
		employee5.addSkill("Programming");
		employee5.addSkill("Machine Learning");
		employee5.addSkill("Artificial Intelligence");
		CandidateEmployee employee6=new CandidateEmployee("Denzel Washington","Computer Science");
		employee6.addSkill("Databases");
		employee6.addSkill("Cryptocurrency");
		employee6.addSkill("Computer Architecture");
		CandidateEmployee employee7=new CandidateEmployee("Leonadro DiCaprio","Philology");
		employee7.addSkill("Literature");
		employee7.addSkill("Latin");
		employee7.addSkill("Modern History");
		CandidateEmployee employee8=new CandidateEmployee("Megan Fox","Philosophy");
		employee8.addSkill("Ancient History");
		employee8.addSkill("Philosophy");
		employee8.addSkill("Theater");
		CandidateEmployee employee9=new CandidateEmployee("Johnny Depp","Arts");
		employee9.addSkill("Arts");
		employee9.addSkill("Architecture");
		employee9.addSkill("History of Arts");
		CandidateEmployee employee10=new CandidateEmployee("Anthony Hopkins","Physical Education");
		employee10.addSkill("Sports Coaching");
		employee10.addSkill("Basketball");
		employee10.addSkill("Martial Arts");
		CandidateEmployee employee11=new CandidateEmployee("Meryl Streep","Electronics");
		employee11.addSkill("Electromagnetism");
		employee11.addSkill("Electronic circuits");
		employee11.addSkill("Robotics");
		CandidateEmployee employee12=new CandidateEmployee("Julia Roberts","Physics");
		employee12.addSkill("Thermodynamics");
		employee12.addSkill("Computational Physics");
		employee12.addSkill("Biophysics");
		CandidateEmployee employee13=new CandidateEmployee("Harrison Ford","Math");
		employee13.addSkill("Theoretical Math");
		employee13.addSkill("Game Theory");
		employee13.addSkill("Data analysis");
		CandidateEmployee employee14=new CandidateEmployee("Nicole Kidman","Special Education");
		employee14.addSkill("Special Education");
		employee14.addSkill("Psychology");
		employee14.addSkill("Life Coach");
		try {
			FileOutputStream f = new FileOutputStream(new File("data.ser"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(teachingRequirements);
			o.writeObject(pttDirector);
			o.writeObject(admin);
			o.writeObject(decision);
			o.writeObject(classDirector1);
			o.writeObject(classDirector2);
			o.writeObject(classDirector3);
			o.writeObject(classDirector4);
			o.flush();
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}

}