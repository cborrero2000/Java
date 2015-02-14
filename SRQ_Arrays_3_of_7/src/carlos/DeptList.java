package carlos;

import java.util.*;

class A{
	A(){
		
	}
}

public class DeptList extends A{

	DeptList(){
		super();	
	}
	
	DeptList(int a){
		this();
	}
	
	
	public static void main(String[] args) {
		
		ArrayList deptNoList=new ArrayList<Integer>();
		deptNoList.add(0);
		deptNoList.add(1);
		deptNoList.add(5);
		deptNoList.add(19);
		deptNoList.add(101);
		deptNoList.add(600);
		
		for(Object deptNo : deptNoList){
			System.out.print(deptNoList + " ");
			
			if(deptNo.equals(5))
				break;
		}
	}
}