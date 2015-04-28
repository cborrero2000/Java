
public class TestInterfaceCasting {
Communicator comm;
Communicator comm2;

public void method(){
	comm = new Test();
	comm2 = (Communicator) new Test();
}

}
