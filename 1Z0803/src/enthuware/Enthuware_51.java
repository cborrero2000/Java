package enthuware;

public class Enthuware_51 {

    public static void main(String[] args) {
        Bbxx b = new Bbxx();
        Aaxx a = b;
        Ii i = a;
        
        System.out.println(i);
        System.out.println((Bbxx)a);
        System.out.println(a);
        System.out.println(b);
        
    }
}

interface Ii { }

class Aaxx implements Ii{
    public String toString(){ return "in a"; }
}

class Bbxx extends Aaxx{
    public String toString(){ return "in b"; }
}