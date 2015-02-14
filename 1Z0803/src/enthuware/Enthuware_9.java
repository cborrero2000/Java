package enthuware;

public class Enthuware_9 {
    void probe(int... x) { System.out.println("In ..."); }  //1
    
    static void string(String[] x) { System.out.println("String[]"); }  //1
    
    static void integ(int[] x) { System.out.println("Integer[]"); }  //1
    
//    void probe(Integer x) { System.out.println("In Integer"); } //2
    
//    void probe(long x) { System.out.println("In long"); } //3 
    
    void probe(Long x) { System.out.println("In LONG"); } //4
    
    public static void main(String[] args){
        Integer a = 4; new Enthuware_9().probe(a); //5
        int b = 4; new Enthuware_9().probe(b); //6
        String[] str1 = {"Hello"};
        string(str1);
        
//        string("hello","world"); The method string(String[]) in the type Enthuware_9 is not applicable for the arguments (String, String)
    }
}