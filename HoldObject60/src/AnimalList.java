
public class AnimalList {

	private Animal[] list = new Animal[5];
	private int i;
	
	public void add(Animal a){
		if(list.length>i){
			list[i]=a;
			i++;
			
			System.out.println("Object has been added to the list at index " + i);
		}
	}
	
}
