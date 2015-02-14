package carlos;

class A {
	public void aMethod()/*throws Exception*/{
		System.out.println("Inside A");
	}
}

class B extends A{
	public static void main(String[] args) {
		A ob = new B();
		try{
		ob.aMethod();
		
			
		}catch(ArrayIndexOutOfBoundsException e){
			
			System.out.println("Inside 1");
			return;
		}catch(IndexOutOfBoundsException e){
			System.out.println("Inside 2");
		}catch(Exception e){
			System.out.println("Inside 3");
		}finally{
			System.out.println("finally");
		}
		
	}
	
	public void aMethod() throws ArrayIndexOutOfBoundsException{
		System.out.println("Inside B");
		throw new ArrayIndexOutOfBoundsException();
	}
	
	class AA{}
	
	class BB extends AA{}
	
	class CC extends BB{}
	
	
	AA a1 = new BB();
	
	AA a2 = new CC();
	
	BB b1 =  new CC();
	
	//CC c1 = (CC)new BB();
}
