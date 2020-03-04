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
	private ArrayList<ClassDirector> classDirector;
	private Administrator admin;
	private CandidateEmployee employee1,employee2,employee3,employee4,employee5,employee6,employee7,employee8,employee9,employee10,
	employee11,employee12,employee13,employee14;
	private TeachingRequirements teachingRequirements;
	private ArrayList<Staff> staff;
	private ArrayList<CandidateEmployee> employees;
	public Model() throws ClassNotFoundException{
		staff=new ArrayList<Staff>();
		classDirector=new ArrayList<ClassDirector>();
		employees=new ArrayList<CandidateEmployee>();
		try {
			FileInputStream is = new FileInputStream("data.ser");
			ObjectInputStream ois = new ObjectInputStream(is);
			
			TeachingRequirements.read(ois);
			PTTDirector.read(ois);
			Administrator.read(ois);
			Decision.read(ois);
			classDirector=(ArrayList<ClassDirector>) ois.readObject();
			if(classDirector==null)
			{classDirector=new ArrayList<ClassDirector>();}
			
			/*employee1=(CandidateEmployee) ois.readObject();
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
			employee14=(CandidateEmployee) ois.readObject();*/
			/*For test purposes
			System.out.println(TeachingRequirements.getInstance().getListOfRequirements());
			System.out.println(PTTDirector.getInstance().getTrying().get(0));
			System.out.println(Administrator.getInstance().getTrying().get(0));
			System.out.println(Administrator.getInstance().getCandidates().get(0).getName());
			*/
			ois.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		initializeStaff();
		//addEmployees();
	}
	public void initializeStaff() {
		staff.add(SystemAdmin.getInstance());
		staff.add(PTTDirector.getInstance());
		staff.add(Administrator.getInstance());
		staff.addAll(classDirector);	

	}

	public PTTDirector getPttDirector() {
		return pttDirector;
	}
	public Administrator getAdmin() {
		return admin;
	}
	public ArrayList<Staff> getStaff() {
		return staff;
	}
	public ArrayList<ClassDirector> getCD() {
		return classDirector; 
	}
	public void initialize() {
		
	}
	public void update() {
		try {
			FileOutputStream f = new FileOutputStream(new File("data.ser"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			System.out.println(TeachingRequirements.getInstance().getListOfRequirements());
			o.writeObject(TeachingRequirements.getInstance());
			o.writeObject(PTTDirector.getInstance());
			o.writeObject(Administrator.getInstance());
			o.writeObject(Decision.getInstance());
			o.writeObject(classDirector);
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
