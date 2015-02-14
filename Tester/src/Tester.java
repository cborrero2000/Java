import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

class Tester extends JButton{
    public Tester(String label) {
        super(label);
        System.out.println("Inside the constructor");
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Inside the paint component method");
        super.paintComponent(g);
    }
    /***************************************************/
    
    public void aMethod(int X, double Y){
    	
    }

    public void AMethod(int X, double Y){
    	
    }
 
 	/***************************************************/
    
    int[] a = {new Integer(10),new Integer(10),new Integer(10),new Integer(10),new Integer(10),new Integer(10), new Integer(10)};
    Integer[] ab = {1,2,3,4,5,6,7,8,9,10};
    Integer b = 1_0;
    
    Float f = 10.5F;  // with float we need the constructor
    Double d = 10.9;
    Character e = 'c';
    
    String[] customer = {new String("Samsung"), new String("LG"), new String("Nokia")};
    
    Float[] c = {5.8F,6.3F,54.5F,5.333F,90.4F};
    
    /***************************************************/
    
    public static void main(String args[]) {
        JButton b = new Tester("Click Me");
        b.setBackground(Color.GREEN);
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.add(b);
        f.add(p);
        f.setVisible(true);
        f.setSize(200,200);
    }
}