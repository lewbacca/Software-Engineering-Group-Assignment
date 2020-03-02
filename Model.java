import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Model {
	private PTTDirector pttDirector;
	private ClassDirector classDirector1,classDirector2,classDirector3,classDirector4;
	private Administrator admin;
	private CandidateEmployee employee1,employee2,employee3,employee4,employee5,employee6,employee7,employee8,employee9,employee10,
	employee11,employee12,employee13,employee14;
	private TeachingRequirements teachingRequirements;
	private ArrayList<Staff> staff;
	private ArrayList<CandidateEmployee> employees;
	public Model() throws ClassNotFoundException{
		staff=new ArrayList<Staff>();
		employees=new ArrayList<CandidateEmployee>();
		try {
			FileInputStream is = new FileInputStream("data.txt");
			ObjectInputStream ois = new ObjectInputStream(is);
			teachingRequirements=(TeachingRequirements) ois.readObject();
			pttDirector=(PTTDirector) ois.readObject();
			admin=(Administrator) ois.readObject();
			classDirector1=(ClassDirector) ois.readObject();
			classDirector2=(ClassDirector) ois.readObject();
			classDirector3=(ClassDirector) ois.readObject();
			classDirector4=(ClassDirector) ois.readObject();
			employee1=(CandidateEmployee) ois.readObject();
			employee2=(CandidateEmployee) ois.readObject();
			employee3=(CandidateEmployee) ois.readObject();
			employee4=(CandidateEmployee) ois.readObject();
			employee5=(CandidateEmployee) ois.readObject();
			employee6=(CandidateEmployee) ois.readObject();
			employee7=(CandidateEmployee) ois.readObject();
			employee8=(CandidateEmployee) ois.readObject();
			employee9=(CandidateEmployee) ois.readObject();
			employee10=(CandidateEmployee) ois.readObject();
			employee11=(CandidateEmployee) ois.readObject();
			employee12=(CandidateEmployee) ois.readObject();
			employee13=(CandidateEmployee) ois.readObject();
			employee14=(CandidateEmployee) ois.readObject();
			System.out.println(teachingRequirements.getListOfRequirements());
			ois.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		addStaff();
		addEmployees();
	}
	public void addStaff() {
		staff.add(pttDirector);
		staff.add(admin);
		staff.add(classDirector1);	
		staff.add(classDirector2);
		staff.add(classDirector3);
		staff.add(classDirector4);
	}
	public void addEmployees() {
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
		employees.add(employee4);
		employees.add(employee5);
		employees.add(employee6);
		employees.add(employee7);
		employees.add(employee8);
		employees.add(employee9);
		employees.add(employee10);
		employees.add(employee11);
		employees.add(employee12);
		employees.add(employee13);
		employees.add(employee14);
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
	public void initialize() {
		
	}
	public void update() {
		try {
			FileOutputStream f = new FileOutputStream(new File("data.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			System.out.println(teachingRequirements.getListOfRequirements());
			o.writeObject(teachingRequirements);
			o.writeObject(pttDirector);
			o.writeObject(admin);
			o.writeObject(classDirector1);
			o.writeObject(classDirector2);
			o.writeObject(classDirector3);
			o.writeObject(classDirector4);
			o.writeObject(employee1);
			o.writeObject(employee2);
			o.writeObject(employee3);
			o.writeObject(employee4);
			o.writeObject(employee5);
			o.writeObject(employee6);
			o.writeObject(employee7);
			o.writeObject(employee8);
			o.writeObject(employee9);
			o.writeObject(employee10);
			o.writeObject(employee11);
			o.writeObject(employee12);
			o.writeObject(employee13);
			o.writeObject(employee14);
			o.flush();
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
	public CandidateEmployee getEmployee(int ID){
		for (CandidateEmployee a:employees) {
			if (a.getID()==ID) {
				return a;
			}
		}
		return null;
	}
	
	
}
