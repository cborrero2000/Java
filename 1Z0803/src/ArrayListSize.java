import java.lang.Math;
import java.util.ArrayList;

public class ArrayListSize {

	
	public static void main(String[] args) {
	
		ArrayList<Integer> list = new ArrayList<>(1);
		list.ensureCapacity(2);
		System.out.println(list);	
		System.out.println("size: "+list.size());
		System.out.println("capacity: "+list.size());
		
		list.add(1);
		System.out.println(list);	
		System.out.println("size: "+list.size());
		System.out.println("capacity: "+list.size());
		
		list.add(2);
		System.out.println(list);	
		System.out.println("size: "+list.size());
		System.out.println("capacity: "+list.size());
		
		list.add(3);
		System.out.println(list);	
		System.out.println("size: "+list.size());
		System.out.println("capacity: "+list.size());
		
		list.remove(1);
//		list.add(2);
		System.out.println(list);	
		System.out.println("size: "+list.size());
		System.out.println("capacity: "+list.size());
		
		list.removeAll(list);
//		list.add(2);
		System.out.println(list);	
		System.out.println("size: "+list.size());
		System.out.println("capacity: "+list.size());
		
		int ix[] =new int[3];
		
		System.out.println(ix.length);
		System.out.println(ix[1]);
		
		String[] str = new String[5];
		System.out.println(str.length);
		System.out.println(str[1]);		
	}
}