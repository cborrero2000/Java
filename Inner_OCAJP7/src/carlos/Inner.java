package carlos;

public class Inner {
	
	public static void main(String[] args){
		Inner inner = new Inner();
		inner.new Outer().innerMethod();
		
		////////////////////////////////////////////////////////////
		outer: for(int x=0; x<2; x++){
			middle: for(int y=0; y<2; y++){
				inner: for(int z=0;z<2;z++){
					if(y==x){
						System.out.println("x="+x+" y="+y+" z="+z);
						break middle;
					}
				}
			}
		}
		////////////////////////////////////////////////////////////
		
	}
	
	final int num = 2399;

	class Outer {
		
		int num=9932;
		
		void innerMethod(){
			//System.out.println("Inner.this.num: " + Inner.num); //Doesn't know which one of the num variables to access since it has access to the outer and inner so then we need this to specify from what class object we want to check the variable value
			System.out.println("Inner.this.num: " + Inner.this.num);
			System.out.println("Outer.this.num: " + Outer.this.num);
		}
	}

}
