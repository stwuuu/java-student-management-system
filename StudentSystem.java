import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {

    private static final String URL = "jdbc:mysql://localhost:3306/student_system?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in,"GBK");
        ArrayList<Student> list = StudentDao.queryAllStudents();

        while (true) {
            System.out.println("*************************");
            System.out.println("欢迎使用学生管理系统");
            System.out.println("1. 添加学生");
            System.out.println("2. 删除学生");
            System.out.println("3. 修改学生");
            System.out.println("4. 查询学生");
            System.out.println("5. 退出系统");
            System.out.println("*************************");
            System.out.print("请输入操作编号：");

            String choose = sc.next();

            switch (choose) {
                case "1":
                    addStudent(list);
                    break;
                case "2":
                    deleteStudent(list);
                    break;
                case "3":
                    updateStudent(list);
                    break;
                case "4":
                    queryStudent(list);
                    break;
                case "5":
                    System.out.println("感谢使用学生管理系统！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效操作，请重新输入！");
                 }
            }
        }

    public static void addStudent(ArrayList<Student> list) throws SQLException{
        Scanner sc = new Scanner(System.in,"GBK");
        String id;
        while(true){
            System.out.println("请输入学生学号");
            id = sc.next();
            int index = getIndex(list, id);
            if(index == -1){
                break;
            }
            System.out.println("该学号已存在，请重新输入！");
        }
        System.out.print("请输入学生姓名：");
        String name = sc.next();
        System.out.print("请输入学生年龄：");
        int age = sc.nextInt();
        System.out.print("请输入学生家庭住址：");
        String address = sc.next();

        Student s = new Student(id, name, age, address);
        StudentDao.addStudentToDB(s);
        list.add(s);
        System.out.println("学生信息添加成功！");
    }

    public static void queryStudent(ArrayList<Student> list){
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加后查询");
            return;
        }
            System.out.printf("%-12s%-8s%-6s%-10s%n", "学号", "姓名", "年龄", "地址");
            for (int i = 0; i < list.size(); i++) {
                Student s = list.get(i);
                System.out.printf("%-12s%-8s%-6d%-10s%n", s.getId(), s.getName(), s.getAge(), s.getAddress());
            
        }
    }

    public static int getIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++){
            Student s = list.get(i);
            if (s.getId().equals(id)){
                return i;
            }

        }
        return -1;
    }

    public static void deleteStudent(ArrayList<Student> list) throws SQLException{
        Scanner sc = new Scanner(System.in,"GBK");
        System.out.print("请输入要删除的学生学号：");
        String id = sc.next();
        int index = getIndex(list, id);
        if (index == -1){
            System.out.println("学号不存在，请重新输入！");
        } else {
            StudentDao.deleteStudentFromDB(id);
            list.remove(index);
            System.out.println("学生信息删除成功！");
        }
    }

    public static void updateStudent(ArrayList<Student> list) throws SQLException{
        Scanner sc = new Scanner(System.in,"GBK");
        System.out.print("请输入要修改的学生学号：");
        String id = sc.next();
        int index = getIndex(list, id);
        if(index == -1){
            System.out.printf("学号为%s的学生不存在，请重新输入！%n", id);
            return;
        }
            Student s = list.get(index);

            System.out.println("请输入新的学生姓名：");
            String name = sc.next();

            System.out.println("请输入新的学生年龄：");
            int age = sc.nextInt();

            System.out.println("请输入新的学生家庭住址：");
            String address = sc.next();

            s.setName(name);
            s.setAge(age);
            s.setAddress(address);
            StudentDao.updateStudentToDB(s);
            System.out.println("学生信息修改成功！");
    } 
}