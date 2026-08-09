import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in,"GBK");
        ArrayList<Student> list;
        try {
            list = StudentDao.queryAllStudents();
        } catch (SQLException e) {
            System.out.println("数据库连接失败，请检查MySQL是否启动、账号密码是否正确。");
            return;
        }

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
                    try {
                        addStudent(sc, list);
                    } catch (SQLException e) {
                        System.out.println("添加学生失败，请检查数据库连接。");
                    }
                    break;
                case "2":
                    try {
                        deleteStudent(sc, list);
                    } catch (SQLException e) {
                        System.out.println("删除学生失败，请检查数据库连接。");
                    }
                    break;
                case "3":
                    try {
                        updateStudent(sc, list);
                    } catch (SQLException e) {
                        System.out.println("修改学生失败，请检查数据库连接。");
                    }
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

    public static void addStudent(Scanner sc, ArrayList<Student> list) throws SQLException{
        
        String id = inputAddId(sc, list);

        //输入姓名
        String name = inputName(sc);

        System.out.print("请输入学生年龄：");
        int age = inputAge(sc);

        //输入地址
        String address = inputAddress(sc);

        Student s = new Student(id, name, age, address);
        boolean success = StudentService.addStudent(list, s);

        if (success) {
            System.out.println("学生信息添加成功!");
        } else {
            System.out.println("学生信息添加失败!");
        }
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

    public static void deleteStudent(Scanner sc, ArrayList<Student> list) throws SQLException{
        String id = inputExistingId(sc, list, "请输入要删除的学生学号：");
        

        boolean success = StudentService.deleteStudent(list, id);
            
        if (success) {
                System.out.println("学生信息删除成功！");
        } else {
                System.out.println("学生信息删除失败！");
        }
    }


    public static void updateStudent(Scanner sc, ArrayList<Student> list) throws SQLException{
        String id = inputExistingId(sc, list, "请输入要修改的学生学号：");
        int index = StudentService.getIndex(list, id);
        
            Student s = list.get(index);

            String name = inputName(sc);

            System.out.println("请输入新的学生年龄：");
            int age = inputAge(sc);

            String address = inputAddress(sc);

            boolean success = StudentService.updateStudent(s, name, age, address);
            
            if (success) {
                System.out.println("学生信息修改成功！");
            } else {
                System.out.println("学生信息修改失败！");
            }
    }

    public static int inputAge(Scanner sc){

        while (true) {
            if (sc.hasNextInt()) {
                int age = sc.nextInt();
                if (age >= 1 && age <= 120) {
                    return age;
                } else {
                    System.out.println("年龄必须在1到120之间，请重新输入：");
                }
            } else{
                sc.next();
                System.out.print("年龄必须是整数，请重新输入：");
            }
        }
    }

    public static String inputAddId(Scanner sc, ArrayList<Student> list) {
        while (true) {
            System.out.println("请输入学号");
            String id = sc.next();

            if (!id.matches("\\d{1,20}")) {
                System.out.println("学号只能是1到20位数字，请重新输入！");
                continue;
            }

            if (StudentService.isIdExists(list, id)) {
                System.out.println("该学号已存在，请重新输入！");
                continue;
            }

            return id;
        }
    }

    public static String inputName(Scanner sc) {
        while (true) {
            System.out.println("请输入学生姓名：");
            String name = sc.next();

            if (name.length() > 20) {
                System.out.println("姓名不能超过20个字符，请重新输入！");
                continue;
            }

            return name; 
        }
    }

    public static String inputAddress(Scanner sc) {
        while (true) {
            System.out.println("请输入学生家庭住址：");
            String address = sc.next();

            if (address.length() > 50) {
                System.out.println("地址不能超过50个字符，请重新输入！");
                continue;
            }

            return address;
        }
    }

    public static String inputExistingId(Scanner sc, ArrayList<Student> list, String message) {
        while (true) {
            System.out.println(message);
            String id = sc.next();

            if (!id.matches("\\d{1,20}")) {
                System.out.println("学号只能是1到20位数字，请重新输入！");
                continue;
            }

            if (!StudentService.isIdExists(list, id)) {
                System.out.println("学号不存在，请重新输入！");
                continue;
            }

            return id;
        }
    }
}