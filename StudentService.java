import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {
    public static int getIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++){
            Student s = list.get(i);
            if (s.getId().equals(id)){
                return i;
            }

        }
        return -1;
    }

    public static boolean isIdExists(ArrayList<Student> list, String id){
        return getIndex(list,id) != -1;
    }

    public static void addStudent(ArrayList<Student> list, Student s) throws SQLException {
        StudentDao.addStudentToDB(s);
        list.add(s);
    }

    public static void deleteStudent(ArrayList<Student> list,String id) throws SQLException {
        int index = getIndex(list, id);
        StudentDao.deleteStudentFromDB(id);
        list.remove(index);
    }

    public static void updateStudent(Student s, String name, int age, String address) throws SQLException {
        Student newStudent = new Student(s.getId(), name, age, address);

        StudentDao.updateStudentToDB(newStudent);

        s.setName(name);
        s.setAge(age);
        s.setAddress(address);
        
    }

}
