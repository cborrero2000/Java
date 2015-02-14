
class Instance2 extends Instance{

	Instance2(){
		System.out.println("Constructor: Instance2");
	}
	
	Instance2(int ix){
		System.out.println("int Constructor: Instance2");
	}
	
	
	class Inst{
		int i;
		
		void method1(){
//			Inst.i=10; //Cannot make a static reference to the non-static field Inst.i
			
			i = 15;
			
			System.out.print(i);
		}
		
	}
}

 class Instance{
	
	Instance(){
		System.out.println("Constructor: Instance");
	}
	
	Instance(int i){
		System.out.println("int Constructor: Instance");
	}
	
	int ii;
	public static void main(String[] args) {
	
		//ii=10;
		
		int[][] intArray = new int[10][];
		
		System.out.println(intArray.length); //output: 10
		
		//System.out.println(intArray[0].length); java.lang.NullPointerException
		
		//byte bok = 1100;  An integer does not fit into a byte
		
		byte bok = 0b1100;
		
		Instance2 i2 = new Instance2();
		Instance2 i3 = new Instance2(5);
		
		int[]x1 = {3};
		int[][]x2 = {{3,4},{5,6}};
		int[][][]x3 = {{{73,74},{75,76}},{{33,34},{35,36}}};
		
		Integer[]x4 = {3};
		Integer[]x5 = null;
		
		System.out.println(x1);
		System.out.println(x1[0]);
		System.out.println(x2[0][0]);
		System.out.println(x3[0][0][0]);
		
		System.out.println(x1.getClass());
		System.out.println(x4.getClass());
		System.out.println(x4);
		System.out.println(x5);
		//System.out.println(x5.getClass());
		System.out.println(x3.getClass());
		
		
	}
}