import java.io.*;

interface SampleClosable {
public void close () throws java.io.IOException;
}

//public class TestImp implements SampleClosable {
//public void close () throws java.io.IOException {
//// do something
//}
//}

//public class TestImp implements SampleClosable {
//public void close () throws Exception {  /*Exception is not subclass of IOException, actually is the SuperClass, Compilation fails*/
//// do something
//}
//}

//public class TestImp implementations SampleClosable { /*Wrong implementing keyword*/
//public void close () throws Exception {
//// do something
//}
//}

//public class TestImp extends SampleClosable {		/*A Interface cannot not be extended by a class only by another Interface*/
//public void close () throws java.io.IOException {
//// do something
//}
//}