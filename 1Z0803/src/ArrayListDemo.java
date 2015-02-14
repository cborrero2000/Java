import java.util.ArrayList;

public class ArrayListDemo {
   public static void main(String[] args) {
      
    // create an empty array list with an initial capacity
    ArrayList<String> arrlist = new ArrayList<String>(5);

    // use add() method to add values in the list
    arrlist.add("G");
    arrlist.add("E");
    arrlist.add("F");
    arrlist.add("M");
    arrlist.add("E");

    System.out.println("Size of list: " + arrlist.size());

    // let us print all the values available in list
    for (String value : arrlist) {
      System.out.println("Value = " + value);
    }  
	
    // Removes first occurrence of "E"
    arrlist.remove("E"); // Removes first occurrence only
    //arrlist.remove("E");
    //arrlist.remove("E");

    System.out.println("Now, Size of list: " + arrlist.size());
	
    // let us print all the values available in list
    for (String value : arrlist) {
      System.out.println("Value = " + value);
    }  
  }
}   