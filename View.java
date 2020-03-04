import java.util.Map.Entry;
import java.util.Set;

public class View {
	private Model model;
	public View(Model model) {
		this.model=model;
	}
	
	//login menu
	public void loginName() {
		System.out.println("============LOGIN============");
		System.out.print("ID: ");
	}
	public void loginPassword() {
		System.out.print("PASSWORD: ");
		
	}
	
	public void invalidCredentials() {
		System.out.println("INVALID CREDENTIALS! Try again");
	}
	
	public void quitProgram() {
		System.out.println("For login screen, press enter.\nTo quit program, type 'quit' and press enter.");
	}
	
	
	public void welcome(Staff user) {
		System.out.println("\n===========WELCOME===========");
		System.out.println("Welcome "+user.getName());
		
	}
	
	//System Admin Menu
	public void welcomeSystemAdmin() {
		System.out.println("0.Logout, 1.Add Staff, 2.Remove Staff");
	}
	
	public void listStaff() {
		System.out.println("Staff ID Staff Name Staff Title");
	}
	
	public void listEmployee() {
		System.out.println("Emp ID Emp Name Emp Title");
	}
	
	public void nameAdd() {
		System.out.print("Please enter name: ");
	}
	
	public void passAdd() {
		System.out.print("Please enter password: ");
	}
	
	
	public void chooseType() {
		System.out.println("0.Initial Menu, 1.Canditate Employee, 2.Class Director");
	}
	
	
	
	public void removeStaff() {
		System.out.print("Please enter ID to be removed: ");
	}
	
	
	
	
	//Class Director Menu
	public void welcomeClassDirector() {
		System.out.println("Here you can add Teaching Requirements");
	}
	public void addTeachingRequirements() {
		System.out.print("Teaching Requirement: ");
	}
	public void addOrExitClassDirector() {
		System.out.print("0.Logout, 1.Add another Requirement: ");
	}
	public void invalidChoice() {
		System.out.println("INVALID CHOICE! Make a suiteble choice");
	}
	
	//Administrator menu
	public void initialChoiceAdministrator() {
		System.out.println("0. Logout\n1. Create Proposals\n2. Assign Training");
	}
	public void welcomeProposalsAdministrator() {
		System.out.println("Here you can create Proposals for approval\n");
		}
	public void welcomeTrainingAdministrator() {
		System.out.println("Here you can assign the Training for candidates\n");
	}
	public void showRequirements() {
		System.out.println("These are the pending Teaching Requirements:");
			int i=0;
			for (String requirement:TeachingRequirements.getInstance().getListOfRequirements()) {
				System.out.println(i+": "+requirement);
				i++;
			}
	}
	public void showCandidateEmployees() {
		System.out.println("These are the Candidate Employees: ");
		int i=0;
		StringBuilder skills=new StringBuilder();
		
		System.out.println("ID\tName\tSkills");
		for (CandidateEmployee candidate:Administrator.getInstance().getCandidates()) {
			for (String skill:candidate.getSkills()) {
				skills.append(skill+" ");
			}
			System.out.println(candidate.getID()+":"+"\t"+candidate.getName()+"\t"+skills.toString());
			skills.setLength(0);
			
		}
	}
	public void chooseRequirement() {
		System.out.print("Requirement: ");
	}
	
	public void chooseCandidate() {
		System.out.print("Candidate ID: ");
	}
	public void addOrExitAdministrator() {
		System.out.print("0.Logout, 1.Add another, 2.Initial Menu: ");
	}
	
	public void showCandidateTrainees() {
		System.out.println("These Candidates have been approved by PTT Director");
		
		Set<Entry<CandidateEmployee,String>> traineesKey=Decision.getInstance().getApprovals().entrySet();
		StringBuilder skills=new StringBuilder();
		System.out.println("ID\tName\tApproved For\tSkills");
		for (Entry<CandidateEmployee,String> trainee:traineesKey){
			CandidateEmployee candidate=trainee.getKey();
			for (String skill:candidate.getSkills()) {
				skills.append(skill+" ");
			}
			System.out.println(candidate.getID()+"\t"+candidate.getName()+"\t"+trainee.getValue()+"\t"+skills.toString());
		}
	}
	public void makeComment() {
		System.out.print("Training requests for this Candidate: ");
	}
	//PTT Director Menu
	public void welcomePTTDirector() {
		System.out.println("Here you can approve Teaching Requests");
	}
	public void showProposals() {
	
		System.out.println("ID\t Name\t Teaching Request\t");
		for (Entry<CandidateEmployee, String> proposal:Decision.getInstance().getProposals().entrySet()) {
			CandidateEmployee candidate=proposal.getKey();
			System.out.println(candidate.getID()+"\t"+candidate.getName()+"\t"+proposal.getValue());	
		}
	}
	public void addOrExitPTTDirector() {
		System.out.print("0.Logout, 1.Handle another Request: ");
	}
	
	public void approvedOrNot() {
		System.out.print("0.Reject, 1.Approve, 2.Decide later: ");
	}
	public void emptyList() {
		System.out.println("\nThis list is empty. Redirecting:");
	}
	
	public void youreDone() {
		System.out.println("Your tasks are complete. Turning off program.");
	}
	
}
