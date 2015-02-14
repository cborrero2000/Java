package carlos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoopTest {
	
	public static void main(String[] args) {
		for(int i=0;i<2;++i)
			for(int j =-1;j<2;++j)
				for(int k=-1; k<2; ++k){
					System.out.println(" K "+k);
					if((i++-k++)==2)
						break;
				}
		
		boolean b1=true;
		Boolean b2=false;
		
		if(b1^b2){
			System.out.println(1^1);
			System.out.println(1^-1);
			System.out.println(1^3);
			
			
			String s3="Hello;World";
			String[] s4 = s3.split(";");
			System.out.println(s4[0]);	//Output[0]: Hello
			System.out.println(s4[1]);	//Output[1]: World

			String input = "I have a cat, but I like my dog better.";
			
			System.out.println("Pattern.quote(input): " + Pattern.quote(input));

			Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
			System.out.println(p.toString());  // Output: (mouse|cat|dog|wolf|bear|human)
			Matcher m = p.matcher(input);

			List<String> animals = new ArrayList<String>();
			while (m.find()) {
				System.out.println("Found a " + m.group() + ".");
				animals.add(m.group());
				System.out.println(animals);
			}
			//Output: Found a cat.
			//Output: Found a dog.

			File f = new File("c:\\folder2\\File");
			
			System.out.println("getName(): " + f.getName());
			System.out.println("getParent(): " + f.getParent());
			
			List<? super Integer> list=new ArrayList<Integer>();
			list.add(200);
		}
			
			
		}
		
	}

