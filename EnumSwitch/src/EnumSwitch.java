enum Week {
Sunday, Monday, Tuesday, Wednesday,Thursday,Friday,Satarday
}

public class EnumSwitch {
	public static void main(String args[]) {
		
		final float i=5.5f;
		int j=5;
		Week week;
		week = Week.Sunday;
		
		switch(week) {
			default:
				System.out.println("default");
		
				switch(j) {
					case 2+4:
						System.out.println("int");
					case (int)4.9:
						System.out.println("type casting");
					case (int)i:
						System.out.println("final float"); 
				}
		
			case Sunday:
				System.out.println("Sunday");
				break;
			case Monday:
				System.out.println("Monday");
				break;
			case Tuesday:
				System.out.println("Tuesday");
				break;
			case Wednesday:
				System.out.println("Wednesday");
		}
	}
}