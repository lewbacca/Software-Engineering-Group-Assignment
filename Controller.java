import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Controller {
	private Model model;
	private View view;
	private Staff user;
	private Scanner sc;
	private TeachingRequirements teachingRequirements;
	private boolean wantsToExit;

	public Controller(Model model) throws ClassNotFoundException {
		this.model = model;
		view = new View(model);
		sc = new Scanner(System.in);
		wantsToExit=false;
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
			if (user.getPassword().equals(password)) {
				passMatch = true;
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
		user = login();
		if (user instanceof PTTDirector) {
			pttDirectorControl();
		} else if (user instanceof ClassDirector) {
			classDirectorControl();
		} else if (user instanceof Administrator) {
			administratorControl();
		}
		model.update();
		System.out.println("Scanner closed.");
		sc.close();
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
		while(!wantsToExit){
			view.initialChoiceAdministrator();
			boolean validChoice = false;
	
			while (!validChoice) {
				int input = sc.nextInt();
				sc.nextLine();
				if (input == 0) {
					validChoice = true;
					wantsToExit=true;
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
			view.showRequirements();
			view.showCandidateEmployees();
			boolean addedEnough = false;
			while (!addedEnough) {
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
			view.showCandidateTrainees();
			ArrayList<Integer> ids = new ArrayList<Integer>();
			Set<CandidateEmployee> trainees=Decision.getInstance().getApprovals().keySet();
			for(CandidateEmployee c: trainees) {
				ids.add(c.getID());
			}
			boolean addedEnough = false;
			while (!addedEnough) {
				view.chooseCandidate();
				int input = sc.nextInt();
				sc.nextLine();
				
				boolean validChoice = false;
				while (!validChoice) {
					if (ids.contains(input)) {
						validChoice = true;
						view.makeComment();
						String comment = sc.nextLine();
						sc.nextLine();
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
				wantsToExit=true;
				return true;
			} else if (input == 1) {
				validChoice = true;
			} else if (input == 2) {
				wantsToExit=false;
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
