
public class Principal {
	public static void main(String[] args) {
		Constructor obj = new Constructor();
		Constructor obj1 = new Constructor(12);
		Constructor obj2 = new Constructor(20, 15);
		Constructor obj3 = new Constructor(22, 17, 10);
		
		//obj.setTime(15, 3, 20);
		System.out.println(obj.toMilitary());
		System.out.println(obj1.toMilitary());
		System.out.println(obj2.toMilitary());
		System.out.println(obj3.toMilitary());
	}

}
