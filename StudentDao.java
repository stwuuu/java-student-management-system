import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class StudentDao {
    public static Connection getConnection() throws SQLException {
        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream("db.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            throw new SQLException("读取数据库配置文件失败", e);
        }

        String url = prop.getProperty("db.url");
        String username = prop.getProperty("db.username");
        String password = prop.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
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


