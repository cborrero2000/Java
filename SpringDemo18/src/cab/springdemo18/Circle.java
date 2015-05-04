package cab.springdemo18;

import org.springframework.beans.factory.annotation.Required;

public class Circle implements Shape {

	private Point center;
	
	public Point getCenter() {
		return center;
	}

	@Required
	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public void draw() {
		System.out.println("Drawing a Circle:");
		System.out.println("Circle point: (" + getCenter().getX() + ", " + getCenter().getY() + ")");
		
	}

	
	
}
