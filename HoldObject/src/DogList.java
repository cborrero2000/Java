public class DogList {

	Dog[] list = new Dog[5];
	
	private int i;
	
	public void add(Dog dog){

		if(i < list.length){
			list[i] = dog;
			i++;
			
			System.out.println("Dog object has been added at the index " + i);
		}
	}
}
