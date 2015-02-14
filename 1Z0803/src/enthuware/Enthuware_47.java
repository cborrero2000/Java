package enthuware;
import static enthuware.PrintScreen.*;

public class Enthuware_47 {

    int x = 5;
    int getX(){ return x; }

    public static void main(String args[]) throws Exception{
    	Enthuware_47 tc = new Enthuware_47();
        tc.looper();
        System.out.println(tc.x);
        
        Integer I1=5;
        Integer I2=6;
        Long L1=3L;
        Boolean B1=null;
        
        if(I1==I1){
        	
        }
        
        double dsad= 5.8%4.5;
        
        
    }
    
    public void looper(){
        int x = 0;
        ps("getX(): " + getX());
        while( (x = getX()) != 0 ){
            for(int m = 10; m>=0; m--){
                x = m;
            }
        }
        
   }     
}