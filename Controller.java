import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
/*
 * this is the controller part of the MVC architecture we have implemented
 */
public class Controller {
	private Model model;
	private View view;
	private Staff user;
	private Scanner sc;
	private boolean wantsToLogout;
	private String typeQuit;
	public Controller(Model model) throws ClassNotFoundException {
		this.model = model;
		view = new View(model);
		sc = new Scanner(System.in);
		wantsToLogout=false;
		typeQuit="";
		
	}
/**
 * allows a user to log in
 * @return the Staff object that corresponds to the log in details
 */
	public Staff login() {
		boolean idMatch = false;
		boolean passMatch = false;
		Staff user = null;
		while (!idMatch || !passMatch) {
			view.loginName();
			int input = sc.nextInt();
			sc.nextLine();
			view.loginPassword();
			String password = sc.nextLine();
			for (Staff a : model.getStaff()) {
				if (a.getID() == input) {
					idMatch = true;
					user = a;
				}
			}
			if(user!=null) {
				if (user.getPassword().equals(password)) {
					passMatch = true;
				}
			}
			if (idMatch && passMatch) {
				break;
			} else {
				view.invalidCredentials();
			}
		}
		return user;
	}
/**
 * defers control to the appropriate method based on who the user logged in is
 */
	public void userDeference() {
		while(!typeQuit.equals("quit")) {
			user = login();
			wantsToLogout=false;
			if (user instanceof PTTDirector) {
				pttDirectorControl();
			} else if (user instanceof ClassDirector) {
				classDirectorControl();
			} else if (user instanceof Administrator) {
				administratorControl();
	//		} else if (user instanceof SystemAdmin) {
	//			sysAdminControl();
	//		}
			}
			view.quitProgram();
			typeQuit=sc.nextLine();
		}
		
		model.update();
		sc.close();
	}
	
//	public void sysAdminControl()
//	{
//		while(!wantsToLogout){
//		view.listStaff();
//		for (Staff a : model.getStaff()) {
//				System.out.println(a.getID()+" "+a.getName()+" "+a.getTitle());
//			}
//		view.listEmployee();;
//		for(CandidateEmployee c:Administrator.getInstance().getCandidates()) {
//				System.out.println(c.getID()+" "+c.getName()+" "+c.getTitle());
//		}
//		view.welcomeSystemAdmin();
//		int input = sc.nextInt();
//		sc.nextLine();
//		if(input==1) {
//			view.nameAdd();
//			String name=sc.nextLine();
//			view.passAdd();
//			String password=sc.nextLine();
//			int ID;
//			
//			view.chooseType();
//			int input2 = sc.nextInt();
//			sc.nextLine();			
//			if(input2==1)
//				{
//				ID=Administrator.getInstance().getCandidates().size();
//				CandidateEmployee ce=new CandidateEmployee(name,password);
//				Administrator.getInstance().getCandidates().add(ce);
//				}
//			else if(input2==2)
//				{
//				ID=model.getStaff().size();
//				ClassDirector st=new ClassDirector(name,ID,password);
//				model.getCD().add(st);
//				}
//			
//		}else if(input==2) {
//			view.chooseType();
//			int input2 = sc.nextInt();
//			sc.nextLine();
//			if(input2==1)
//				{	
//			view.removeStaff();
//			int input3 = sc.nextInt();
//			sc.nextLine();
//			Administrator.getInstance().getCandidates().remove(input3-1);
//				}
//			if(input2==2)
//				{
//			view.removeStaff();
//			int input3 = sc.nextInt();
//			sc.nextLine();
//			model.getCD().remove(input3);
//				}
//			
//
//		}else {wantsToLogout=true;}
//		
//		}
//	}
/**
 * this method controls the use of PTT director functionality, which allows you to see a candidate proposed by the administrator and approve, reject or decide later on this request
 */
	public void pttDirectorControl() {
		Decision.getInstance().updateProposals(); //removes approved requests from the proposals list and 
		if(!Decision.getInstance().getProposals().isEmpty()) {
			view.welcome(user);
			boolean approvedEnough=false;
			while(!approvedEnough) { //this loop allows us to keep approving requests
				view.showProposals();
				Set<CandidateEmployee> candidates = Decision.getInstance().getProposals().keySet();
				ArrayList<Integer> ids = new ArrayList<Integer>(); //IDs are shown in the view and used to select candidates
				for(CandidateEmployee c: candidates) {
					ids.add(c.getID()); //we get the ID for every key in the proposals hash map
				}
				boolean validChoice=false;
				int index=0; //user's choice from the list presented to the
				int input; //user's decision about the candidate
				while(!validChoice) { //this loop makes sure an existing candidate is chosen
					view.chooseCandidate(); 
					index=sc.nextInt();
					sc.nextLine();
					if(ids.contains(index)) { //appropriate choice - loops is exited 
						validChoice=true;	
					}else {
						view.invalidChoice();
					}
				}
				validChoice=false; 
				while(!validChoice) {
					view.approvedOrNot();
					input = sc.nextInt();
					sc.nextLine();
					if(input==0) { //the candidate is rejected 
						validChoice=true; 
						for(CandidateEmployee c: candidates) {
							if(c.getID()==index) {
								Administrator.getInstance().addCandidate(c); //they are added back to the the pool of candidates the administrator uses to make proposals
							}
						}
						Decision.getInstance().removeProposal(index); //the proposal is removed
					}else if(input==1) { //the candidate has been approved
						validChoice=true;
						Decision.getInstance().approve(index); //sets the candidates boolean approved to true
						Decision.getInstance().updateApprovals(); //takes all candidates are approved and the corresponding requirement and puts them in the approvals hashmap
					}else if(input==2) { //this corresponds to "Decide later", so nothing is changed
						validChoice=true;
					}else {
						view.invalidChoice();
					}
				}
				Decision.getInstance().updateProposals();
				if(Decision.getInstance().getProposals().isEmpty()) { //if the last proposal was processed, the program exits
					view.youreDone();
					break;
				}
				validChoice=false;
				view.addOrExitPTTDirector(); //exit or keep working menu
				while(!validChoice) {
					input = sc.nextInt();
					sc.nextLine();
					if(input==0) { //allows the user to exit
						approvedEnough=true;
						validChoice=true;
					}else if(input==1) { //allows the user to keep reviewing proposals
						validChoice=true;
					}else {
						view.invalidChoice();
					}
				}
			}
		}else {
			view.emptyList(); //in case there are no proposals
		}
	}
/**
 * over-arching method for administrator control which directs the user to a specific function of the administrator 
 */
	public void administratorControl() {
		view.welcome(user);
		while(!wantsToLogout){
			view.initialChoiceAdministrator();
			boolean validChoice = false;
			while (!validChoice) {
				int input = sc.nextInt();
				sc.nextLine();
				if (input == 0) {
					validChoice = true;
					wantsToLogout=true;
				} else if (input == 1) {
					validChoice = true;
					createProposals();
				} else if (input == 2) {
					validChoice = true;
					assignTraining();
				}else {
					view.invalidChoice();
				}
			}
		}
	}
/**
 * this method allows the user to create proposals based on a choice of candidate employee and a requirement
 */
	public void createProposals() {
		view.welcomeProposalsAdministrator();
		if (!TeachingRequirements.getInstance().getListOfRequirements().isEmpty()
				&& !Administrator.getInstance().getCandidates().isEmpty()) { //we need both requirements and candidates to be able to make proposals
			boolean addedEnough = false;
			while (!addedEnough) { //allows for multiple proposals to be created
				view.showRequirements();
				view.showCandidateEmployees();
				int input; //choice of candidate
				boolean validChoice = false;
				String requirement = null; //choice of requirement
				CandidateEmployee candidate = null;
				ArrayList<Integer> ids=new ArrayList<Integer>(); //a list of the candidates IDs added in the for loop below 
				for(CandidateEmployee c:Administrator.getInstance().getCandidates()) {
					ids.add(c.getID());
				}
				view.chooseRequirement();
				while (!validChoice) { //checks for an appropriate choice of requirement
					input = sc.nextInt();
					sc.nextLine();
					if (input < TeachingRequirements.getInstance().getListOfRequirements().size() && input > -1) {
						validChoice = true;
						requirement = TeachingRequirements.getInstance().getListOfRequirements().get(input); //saves the value of the choice
					} else {
						view.invalidChoice();
					}
				}
				validChoice = false;
				while (!validChoice) { //checks for an appropriate choice of candidate
					view.chooseCandidate();
					input = sc.nextInt();
					sc.nextLine();
					if (ids.contains(input)) { 
						validChoice = true;
						for(CandidateEmployee c: Administrator.getInstance().getCandidates()) {
							if (c.getID()==input) {
								candidate=c; //we get the candidate object by searching for their ID in the list of candidates, based on the user's input 
							}
						}
						Administrator.getInstance().getCandidates().remove(candidate); //assures they cannot be chosen in a proposal with another requirement (if this proposal is rejected, they will be added back in) 
					} else {
						view.invalidChoice(); 
					}
				}
				Administrator.getInstance().requestDecision(candidate, requirement); //this effectively creates an entry in the proposals list for this candidate and this requirement
				addedEnough = addOrOtherOrExit(); 
			}
		} else {
			view.emptyList();
		}
	}
/**
 * this method allows the user to assign training in the form of a comment to approved proposals, the comment is stored with the candidate
 */
	public void assignTraining() {
		if (!Decision.getInstance().getApprovals().isEmpty()){
			
			ArrayList<Integer> ids = new ArrayList<Integer>(); 
			Set<CandidateEmployee> trainees=Decision.getInstance().getApprovals().keySet();
			for(CandidateEmployee c: trainees) {
				ids.add(c.getID()); //storing candidates IDs in a list
			}
			boolean addedEnough = false;
			while (!addedEnough) {
				view.showCandidateTrainees();
				view.chooseCandidate();
				int input = sc.nextInt();
				sc.nextLine();
				
				boolean validChoice = false;
				while (!validChoice) {
					if (ids.contains(input)) {
						validChoice = true;
						view.makeComment();
						String comment = sc.nextLine();
						for(CandidateEmployee c: trainees) {
							if(c.getID()==input) {
								c.setTraining(comment); //if the input matches the ID we set the comment for the CandidateEmployee
								
							}
						}
						
					} else {
						view.invalidChoice();
					}
				}
				addedEnough = addOrOtherOrExit();
			}
		}else{
			view.emptyList();	
		}
	}
/**
 * a method providing add more or logout functionality
 * @return boolean value - has the user added enough
 */
	public boolean addOrOtherOrExit() {
		boolean validChoice = false;
		while (!validChoice) {
			view.addOrExitAdministrator();
			int input = sc.nextInt();
			sc.nextLine();
			if (input == 0) {
				wantsToLogout=true;
				return true;
			} else if (input == 1) {
				validChoice = true;
			} else if (input == 2) {
				wantsToLogout=false;
				return true;
			} else {
				view.invalidChoice();
			}
		}
		return false;
	}
/**
 * a method allowing for the class director to see previously added requirements and add new ones	
 */
	public void classDirectorControl() {
		view.welcome(user);
		view.welcomeClassDirector();
		boolean addedEnough=false;
		while(!addedEnough) {
			if (!TeachingRequirements.getInstance().getListOfRequirements().isEmpty()) { //if there are requirements already, show them
				view.showRequirements();
			}
			view.addTeachingRequirements();
			String input=sc.nextLine();
			TeachingRequirements.getInstance().addRequirements(input); //effectively, adds this string to the listOfRequirements in TeachingRequirements  
			boolean validChoice= false;
			while(!validChoice) {
				view.addOrExitClassDirector();
				int choice=sc.nextInt();
				sc.nextLine();
				if(choice==0) {
					validChoice=true;
					addedEnough=true;
				}else if(choice==1) {
					validChoice=true;
				}else {
					view.invalidChoice();
				}
			}
		}
	}	
}
