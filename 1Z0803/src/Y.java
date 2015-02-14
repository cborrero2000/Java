//SOLUTION 1
//import java.io.IOException;
//public class Y {
//public static void main(String[] args) throws IOException {
//try {
//doSomething();
//}
//catch (RuntimeException e) {
//System.out.println(e);
//}
//}
//static void doSomething() throws IOException {
//if (Math.random() > 0.5) throw new IOException();
//throw new RuntimeException();
//}
//}

//SOLUTION 2
//import java.io.IOException;
//public class Y {
//public static void main(String[] args) throws IOException {
//try {
//doSomething();
//}
//catch (RuntimeException e) {
//System.out.println(e);
//}
//}
//static void doSomething() {
//if (Math.random() > 0.5)
//	try {
//		throw new IOException();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//throw new RuntimeException();
//}
//}

//SOLUTION 3
//import java.io.IOException;
//public class Y {
//public static void main(String[] args) {
//try {
//doSomething();
//}
//catch (IOException e) {
//System.out.println(e);
//}
//}
//static void doSomething() throws IOException {
//if (Math.random() > 0.5)
//		throw new IOException();
//throw new RuntimeException();
//}
//}


//SOLUTION 3
import java.io.IOException;
public class Y {
public static void main(String[] args) throws RuntimeException {
try {
doSomething();
}
catch (RuntimeException e) {
System.out.println(e);
}
}
static void doSomething() {
if (Math.random() > 0.5)
		throw new RuntimeException();
throw new RuntimeException();
}
}