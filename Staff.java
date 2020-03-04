/*
 * an overlaying class that is extended by all staff that have control roles in the program
 */
public class Staff{
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
}
