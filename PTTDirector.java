import java.io.Serializable;
public class PTTDirector extends Staff implements Serializable{
	/**
	 * 
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
	 public Object readResolve() {
	       return getInstance( );
	    }
	
	
}
