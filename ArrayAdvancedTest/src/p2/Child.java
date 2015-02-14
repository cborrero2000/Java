package p2;
import p1.Parent;

public class Child extends Parent {
 public static void main(String[] args) {
	new Child().childMethod();
}
 void childMethod(){
	 System.out.print("this " + this.parentMethod());
	 Parent p = new Parent();
	 //System.out.print(" parent " + p.parentMethod());
 }
}