
public class Speak {
	public static void main(String[] args) {
		Speak speakIt = new Tell();
		Tell tellIt=new Tell();
//		speakIt.tellItLikeItIs(); //The method tellItLikeItIs() is undefined for the type Speak
//		(Truth)speakIt.tellItLikeItIs(); //The method tellItLikeItIs() is undefined for the type Speak
		((Truth)speakIt).tellItLikeItIs();
		tellIt.tellItLikeItIs();
//		(Truth)tellIt.tellItLikeItIs(); //Truth cannot be resolved to a variable
		((Truth)tellIt).tellItLikeItIs();
	}
}
/// >>>
class Tell extends Speak implements Truth{
	public void tellItLikeItIs(){
		System.out.println("Right on!");
	}
}

interface Truth {public void tellItLikeItIs();}
