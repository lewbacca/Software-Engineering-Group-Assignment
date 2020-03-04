import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

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
		} else if (user instanceof SystemAdmin) {
			sysAdminControl();
		}
		model.update();
		view.quitProgram();
		typeQuit=sc.nextLine();
		}
		
		
		sc.close();
	}
	
	public void sysAdminControl()
	{
		while(!wantsToLogout){
		view.listStaff();
		for (Staff a : model.getStaff()) {
				System.out.println(a.getID()+" "+a.getName()+" "+a.getTitle());
			}
		view.listEmployee();;
		for(CandidateEmployee c:Administrator.getInstance().getCandidates()) {
				System.out.println(c.getID()+" "+c.getName()+" "+c.getTitle());
		}
		view.welcomeSystemAdmin();
		int input = sc.nextInt();
		sc.nextLine();
		if(input==1) {
			view.nameAdd();
			String name=sc.nextLine();
			view.passAdd();
			String password=sc.nextLine();
			int ID;
			
			view.chooseType();
			int input2 = sc.nextInt();
			sc.nextLine();			
			if(input2==1)
				{
				ID=Administrator.getInstance().getCandidates().size();
				CandidateEmployee ce=new CandidateEmployee(name,password);
				}
			else if(input2==2)
				{
				ID=model.getStaff().size();
				ClassDirector st=new ClassDirector(name,ID,password);
				model.getCD().add(st);
				model.getStaff().add(st);
				}
			
		}else if(input==2) {
			view.chooseType();
			int input2 = sc.nextInt();
			sc.nextLine();
			if(input2==1)
				{	
			view.removeStaff();
			int input3 = sc.nextInt();
			sc.nextLine();
			Administrator.getInstance().getCandidates().remove(input3-1);
				}
			if(input2==2)
				{
			view.removeStaff();
			int input3 = sc.nextInt();
			sc.nextLine();
			model.getCD().remove(input3);
				}
			

		}else if(input==3) {
			model.resetData();
		}else {wantsToLogout=true;}
		
		}
	}

	public void pttDirectorControl() {
		Decision.getInstance().updateProposals();
		if(!Decision.getInstance().getProposals().isEmpty()) {
			view.welcome(user);
			boolean approvedEnough=false;
			while(!approvedEnough) {
				view.showProposals();
				Set<CandidateEmployee> candidates = Decision.getInstance().getProposals().keySet();
				ArrayList<Integer> ids = new ArrayList<Integer>();
				for(CandidateEmployee c: candidates) {
					ids.add(c.getID());
				}
				boolean validChoice=false;
				int index=0;
				int input;
				while(!validChoice) {
					view.chooseCandidate();
					index=sc.nextInt();
					sc.nextLine();
					if(ids.contains(index)) {
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
					if(input==0) {
						validChoice=true;
						for(CandidateEmployee c: candidates) {
							if(c.getID()==index) {
								Administrator.getInstance().addCandidate(c);
							}
						}
						Decision.getInstance().removeProposal(index);
					}else if(input==1) {
						validChoice=true;
						Decision.getInstance().approve(index);
						Decision.getInstance().updateApprovals();
					}else if(input==2) {
						validChoice=true;
					}else {
						view.invalidChoice();
					}
				}
				Decision.getInstance().updateProposals();
				if(Decision.getInstance().getProposals().isEmpty()) {
					view.youreDone();
					break;
				}
				validChoice=false;
				view.addOrExitPTTDirector();
				while(!validChoice) {
					input = sc.nextInt();
					sc.nextLine();
					if(input==0) {
						approvedEnough=true;
						validChoice=true;
					}else if(input==1) {
						validChoice=true;
					}else {
						view.invalidChoice();
					}
				}
			}
		}else {
			view.emptyList();
		}
	}

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
				}
			}
		}
	}

	public void createProposals() {
		view.welcomeProposalsAdministrator();
		if (!TeachingRequirements.getInstance().getListOfRequirements().isEmpty()
				&& !Administrator.getInstance().getCandidates().isEmpty()) {
			boolean addedEnough = false;
			while (!addedEnough) {
				view.showRequirements();
				view.showCandidateEmployees();
				int input;
				boolean validChoice = false;
				String requirement = null;
				CandidateEmployee candidate = null;
				ArrayList<Integer> ids=new ArrayList<Integer>();
				for(CandidateEmployee c:Administrator.getInstance().getCandidates()) {
					ids.add(c.getID());
				}
				view.chooseRequirement();
				while (!validChoice) {
					input = sc.nextInt();
					sc.nextLine();
					if (input < TeachingRequirements.getInstance().getListOfRequirements().size() && input > -1) {
						validChoice = true;
						requirement = TeachingRequirements.getInstance().getListOfRequirements().get(input);
					} else {
						view.invalidChoice();
					}
				}
				validChoice = false;
				while (!validChoice) {
					view.chooseCandidate();
					input = sc.nextInt();
					sc.nextLine();
					if (ids.contains(input)) {
						validChoice = true;
						for(CandidateEmployee c: Administrator.getInstance().getCandidates()) {
							if (c.getID()==input) {
								candidate=c;
							}
						}
						Administrator.getInstance().getCandidates().remove(candidate);
					} else {
						view.invalidChoice();
					}
				}
				Administrator.getInstance().requestDecision(candidate, requirement);
				addedEnough = addOrOtherOrExit();
			}
		} else {
			view.emptyList();
		}
	}

	public void assignTraining() {
		System.out.println(Decision.getInstance().getApprovals().toString());
		if (!Decision.getInstance().getApprovals().isEmpty()){
			
			ArrayList<Integer> ids = new ArrayList<Integer>();
			Set<CandidateEmployee> trainees=Decision.getInstance().getApprovals().keySet();
			for(CandidateEmployee c: trainees) {
				ids.add(c.getID());
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
								c.setTraining(comment);
								
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
	
	public void classDirectorControl() {
		view.welcome(user);
		view.welcomeClassDirector();
		boolean addedEnough=false;
		while(!addedEnough) {
			if (!TeachingRequirements.getInstance().getListOfRequirements().isEmpty()) {
				view.showRequirements();
			}
			view.addTeachingRequirements();
			String input=sc.nextLine();
			TeachingRequirements.getInstance().addRequirements(input);
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
