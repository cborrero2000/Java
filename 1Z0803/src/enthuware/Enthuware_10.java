package enthuware;

public class Enthuware_10 {
		private M m = new M(); 
		
		public void makeItNull(M pM){ 
//			pM = null;
			m = null;
		}
		
		public void makeThisNull(){
			makeItNull(m);
		}
		
		public static void main(String[] args){
			Enthuware_10 n = new Enthuware_10();
			n.makeThisNull();
		}
}

class M { }

//class N{
//	private M m = new M(); 
//	
//	public void makeItNull(M pM){ 
//		pM = null;
//	}
//	
//	public void makeThisNull(){
//		makeItNull(m);
//	}
//	
//	public static void main(String[] args){
//		N n = new N();
//		n.makeThisNull();
//	}
//}