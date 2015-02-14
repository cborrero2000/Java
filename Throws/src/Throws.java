import java.io.*;
class file1{
    public static void main(String[] args) throws IOException{
      FileWriter file = new FileWriter("D:\\Data1.txt");
      file.write("Guru99 Melonsqui");
      file.write("\r\nMelonar");
      file.close();
    }
}