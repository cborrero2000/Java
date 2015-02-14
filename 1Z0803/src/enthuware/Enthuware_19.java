package enthuware;

public class Enthuware_19 {
	public static void main(String args[]){
		for ( int i = 0 ; i < 3 ; i++){
			boolean flag= false;
			switch(i){
//						flag= true;
			}
			
			if(flag)
				System.out.println( i );
			
			int i1=1;
			Integer i2=1;
			
			if(i2.equals(i1))
				System.out.println("i2 and i1 are equals");
			else
				System.out.println("i2 and i1 are NOT equals");
			
		}
	}
	
	public void methodEx() throws Exception{
		try{
			throw new Exception();
		}catch(Exception e){
			
		}
	}
	
}
