package enthuware;
import java.*;

public class Enthuware_32 {

	static String j = "";
	public static void method( int i){
		try{
			if(i == 2){
				throw new Exception();
			}
			j += "1";
		}
		catch (Exception e){
			j += "2";
//			return;
		}
		finally{
			j += "3";
		}
		j += "4";
	}
	public static void main(String args[]){
		method(1);
		method(2);
		System.out.println(j);
	}
}