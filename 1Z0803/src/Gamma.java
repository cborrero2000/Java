class Alpha {
String getType() { return "alpha"; }
public static void main(String[] args) {
	//Gamma g1 = new Alpha(); //(Compilation Error) Type mismatch: cannot convert from Alpha to Gamma
	//Gamma g2 = new Beta();	//(Compilation Error) Type mismatch: cannot convert from Alpha to Gamma


	System.out.print("Before");
	System.out.println("Alpha");
	System.out.print("After");
	}
}
class Beta extends Alpha {
String getType() { return "beta"; }
//public static void main(String[] args) {
//	//Gamma g1 = new Alpha(); //(Compilation Error) Type mismatch: cannot convert from Alpha to Gamma
//	//Gamma g2 = new Beta();	//(Compilation Error) Type mismatch: cannot convert from Alpha to Gamma
//
//
//		System.out.println("Beta");
//	}
}
class Gamma extends Beta {
String getType() { return "gamma"; }
//public static void main(String[] args) {
//Gamma g1 = new Alpha(); //(Compilation Error) Type mismatch: cannot convert from Alpha to Gamma
//Gamma g2 = new Beta();	//(Compilation Error) Type mismatch: cannot convert from Alpha to Gamma


//	System.out.println("Gamma");
//}
}