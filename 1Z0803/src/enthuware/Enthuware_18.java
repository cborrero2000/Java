package enthuware;

public class Enthuware_18 {
	
	public static void main(String[] args) {
			int cca =1;
			loop: for (int i = 1; i < 5; i++){
					for (int j = 1; j < 5; j++){
						System.out.println(i);
						if (cca == 0) {  continue loop;  }
						System.out.println(j);
					}
		      	}
	}
}
