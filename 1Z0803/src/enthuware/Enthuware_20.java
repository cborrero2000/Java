package enthuware;

class Enthuware_20 {

//	final boolean bool; The blank final field bool may not have been initialized
	
	static Object obj = new Object();
	
	int i1;
	
	public void method(){
		int i;
		i=this.i1;
		i=i1;
	}
	
	public static void main(String[] args) { 
		Baap b = new Beta(); 
		System.out.println(b.h + " " + b.getH()); 
		Beta bb = (Beta) b; 
		System.out.println(bb.h + " " + bb.getH());
		
		obj.toString();
	}
}

class Baap {
	public int h = 4;
	public int getH() {
		System.out.println("Baap " + h); 
		return h;} }  

class Beta extends Baap {
	public int h = 44; 
	public int getH() { 
		System.out.println("Beta " + h); 
		return h;} 
	}