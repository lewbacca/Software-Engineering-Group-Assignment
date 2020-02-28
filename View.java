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
	
	
	public void welcome(Staff user) {
		System.out.println("\n===========WELCOME===========");
		System.out.println("Welcome "+user.getName());
	}
	
	//Class Director Menu
	public void welcomeClassDirector() {
		System.out.println("Here you can add Teaching Requirements");
	}
	public void addTeachingRequirements() {
		System.out.print("Teaching Requirement: ");
	}
	public void addOrExitClassDirector() {
		System.out.print("0.EXIT, 1.Add another Requirement: ");
	}
	public void invalidChoice() {
		System.out.println("INVALID CHOICE! Make a suiteble choice");
	}
	
	//Administrator menu
	public void initialChoiceAdministrator() {
		System.out.println("0. EXIT\n1. Create Proposals\n2. Assign Training");
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
		
		System.out.println("No.\t ID\t Name\t Skills");
		for (CandidateEmployee candidate:model.getAdmin().getCandidates()) {
			for (String skill:candidate.getSkills()) {
				skills.append(skill+" ");
			}
			System.out.println(i+":\t "+candidate.getID()+"\t"+candidate.getName()+"\t"+skills.toString());
			skills.setLength(0);
			i++;
		}
	}
	public void chooseRequirement() {
		System.out.print("Requirement: ");
	}
	
	public void chooseCandidate() {
		System.out.print("Candidate ID: ");
	}
	public void addOrExitAdministrator() {
		System.out.print("0.EXIT, 1.Add another, 2.Initial Menu: ");
	}
	
	public void showCandidateTrainees() {
		System.out.println("These Candidates have been approved by PTT Director");
		int i=0;
		Set<Entry<CandidateEmployee,String>> traineesKey=Administrator.getInstance().getTrainees().keySet();
		StringBuilder skills=new StringBuilder();
		System.out.println("No.\t ID\t Name\t Approved For\t Skills");
		for (Entry<CandidateEmployee,String> trainee:traineesKey){
			CandidateEmployee candidate=trainee.getKey();
			for (String skill:candidate.getSkills()) {
				skills.append(skill+" ");
			}
			System.out.println(i+"\t "+candidate.getID()+"\t"+candidate.getName()+"\t"+trainee.getValue()+"\t"+skills.toString());
			i++;
		}
	}
	public void makeComment() {
		System.out.print("Training requests for this Candidate: ");
	}
	//PTT Director Menu
	public void welcomePTTDirector() {
		System.out.println("Here you can approve Teaching Requests");
	}
	public void showApprovals() {
		int i=0;
		System.out.println("No.\t ID\t Name\t Teaching Request\t");
		for (Entry<CandidateEmployee, String> approval:Decision.getInstance().getApprovals().keySet()) {
			CandidateEmployee candidate=approval.getKey();
			System.out.println(i+"\t"+candidate.getID()+"\t"+candidate.getName()+"\t"+candidate.getID()+"\t"+approval.getValue());
			i++;
		}
	}
	public void addOrExitPTTDirector() {
		System.out.println("0.EXIT, 1.Handle another Request: ");
	}
	
	public void emptyList() {
		System.out.println("\nThis list is empty. Redirecting:");
	}
	
}
