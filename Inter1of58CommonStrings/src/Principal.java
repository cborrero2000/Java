
public class Principal {
	
	public static void main(String[] args) {
		String[] words = {"funk","chunk","furry","baconator"};
		
		for(String w:words){
			if(w.startsWith("fu"))
				System.out.println(w + " starts with fu");
		}
		
		System.out.println("");
		
		for(String w:words){
			if(w.endsWith("unk"))
				System.out.println(w + " ends with unk");
	}

}

}