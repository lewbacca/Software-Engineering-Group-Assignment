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
	private ArrayList<Staff> staff;
	public Model() throws ClassNotFoundException{
		staff=new ArrayList<Staff>();
	
		try {
			FileInputStream is = new FileInputStream("data.ser");
			ObjectInputStream ois = new ObjectInputStream(is);
			
			TeachingRequirements.read(ois);
			PTTDirector.read(ois);
			Administrator.read(ois);
			Decision.read(ois);
			classDirector1=(ClassDirector) ois.readObject();
			classDirector2=(ClassDirector) ois.readObject();
			classDirector3=(ClassDirector) ois.readObject();
			classDirector4=(ClassDirector) ois.readObject();
			ois.close();
			is.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		addStaff();
	}
	public void addStaff() {
		staff.add(PTTDirector.getInstance());
		staff.add(Administrator.getInstance());
		staff.add(classDirector1);	
		staff.add(classDirector2);
		staff.add(classDirector3);
		staff.add(classDirector4);
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
			FileOutputStream f = new FileOutputStream(new File("data.ser"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			System.out.println(TeachingRequirements.getInstance().getListOfRequirements());
			o.writeObject(TeachingRequirements.getInstance());
			o.writeObject(PTTDirector.getInstance());
			o.writeObject(Administrator.getInstance());
			o.writeObject(Decision.getInstance());
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
