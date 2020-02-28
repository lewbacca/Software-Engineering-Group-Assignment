import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Controller {
	private Model model;
	private View view;
	private Staff user;
	private Scanner sc;
	private boolean wantsToExit;

	public Controller(Model model) {
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
		System.out.println("Scanner closed.");
		sc.close();
	}

	public void pttDirectorControl() {
		view.welcome(user);
		view.showApprovals();

		boolean validChoice = false;
		System.out.println("You made it.");
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
				view.chooseCandidate();
				while (!validChoice) {
					input = sc.nextInt();
					sc.nextLine();
					if (input < Administrator.getInstance().getCandidates().size() && input > -1) {
						validChoice = true;
						candidate = Administrator.getInstance().getCandidates().get(input);
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
		Administrator.getInstance().checkForTrainees();

		if (!Administrator.getInstance().getTrainees().isEmpty()) {
			view.showCandidateTrainees();

			boolean addedEnough = false;
			while (!addedEnough) {
				view.chooseCandidate();

				int input = sc.nextInt();
				boolean validChoice = false;
				sc.nextLine();
				Entry<CandidateEmployee, String>[] arrayOfEntries = null;
				while (!validChoice) {

					if (input < Administrator.getInstance().getTrainees().size() && input > -1) {
						validChoice = true;
						view.makeComment();
						String comment = sc.nextLine();
						sc.nextLine();
						CandidateEmployee candidate = null;
						Set<Entry<CandidateEmployee, String>> entries = Administrator.getInstance().getTrainees().keySet();
						arrayOfEntries = entries.toArray(arrayOfEntries);
						Administrator.getInstance().getTrainees().put(arrayOfEntries[input], comment);

					} else {
						view.invalidChoice();
					}
				}
				addedEnough = addOrOtherOrExit();
			}
		} else {
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
		System.out.println("You made it.");
	}

}
