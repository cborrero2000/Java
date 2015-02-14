
public class Principal {
	public static void main(String[] args) {
		
		Merengue merenguin = new Merengue(2, 2);
		
		Merengue merequetengue = new Merengue();
		
		Principal princip = new Principal(); // Nice, I can create an object within its own class
	}
	
	int getNumber(int x, int y){
		return 1;
	}
	
	protected long getNumber(int x, int y, int z){
		return 1;
	}
}
