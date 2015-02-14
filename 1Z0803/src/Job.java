import java.io.FileNotFoundException;

interface HourlyWorker{
	public static double minimum_wage=12.00;
	abstract public void performWork(double hours);
}


class Job {
Job(){};
private double [] measurements=new double[]{4.00,5.00};
private String name;
private String[]reqs;
public Job(String name, String... reqs) {
	this();
	//this("hello","asd"); Recursive constructor invocation Job(String, String...)
	//this(); Two calls of constructors, compilation error, Only one inside of the constructor and at the first line only
	this.name=name;
	this.reqs=reqs;
	
	byte b1 = 0Xf;
	byte b2 = 0B01;
}

public void posti(){};

public void post() {this.name="Hellow"; this.pen();}
private void post(char[] rawData) {}
private void pen() {
	if(true)
		throw new /*FileNotFoundException()*/IllegalArgumentException();
	System.out.println("Hello Java");
	
}

public double[] getMeasurements(){
//	return this.measurements;
	try{
		pen();
	} finally{
	
	}
	return measurements.clone();
}

public static void staticM(){
	System.out.println("Static M");
}

public static void main(String[] args) {
	String Str = new String("Welcome to Tutorialspoint.com");

	System.out.print("Return Value :" );
	System.out.println(Str.toCharArray() );
	
	System.out.println(Str.toCharArray()[0]);
	
	char [] ce = Str.toCharArray();
	
	char[] ca = new char[4];
	ca[0]='a';
	ca[1]='b';
	ca[2]='c';
	ca[3]='d';
	
	System.out.println("ca: " + ca[0]);
	System.out.println("ce: " + ce[0]);
	
	System.out.println(ca);
	System.out.println(ce);
	
	Programmer pg = new Programmer();
	Job job = new Job();
	System.out.println(job.measurements);
	System.out.println(job.getMeasurements());
	
	pg.ms=job.getMeasurements();
	System.out.println(pg.ms.equals(job.getMeasurements()));
	System.out.println(pg.ms==job.getMeasurements());

//	pg.initDA(job.getMeasurements());
	pg.ms[0]=17.00;
	double[] dm = job.getMeasurements();
	//dm[0]=33.00;
	System.out.println("job.measurements[0]: "+job.measurements[0]);
	System.out.println(job.getMeasurements());
	//	pg.ms = new double[] {33.00,11.05};
//	System.out.println(pg.ms);
//	System.out.println(pg.ms[0]);
//	System.out.println(job.measurements[0]);
//	System.out.println(job.getMeasurements());
	
	System.out.println(job.toString());
	System.out.println(dm.toString());
	staticM();
	
//	pen();
	
}

}

class Programmer extends Job{
	public double[] ms;
	private String[] languages;
	
	public void initDA(double[] measure){
		measure = new double[] {35.00,11.05};
		System.out.println(measure[0]);
		System.out.println(measure.equals(getMeasurements()));
	}
	
	public void posti(){};
	
	public Programmer() {}
	public Programmer(String name, String...reqs) {super(name, reqs);}
	public void post(String language) {
		staticM();
		post(); 
		//post(language.toCharArray()); //The method post(char[]) from the type Job is not visible
		//this(); //Constructor call must be the first statement in a constructor
		//this(languages.toCharArray()); //Constructor call must be the first statement in a constructor
		//this().toString(); //Constructor call must be the first statement in a constructor
	}
	
	public static void main(String[] args) {
		 String Str = new String("Welcome to Tutorialspoint.com");

	      System.out.print("Return Value :" );
	      System.out.println(Str.toCharArray() );
	      
	      Job job = new Job("Hello","World");
	      job.post();	   
	      
	      staticM();
	}
}