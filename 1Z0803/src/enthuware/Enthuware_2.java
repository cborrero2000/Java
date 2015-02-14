package enthuware;
import java.util.ArrayList;

class Enthuware_2 {
	public static void main(String[] args) {
		String s = "hello";
		StringBuilder sb = new StringBuilder( "hello" ); 
		sb.reverse(); 
		//s.reverse();
		
		System.out.println(sb);
		
		if( s == sb.toString() )
			System.out.println( "Equal" ); 
		else System.out.println( "Not Equal" );
	}
}

//class Game {
//	  public void play() throws Exception   {
//	    System.out.println("Playing...");
//	  }
//	}
//
//	class Soccer extends Game {
//	   public void play(String ball)    {
//	      System.out.println("Playing Soccer with "+ball);      
//	   }
//	}
//
//	class TestClass {
//	   public static void main(String[] args) throws Exception  {
//	       Game g = new Soccer();
//	       // 1
//	       Soccer s = (Soccer) g;
//	       // 2
//	       
//	       g.play();
//	       s.play();
//	       s.play("hello");
//	       g.play("hello");  	It doesn't have define play with argument string in Game. It won't compile
//	       
//	   }
//	}
	
	
//	g.play(); at //1 and s.play("cosco"); at //2
//	g.play(); at //1 and s.play(); at //2