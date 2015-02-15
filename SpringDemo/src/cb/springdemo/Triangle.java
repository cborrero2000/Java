package cb.springdemo;

import static cb.printscreen.PSLog.*;

import java.util.List;

public class Triangle {

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	List<Point> points;

	public void draw() {
		for (Point point : points) {
			ps("Point  = (" + point.getX() + "," + point.getY() + ")");
		}
	}
}