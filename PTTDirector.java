import java.io.Serializable;
public class PTTDirector extends Staff implements Serializable{
	
	private static PTTDirector pttDirector = null;
	
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
	 public Object readResolve() {
	       return getInstance( );
	    }
	
	
}
