abstract class Shape{
   final int b = 20;
   public void display(){
     System.out.println("This is display method");
   }
   abstract public void calculateArea();
}
 
class Rectangle extends Shape{
   public static void main(String args[]){
      Rectangle obj = new Rectangle();
      obj.display();
      Rectangle rect = new Rectangle();
      rect.calculateArea();
     //obj.b=200; //Variable b is Final and it cannot be changed.
  }
   
   public void calculateArea()
   {
	   System.out.println("Rectangle Area Calculated");
   }
   
}