package cab.springdemo20;

import static cb.printscreen.PSLog.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Circle implements Shape {

	private Point center = new Point();

	public Point getCenter() {
		return center;
	}

	@Resource
	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public void draw() {
		ps("Center: x=" + center.getX() + " y=" + center.getY());

	}
	
	@PostConstruct
	public void initializeCircle() {
		ps("Circle is being initialized");
	}
	
	@PreDestroy
	public void destroyCircle() {
		ps("Circle is being destroyed");
	}
}