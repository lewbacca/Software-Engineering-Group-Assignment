
public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
		CandidateEmployee bobi = new CandidateEmployee("bobi", 33,"jerkoff");
		bobi.addSkill("wanking");
		bobi.addSkill("shanking");
		bobi.addSkill("spanking");
		CandidateEmployee mobi = new CandidateEmployee("mobi", 34,"jerkoff");
		mobi.addSkill("banking");
		mobi.addSkill("ranking");
		mobi.addSkill("cranking");
		CandidateEmployee hobi = new CandidateEmployee("hobi", 37,"jerkoff");
		hobi.addSkill("spalunking");
		hobi.addSkill("cooking");
		hobi.addSkill("shitting");
		TeachingRequirements.getInstance().addRequirements("java");
		TeachingRequirements.getInstance().addRequirements("lava");
		TeachingRequirements.getInstance().addRequirements("hubava");
		controller.userDeference();
		
	}

}
