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

    public static boolean addStudent(ArrayList<Student> list, Student s) throws SQLException {
        boolean success = StudentDao.addStudentToDB(s);
        
        if (success) {
            list.add(s);
        }

        return success;

    }

    public static boolean deleteStudent(ArrayList<Student> list,String id) throws SQLException {
        int index = getIndex(list, id);

        boolean success = StudentDao.deleteStudentFromDB(id);

        if (success) {
            list.remove(index);
        }

        return success;
        
    }

    public static boolean updateStudent(Student s, String name, int age, String address) throws SQLException {
        Student newStudent = new Student(s.getId(), name, age, address);

        boolean success = StudentDao.updateStudentToDB(newStudent);

        if (success) {
            s.setName(name);
            s.setAge(age);
            s.setAddress(address);
        }

        return success;
        
    }

    public static ArrayList<Student> searchStudentsByName(ArrayList<Student> list, String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);

            if (s.getName().contains(keyword)) {
                result.add(s);
            }
        }

        return result;
    }

}
