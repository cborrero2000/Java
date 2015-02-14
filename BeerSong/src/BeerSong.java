//package chap01;

public class BeerSong {
    public static void main(String[] args) {
        int beerNum = 99;
        String word = "bottles";
        
        Object obj = new Object();
        Integer i2 = 5;
        long l = 10l;
        int i =5;
        
        l = i;
        i = (int)l;
        
        System.out.println("i: "+i);
        System.out.println("l: "+l);
        
        //i2 = (Integer)obj; //Wrong this one won't work because the integer i2 is expecting an Reference that knows at least the same or even more of what it knows but never less, he needs to fill completely the bucket of knowledge in full, In cannot be half empty or somewhat empty
        // It doesn not work even with a casting. There may be special cases where it works
        
        obj = i2;
        
        
        System.out.println(beerNum + " " + word + " of beer on the wall");
        
        while (beerNum > 0)
        {
            if (beerNum == 1)
            {
                word = "bottle";
            }
            System.out.println(beerNum + " " + word + " of beer on the wall");
            System.out.println(beerNum + " " + word + " of beer");
            System.out.println("Take one down.");
            System.out.println("Pass it around.");
            beerNum = beerNum - 1;
            if (beerNum > 0)
            {
                System.out.println(beerNum +  " " + word + " of beer on the wall");
            }
            else
            {
                System.out.println("No more bottles of beer on the wall");
            }
        }
        
        ////////////////////////
        class Test{
        	public Test(){
        		
        	}
        	
        	public Test(int a){
        		//this;
        	}
        	
        	//this.Test();
        	private String name="OCA";
        	public void aMethod(){
        		System.out.println(this.name);
        	}
        }
        ////////////////////////        
        
        System.out.println("Octal: " + 01234_5_______67);
        System.out.println("HexaDeimal: " + 0xFF);
        System.out.println("HexaDeimal: " + 0xF______F);
        String testString = new String("Hello World");
        System.out.println(testString.toCharArray());
        
        String str1 = "Hi";
        String str2 = "Hi";
        String str3 = new String("Hi");
        
        if (str1 == str2){
        	System.out.println("str1 and str2 are equal Objects");  // this one happens
        }
        else{
        	System.out.println("str1 and str2 are not equal Objects");
        }
        if (str1 == str3){
        	System.out.println("str1 and str3 are equal Objects");  // this one happens
        }
        else{
        	System.out.println("str1 and str3 are not equal Objects");
        }
        
        
        if (str1.equals(str2)){
        	System.out.println("str1 and str2 are equal Objects");  // this one happens
        }
        else{
        	System.out.println("str1 and str2 are not equal Objects");
        }
        if (str1.equals(str3)){
        	System.out.println("str1 and str3 are equal Objects");  // this one happens
        }
        else{
        	System.out.println("str1 and str3 are not equal Objects");
        }     
        
        
        
        String str4 = "Hi";
        String str5 = "hi";
        String str6 = new String("Hi");
        
        if (str4.compareTo(str5) == 0){
        	System.out.println("str4 and str5 are the same lexicographically speaking");  // this one happens
        }
        else{
        	System.out.println("str4 and str5 are NOT the same lexicographically speaking");  // this one happens
        }
        
        if (str4.compareTo(str5) > 0){
        	System.out.println("str4 comes first than str5 lexicographically speaking");  // this one happens
        }
        else if (str4.compareTo(str5) < 0){
        	System.out.println("str4 comes after than str5 lexicographically speaking");  // this one happens
        }
        
        
        
        
    }
}