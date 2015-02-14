
public class ThisString {
		private int hour;
		private int minute;
		private int second;
		
		ThisString(int h, int m, int s){
			hour = h;
			minute = m;
			second = s;
			
			System.out.printf("The constructor for this is %s\n", this);
		}
		
		public String toString(){
			return String.format("%02d:%02d:%02d", hour, minute, second);
		}
}
