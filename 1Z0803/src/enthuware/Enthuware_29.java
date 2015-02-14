package enthuware;

interface Worker { void perform_work(); }

public class Enthuware_29 implements Worker {
	Worker getWorker(final int i){
		return new Worker() {	public void perform_work() {
			System.out.println(i); }
		}; }
	
	
	public void perform_work(){}

}