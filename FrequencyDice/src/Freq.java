import java.util.Random;

public class Freq {
	public static void main(String[] args) {
		int[] freq = new int[7];
		Random dice = new Random();
		
		for(int i=1;i<=1000;i++)
		{
			++freq[1 + dice.nextInt(6)];
			
			System.out.println("i = " + i);
		}
		
		System.out.println("\nFace\tFreq\t%");
		
		for(int i=1; i<freq.length; i++)
		{
			System.out.println(i + "\t" + freq[i] + "\t" + 100*(double)freq[i]/1000 );
		}
	}
}
