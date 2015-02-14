package sub;

	
	import epractizelabs.*;
	
	class Sub extends Super{
		
		public static void main(String[] args){
			//new Super().print(); //Line6
			new Sub().print(); //Line7
			
			String name1 = new String("JAVA TIGER");
			String name2 = new String("JAVA TIGER");
			System.out.println(name1==name2);
			
			// Class Sub=Sub.class;
			// Class ExSample=ExSample.class;             System.out.print("Hello ");
			int a; // Local variable can only take final modifier and Default
		}
	}


