import java.io.EOFException;
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
//	private ClassDirector classDirector1,classDirector2,classDirector3,classDirector4;
//	private TeachingRequirements teachingRequirements;
	private ArrayList<Staff> staff;
	private ArrayList<CandidateEmployee> employees;
	public Model() throws ClassNotFoundException{
		staff=new ArrayList<Staff>();
		classDirector=new ArrayList<ClassDirector>();
//		employees=new ArrayList<CandidateEmployee>();
		try {
			FileInputStream is=null;
			ObjectInputStream ois=null;
			is = new FileInputStream("data.ser");
			try {
			ois = new ObjectInputStream(is);
			}catch(IOException e) {
			}finally {
		
			if(ois!=null)
			{
			TeachingRequirements.read(ois);
			PTTDirector.read(ois);
			Administrator.read(ois);
			Decision.read(ois);
			
			classDirector=(ArrayList<ClassDirector>) ois.readObject();
			if(classDirector==null)
				classDirector=new ArrayList<ClassDirector>();
						if(classDirector==null)
						{classDirector=new ArrayList<ClassDirector>();}
						
			ois.close();
						
			}
			/*For test purposes
			System.out.println(TeachingRequirements.getInstance().getListOfRequirements());
			System.out.println(PTTDirector.getInstance().getTrying().get(0));
			System.out.println(Administrator.getInstance().getTrying().get(0));
			System.out.println(Administrator.getInstance().getCandidates().get(0).getName());
			*/
			}
		}catch (IOException e) {
			System.out.print("With a new data file\n");
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
	public void resetData() {
		
		try {
			FileOutputStream f = new FileOutputStream(new File("data.ser"));
			f.write("".getBytes());
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Reset Done");
		}finally {
			getStaff().removeAll(classDirector);
			classDirector.clear();
			Administrator.getInstance().getCandidates().clear();
		}
		
	}
	public void update() {
		try {
			FileOutputStream f = new FileOutputStream(new File("data.ser"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			//For test purposes
			//System.out.println(TeachingRequirements.getInstance().getListOfRequirements());
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
}