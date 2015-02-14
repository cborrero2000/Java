
 /*public*/ class NoPublicx { // The public type NoPublicx must be defined in its own file
	 int x=1;
	 int y=2;
	 int z=0;
	 
	 public void vdMelon(){
		 z=x+y;	 
		 vdMelon2();
	 };
	 
	 public void vdMelon2(){
		 z=x+y;	 
	 };
	 
public static void main(String[] args) {
	System.out.println("A class without public modifier will still compile fine");
	new NoPublicx().vdMelon();
//	z=x+y;  Cannot make a static reference to the non-static field x,y and z
}
}
