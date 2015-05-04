package cab.springdemo17;

public class Circle implements Shape{

	private Point center;
	
	@Override
	public void draw() {
		System.out.println("\nDrawing a Circle:");
		System.out.println("Circle point: (" + getCenter().getX() + ", " + getCenter().getY() + ")");
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}


}
