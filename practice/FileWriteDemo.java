import java.io.FileWriter;
import java.io.IOException;

public class FileWriteDemo {
    public static void main(String[] args) throws IOException{
        FileWriter fw = new FileWriter("student.txt", true);
        
        fw.write("001,张三,18,北京");
        fw.write("\r\n");
        fw.write("002,李四,19,上海");
        fw.write("\r\n");

        fw.close();

    }
}
