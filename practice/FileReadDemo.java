import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReadDemo {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader("student.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Student> list = new ArrayList<>();

        String line;
        while((line = br.readLine()) != null){
            String[] arr = line.split(",");
            int age = Integer.parseInt(arr[2]);
            Student s = new Student(arr[0], arr[1], age, arr[3]);
            list.add(s);
        }
        br.close();
        System.out.println("一共读取到" + list.size() + "名学生");
    }
}
