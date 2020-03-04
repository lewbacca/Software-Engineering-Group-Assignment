import java.io.Serializable;

public class Staff/* implements Serializable*/{
	//private static final long serialVersionUID = -6642660772967821177L;
	private static Staff staff = null;
	protected String name;
	protected int ID;
	protected String password;
	protected String title;
	 public int getID() {
		  return ID;
	  }
	  public String getName() {
		  return name;
	  }
	  public String getPassword() {
		  return password;
	  }
	  public String getTitle() {
		  return title;
	  }
	/*  
	  protected Object readResolve() {
		  return getInstance();
	}
	  public static Staff getInstance() {
			if (staff == null) {
				staff = new Staff();
			}
			return staff;
	  }
	*/
	  
	  
}
