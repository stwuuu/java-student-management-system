import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao {
    private static final String URL = "jdbc:mysql://localhost:3306/student_system?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static ArrayList<Student> queryAllStudents() throws SQLException {
        ArrayList<Student> list = new ArrayList<>();

        String sql = "SELECT id, name, age, address FROM student ORDER BY id ASC";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
            ) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String address = rs.getString("address");

                    Student s = new Student(id, name, age, address);
                    list.add(s);
                }
            }

            return list;
    }

    public static boolean addStudentToDB(Student s) throws SQLException {

        String sql = "INSERT INTO student (id, name, age, address) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, s.getId());
            pstmt.setString(2, s.getName());
            pstmt.setInt(3, s.getAge());
            pstmt.setString(4, s.getAddress());

            int count = pstmt.executeUpdate();
            return count > 0;
        }
    }

    public static boolean deleteStudentFromDB(String id) throws SQLException {

        String sql = "DELETE FROM student WHERE id = ?";


        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, id);

            int count = pstmt.executeUpdate();
            return count > 0;
        }
    }

    public static boolean updateStudentToDB(Student s) throws SQLException{

        String sql = "UPDATE student SET name = ?, age = ?, address = ? WHERE id = ?";

        try (
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, s.getName());
            pstmt.setInt(2, s.getAge());
            pstmt.setString(3, s.getAddress());
            pstmt.setString(4, s.getId());

            int count = pstmt.executeUpdate();
            return count > 0;
        }
    }
}


