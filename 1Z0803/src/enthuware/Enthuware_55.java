package enthuware;

import java.util.ArrayList;

public class Enthuware_55 {

		   public static void main(String[] args){
			   
			   short s = Short.MAX_VALUE; 
			   char c = Short.MAX_VALUE;
			   StringBuilder sb = new StringBuilder();
			   sb.toString();
			   sb.substring(3);
			   String strsss="ddddd";
			   strsss.substring(3);
			   
//			   Object obj = new Object[]{ "aaa", new Object(), new ArrayList(), new String[]{"","hello","world", },new Object() {} };
			   
		       XMen x = null;
		       if(args.length > 0){
		          x = new XMen(){
		             public void shoot(String s){
		                for(int i=0; i<s.length(); i++){
		                  System.out.println("shot : "+s.charAt(i));
		                }
		             }
		          };
		       }
		       
//		       if(x != null){
		          x.shoot(args[0]);
//		       }
		   }
		
}

interface XMen {
	   void shoot(String a);
	}

