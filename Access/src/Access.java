class BaseClass {

	public int x = 10;
	private int y = 10;
	protected int z = 10;
	int a = 10; //Implicit Default Access Modifier
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	private int getY() {
		return y;
	}
	private void setY(int y) {
		this.y = y;
	}
	protected int getZ() {
		return z;
	}
	protected void setZ(int z) {
		this.z = z;
	}
	int getA() {
		return a;
	}
	void setA(int a) {
		this.a = a;
	}
}

class SubclassInSamePackage extends BaseClass {

	public static void main(String args[]) {
		BaseClass rr = new BaseClass();
		rr.z = 0;
		SubclassInSamePackage subClassObj = new SubclassInSamePackage();
		//Access Modifiers - Public
		System.out.println("Value of x is : " + subClassObj.x);
		subClassObj.setX(20);
		System.out.println("Value of x is : " + subClassObj.x);
		//Access Modifiers - Public
		//    	If we remove the comments it would result in a compilaton
		//    	error as the fields and methods being accessed are private
		/*    	System.out.println("Value of y is : "+subClassObj.y);

		 subClassObj.setY(20);

		 System.out.println("Value of y is : "+subClassObj.y);*/
		//Access Modifiers - Protected
		System.out.println("Value of z is : " + subClassObj.z);
		subClassObj.setZ(30);
		System.out.println("Value of z is : " + subClassObj.z);
		//Access Modifiers - Default
		System.out.println("Value of x is : " + subClassObj.a);
		subClassObj.setA(20);
		System.out.println("Value of x is : " + subClassObj.a);
	}
}