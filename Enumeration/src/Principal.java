import java.util.EnumSet;

public class Principal {
	
	public static void main(String[] args) {
		
		for(Constants people : Constants.values())
			System.out.printf("%s\t%s\t%s\n", people, people.getDesc(), people.getYear());

		System.out.println("\nAnd now for the range of constants\n");
		
		for(Constants people : EnumSet.range(Constants.karla, Constants.carlos))
			System.out.printf("%s\t%s\t%s\n", people, people.getDesc(), people.getYear());
		
		Day x = Day.MONDAY;
		System.out.printf("%s\t%s", x, Day.TUESDAY);
		
		switch(x){
		case MONDAY:
			System.out.printf("\nJava %s\t%s", x, Day.TUESDAY);
		}
	}
}