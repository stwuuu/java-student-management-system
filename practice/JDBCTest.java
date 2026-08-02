import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student_system?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        String username = "root";
        String password = "123456";

        System.out.println("连接成功");
        String sql ="SELECT id,name,age,address FROM student WHERE id = ?";

        try (
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, "001");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String address = rs.getString("address");

                    System.out.println(id + "," + name + "," + age + "," + address);
                }
            }
        }
    }

        
}
