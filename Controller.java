import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Controller {
	private Model model;
	private View view;
	private Staff user;
	public Controller(Model model) {
	this.model = model;
	view=new View(model);
	}
	public void login() {
		Scanner sc= new Scanner(System.in);
		boolean match=false;
		Staff user=null;
		while(!match) {
			view.loginName();
			int input = sc.nextInt();
			sc.nextLine();
			for(Staff a: model.getStaff()) {
				if(a.getID()==input) {
					match=true;
					user=a;
				}else {
					view.invalidCredentials();
				}
			}
		}
		match=false;
		while(!match) {
			view.loginPassword();
			String input = sc.nextLine();
			sc.nextLine();
			if(user.getPassword()== input) {
				match=true;
			}else {
				view.invalidCredentials();
			}
		}
		sc.close();
		
	}
	public void userDeference() {
		login();
		if(user instanceof PTTDirector) {
			pttDirectorControl();
		}else if(user instanceof ClassDirector) {
			classDirectorControl();
		}else if(user instanceof Administrator) {
			administratorControl();
		}
	}
	
	public void pttDirectorControl() {
		view.welcome(user);
		view.proposalsToBeApproved();
		
	}
	public void administratorControl() {
		view.welcome(user);
		view.initialChoiceAdministrator();
		boolean validChoice=false; 
		Scanner sc= new Scanner(System.in);
		while(!validChoice) {
			int input = sc.nextInt();
			sc.nextLine();
			if(input==0) {
				validChoice=true;
			}else if(input==1) {
				validChoice=true;
				createProposals();
			}else if(input==2) {
				validChoice=true;
				assignTraining();
			}
		}
		sc.close();
		
	}
	public void createProposals() {
		view.welcomeProposalsAdministrator();
		Scanner sc= new Scanner(System.in);
		view.showRequirements();
		view.showCandidateEmployees();
		boolean addedEnough=false;
		while(!addedEnough) {
			int input;
			boolean validChoice=false;
		    String requirement=null;
		    CandidateEmployee candidate=null;
		    view.chooseRequirement();
			    while(!validChoice) {
			    	input = sc.nextInt();
			    	sc.nextLine();
						if(input<TeachingRequirements.getInstance().getListOfRequirements().size() && input>-1) {
							validChoice=true;
							requirement=TeachingRequirements.getInstance().getListOfRequirements().get(input);
						}else {
							view.invalidChoice();
						}
				}
			    validChoice=false;
			    view.chooseCandidate();
			    while(!validChoice) {
			    	input=sc.nextInt();
			    	sc.nextLine();
			    	if(input<Administrator.getInstance().getCandidates().size() && input>-1) {
			    		validChoice=true;
			    		candidate = Administrator.getInstance().getCandidates().get(input); 
			    	}else {
			    		view.invalidChoice();
			    	}
			    }
			    Administrator.getInstance().requestDecision(candidate, requirement);
			    addedEnough=addOrOtherOrExit();   
		}
		sc.close();
	}
	public void assignTraining() {
		view.showCandidateTrainees();
		boolean addedEnough=false;
		Scanner sc = new Scanner(System.in);
		while(!addedEnough) {
			view.chooseCandidate();
			
			int input = sc.nextInt();
			boolean validChoice=false;
			sc.nextLine();
			while(!validChoice) {
				Administrator.getInstance().checkForTrainees();
				if(input<Administrator.getInstance().getTrainees().size() && input>-1) {
					validChoice=true;
					view.makeAComment();
					String comment=sc.nextLine();
					sc.nextLine();
					CandidateEmployee candidate=null;
					Set<Entry<CandidateEmployee, String>> entries = Administrator.getInstance().getTrainees().keySet();
					Entry<CandidateEmployee, String>[] arrayOfEntries = entries.toArray(arrayOfEntries);
					Administrator.getInstance().getTrainees().put(arrayOfEntries[input], comment);
					
				}else {
					view.invalidChoice();
				}
			}
		addedEnough=addOrOtherOrExit();
		}
		sc.close();
	}
	public boolean addOrOtherOrExit(){
		Scanner sc =new Scanner(System.in);
		view.addOrExitAdministrator();
		int input = sc.nextInt();
		sc.nextLine();
		boolean validChoice=false;
	    while(!validChoice) {
		    view.addOrExitAdministrator();
		    input=sc.nextInt();
		    sc.nextLine();
		    
		    if(input==0) {
		    	sc.close();
		    	return true;
		    }else if(input==1) {
		    	validChoice=true;
		    }else if(input==2) {
		    	sc.close();
		    	administratorControl();
		    }else {
		    	view.invalidChoice();
		    }
	    }
	    sc.close();
	    return false;
	}
	
	public void classDirectorControl() {
		
	}
}
