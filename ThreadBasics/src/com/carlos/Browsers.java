package com.carlos;

import java.util.Random;



public class Browsers extends Thread {

	Browsers(){
		
	}
	
	Browsers(String str){
		super(str);
	}
	
	public void run(){ 	// Overriding run() method because I am extending from Thread because Thread Class implements run() method
						// with a empty body than therefore does nothing, if I would have been implementing Runnable
						// Interface instead, then I will be implementing the run method 
		for(int i=0; i<5; i++){
			
			System.out.println(getName() + "-"+(i+1));
			
			try{
			
				sleep((int)(Math.random()*2000));
			}catch (InterruptedException e){
				
			}
		}

		System.out.println("Finished Thread: " + getName());
		
		boolean a=true;
		boolean b=true;
		
		if (a&&b==true)
			System.out.println("true-che-man");
		
		int c=213;
		float d= 123F;
		boolean e = true;
		
		if(b|c==d)
			System.out.println("It's true 123F == 123 integer");
		
		float f = c + d;
		
		int[][] arr = {{1,2,3,new Integer(6)},{new Integer(100),300,new Integer(400),600,700}};
		System.out.println("arr[1].length: " + arr[1].length);
			
	}
	
	// public void run(){}: //Duplicate method, by extending Thread class which implements empnty the run method from
							// the interface Runnable, I will require to create another class or a subclass
							// if I want to have different run functionality, so that's why depending on the 
							// requirements sometimes is better not to extend Thread class but create Runnable objects
							// and implement the run method functionality in each one of them.  
}

class BrowsersTestDrive{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		new Browsers("Google").start();
		new Browsers("Yahoo").start();
		new Browsers("Bing").start();
		new Browsers("Ask").start();
	}
	
}
