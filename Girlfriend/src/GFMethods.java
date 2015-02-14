
public class GFMethods {
	
	private String gfName;
	
	GFMethods(String name)
	{
		gfName = name;
	}
	
	public void setName(String name)
	{
		gfName = name;
	}
	
	public String getName()
	{
		return gfName;
	}
	
	public void sayingHerName()
	{
		System.out.printf("The name of my first girlfriend is %s\n", getName());
	}

}
