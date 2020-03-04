import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;

public class TeachingRequirements implements Serializable {
	/**
	 * a singleton class that holds a list of teaching requirements (Strings), that are added by the class director and used by the administrator
	 */
	private static final long serialVersionUID = 1L;
	private static TeachingRequirements teachingRequirements = null;
	private ArrayList<String> listOfRequirements;
	
	private TeachingRequirements() {
		listOfRequirements = new ArrayList<String>();
	}
/*
 * needed for the serialisation of singletons
 */
	public static synchronized void read(ObjectInputStream in){

        try{
        	teachingRequirements = (TeachingRequirements)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
	
	public static TeachingRequirements getInstance() {
		if(teachingRequirements == null) {
			teachingRequirements=new TeachingRequirements();
		}
		return teachingRequirements;
	}
	
	public void addRequirements(String requirement) {
		listOfRequirements.add(requirement);
	}
	
	public void removeRequirement(String requirement) {
		for(String a: listOfRequirements) {
			if (a==requirement) {
				listOfRequirements.remove(a);
			}
		}
	}

	public ArrayList<String> getListOfRequirements() {
		return listOfRequirements;
	}
	
}
