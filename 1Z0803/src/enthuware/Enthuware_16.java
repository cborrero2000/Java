package enthuware;
import static enthuware.Enthuware_15.iS;
import static enthuware.Enthuware_15.mSInt;

public class Enthuware_16 {
	
	public static void main(String[] args){
		Object iA[] = new Object[10];
		iA[0]=1;
		Object ob = new Object();
		
		System.out.println(iA[0]); 
		mSInt();
		
		System.out.println(iA);
		System.out.println(iA.getClass());
		System.out.println(iA.getClass().getName());
		System.out.println(iA.getClass().getSimpleName());
		System.out.println(iA.hashCode());
		System.out.println(Integer.toHexString(iA.hashCode()));
		System.out.println(Integer.toString(iA.hashCode(), 0xc));
		System.out.println("***************************");
		System.out.println(ob);
		System.out.println(ob.getClass());
		System.out.println(ob.getClass().getName());
		System.out.println(ob.getClass().getSimpleName());
		System.out.println(ob.hashCode());
		System.out.println(Integer.toHexString(ob.hashCode()));
		System.out.println(Integer.toString(ob.hashCode(), 0xc));
		
		Integer[] ix = new Integer[10];
		Integer ix1 = 1;
		System.out.println(ix.getClass());
		System.out.println(ix1.getClass());
	}
	
}
