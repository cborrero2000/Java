//class Fred1{
//public static void main (String args) { //The type of the expression must be an array type but it resolved to String
//System.out.println(args[1]);
//}
//}
//class Fred1{								//java.lang.ArrayIndexOutOfBoundsException: 2
//public static void main (String [] args) {
//System.out.println(args[2]);
//}
//}
class Fred1 {									//Compiles Fine. Output: [Ljava.lang.String;@aede59e
public static void main (String [] args) { 
System.out.println (args);
}
}
//class Fred1 {									//Compiles Fine. Output: walls
//public static void main (String [] args) {
//System.out.println (args[1]);
//}
//}