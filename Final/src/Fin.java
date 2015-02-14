
public class Fin {
	
	private int sum;
	public final int NUMBER;
	
	Fin(int n){
		NUMBER=n;
	}

	public void add(){
		sum += NUMBER;
	}
	
	public String toString(){
		return String.format("%d", sum);
	}
}
