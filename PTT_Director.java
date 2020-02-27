
public class PTT_Director extends Staff {
	
	private static PTT_Director pttDirector = null;
	
	private PTT_Director(){
		name="George Clooney";
		ID=1;
		title="PTT Director";
	}
	
	public static PTT_Director getInstance() {
		if (pttDirector == null) {
			pttDirector = new PTT_Director();
		}
		return pttDirector;
	}
}
