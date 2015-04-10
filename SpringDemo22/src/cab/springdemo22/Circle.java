package cab.springdemo22;

import static cb.printscreen.PSLog.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Controller
public class Circle implements Shape {

	public MessageSource getMessageSource() {
		return messageSource;
	}

	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private Point center;
	
	@Autowired
	private MessageSource messageSource;

	public Point getCenter() {
		return center;
	}

	@Resource
	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public void draw() {
		//ps("Drawing Circle");
		ps(this.messageSource.getMessage("drawing.circle", null, "Drawing Circle Default", null));
		//ps("Center: x=" + center.getX() + " y=" + center.getY());
		ps(this.messageSource.getMessage("drawing.center", new Object[] {this.center.getX(), this.center.getY()}, "Drawing Point", null));
		
		ps(this.messageSource.getMessage("greeting", null, "Default Greeting", null));
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