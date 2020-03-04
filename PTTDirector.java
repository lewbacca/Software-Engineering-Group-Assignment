import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;
public class PTTDirector extends Staff implements Serializable{
	/**
	 * a singleton class that allows the user to use PTT director functionality
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int ID;
	private String title;
	private String password;
	private static PTTDirector pttDirector = null;
	private PTTDirector(){
		name="George Clooney";
		ID=1;
		title="PTT Director";
		password="iampttdirector";
	}
	public static synchronized void read(ObjectInputStream in){

        try{

        	pttDirector = (PTTDirector)in.readObject();
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
	
	public static PTTDirector getInstance() {
		if (pttDirector == null) {
			pttDirector = new PTTDirector();
		}
		return pttDirector;
	}
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
	public String getTitle() {
		return title;
	}
	public String getPassword() {
		return password;
	}
}
