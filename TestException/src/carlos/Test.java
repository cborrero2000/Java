package carlos;

	class Test {
		
		public static void main(String[] args) {
			try{
				method();
			} catch (Exception e){}
		}
		
		static void method(){
			
			try{
				//throw new NullPointerException();
				throw new NullPointerException();
			} catch(ArithmeticException e){
			
			System.out.println("b");
			}
			
			finally{
				System.out.println("c");
			}

			System.out.println("d");
		}
	}