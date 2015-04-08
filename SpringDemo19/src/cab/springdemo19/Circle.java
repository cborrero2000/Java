package cab.springdemo19;

import static cb.printscreen.PSLog.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Circle implements Shape {

	private Point center = new Point();

	public Point getCenter() {
		return center;
	}

	@Autowired
	@Qualifier("circleRelated")
	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public void draw() {
		ps("Center: x=" + center.getX() + " y=" + center.getY());

	}
}