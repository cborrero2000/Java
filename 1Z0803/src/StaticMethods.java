class StaticMethods {
	static void one() {
	two();
	StaticMethods.two();
//	three();				//Cannot make a static reference to the non-static method three() from the type StaticMethods
//	StaticMethods.four();	//Cannot make a static reference to the non-static method four() from the type StaticMethods
	}
	static void two() { }
	void three() {
	one();
	StaticMethods.two();
	four();
//	StaticMethods.four();	//Cannot make a static reference to the non-static method four() from the type StaticMethods
	}
	void four() { }
	

	
}

class TestS{

//	three();				//This method requires a body instead of a semicolon. It requires an instance to be called
}
