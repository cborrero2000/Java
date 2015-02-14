import java.io.FileNotFoundException;
import java.io.IOException;

class SuperClass {
    public int doIt(String str, Integer... data)throws IOException {
 String signature = "(String, Integer[])";
 System.out.println(str + " " + signature);
 return 1;
 }
}

public final class SubClass extends SuperClass {
    public int doIt(String str, Integer... data) throws FileNotFoundException {
        String signature = "(String, Integer[])";
        System.out.println("Overridden: " + str + " " + signature);
        return 0;
    }

    public static void main(String... args) {
        SuperClass sb = new SubClass();
        try {
            sb.doIt("hello", 3);
        } catch (Exception e) {
        }//Object
    }
}