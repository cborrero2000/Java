package enthuware;

interface FlyerX{ }
class BirdX implements FlyerX { }
class EagleX extends BirdX { }
class BatX { }

enum enumerator{HOLA, HELLO};

public class Enthuware_26 {
    enumerator enux;
	enumerator method12(){return enux;}
	
    public static void main(String[] args) {
        FlyerX f = new EagleX();
        EagleX e = new EagleX();
        BatX b = new BatX();
        
        if(f instanceof BirdX) System.out.println("f is a BirdX");
        if(e instanceof FlyerX) System.out.println("e is a FlyerX");
        if(b instanceof FlyerX) System.out.println("b is a FlyerX");
        
        //for(;;) System.out.println("Hello..."); Infinite Loop, will compile and run forever until stopped
    }
}