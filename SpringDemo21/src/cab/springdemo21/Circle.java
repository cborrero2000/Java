package cab.springdemo21;

import static cb.printscreen.PSLog.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Controller
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