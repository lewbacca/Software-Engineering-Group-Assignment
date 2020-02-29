
public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
		CandidateEmployee bobi = new CandidateEmployee("bobi", "jerkoff");
		bobi.addSkill("wanking");
		bobi.addSkill("shanking");
		bobi.addSkill("spanking");
		CandidateEmployee mobi = new CandidateEmployee("mobi", "jerkoff");
		mobi.addSkill("banking");
		mobi.addSkill("ranking");
		mobi.addSkill("cranking");
		CandidateEmployee hobi = new CandidateEmployee("hobi","jerkoff");
		hobi.addSkill("spalunking");
		hobi.addSkill("cooking");
		hobi.addSkill("shitting");
		TeachingRequirements.getInstance().addRequirements("java");
		TeachingRequirements.getInstance().addRequirements("lava");
		TeachingRequirements.getInstance().addRequirements("hubava");
		Administrator.getInstance().requestDecision(bobi,TeachingRequirements.getInstance().getListOfRequirements().get(0));
		Administrator.getInstance().requestDecision(mobi,TeachingRequirements.getInstance().getListOfRequirements().get(1));
		Administrator.getInstance().requestDecision(hobi,TeachingRequirements.getInstance().getListOfRequirements().get(2));
		Decision.getInstance();
		controller.userDeference();		
	}

}
