
public class PTTDirector extends Staff {
	
	private static PTTDirector pttDirector = null;
	private String password;
	private PTTDirector(){
		name="George Clooney";
		ID=1;
		title="PTT Director";
		password="iampttdirector";
	}
	
	public static PTTDirector getInstance() {
		if (pttDirector == null) {
			pttDirector = new PTTDirector();
		}
		return pttDirector;
	}
}
