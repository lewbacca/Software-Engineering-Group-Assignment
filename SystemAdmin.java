public class SystemAdmin extends Staff{
	private String name;
	private int ID;
	private String password;
	public SystemAdmin(String name, int ID, String password) {
		this.name="System Admin";
		this.ID=999;
		this.password="bigboss";
	}
	
	public void createStaff()
	{
		if(staffType==0)
		{
			CandidateEmployee employee1=new CandidateEmployee("Tom Hanks","Math");
		}
		if(staffType==1)
		{
			ClassDirector classDirector1=new ClassDirector("Matthew McConaughey",3,"iamclassdirector1");
		}
	}
	public void removeStaff()
	{

	}
}