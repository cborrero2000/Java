package cab.springannotation;

import static cb.printscreen.PSLog.*;

import org.springframework.beans.factory.annotation.Required;

public class Circle implements Shape {
	Point center = new Point();

	public Point getCenter() {
		return center;
	}
	@Required
	public void setCenter(Point center) {
		this.center = center;
	}
	
	@Override
	public void draw() {
		ps("Drawing Circle");
		ps("Circle: Point is: (" + center.getX() + "," + center.getY() + ")");
	}

}
