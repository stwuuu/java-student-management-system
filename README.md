# Java Student Management System

一个基于 Java 控制台、MySQL 和 JDBC 实现的学生管理系统，支持学生信息的添加、删除、修改和查询。

## 项目介绍

本项目是一个 Java 后端基础练习项目，从最初的控制台交互逐步升级到 MySQL 数据库存储，并引入 DAO 分层思想。

系统启动时会从 MySQL 数据库读取学生信息，保存到 `ArrayList<Student>` 集合中。用户可以通过控制台菜单完成学生信息的增删改查操作，数据库操作由 `StudentDao` 统一负责。

## 技术栈

- Java
- MySQL
- JDBC
- PreparedStatement
- ResultSet
- ArrayList
- DAO 分层

## 功能列表

- 添加学生
- 删除学生
- 修改学生
- 查询学生
- 学号重复校验
- 年龄输入合法性校验
- MySQL 数据持久化

## 项目结构

```text
Student.java         学生实体类，封装 id、name、age、address
StudentDao.java      数据访问层，负责连接 MySQL 并执行 SQL
StudentService.java  业务逻辑层，负责学号校验、添加、删除、修改等业务流程
StudentSystem.java   控制台交互层，负责菜单、输入和输出提示
```

## 数据库设计

数据库名：

```sql
student_system
```

学生表：

```sql
CREATE TABLE student (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20),
    age INT,
    address VARCHAR(50)
);
```

字段说明：

| 字段 | 类型 | 说明 |
|---|---|---|
| id | VARCHAR(20) | 学号，主键，唯一标识学生 |
| name | VARCHAR(20) | 学生姓名 |
| age | INT | 学生年龄 |
| address | VARCHAR(50) | 家庭住址 |

## 运行方式

1. 创建 MySQL 数据库和学生表。
2. 修改 `StudentDao.java` 中的数据库连接信息：

```java
private static final String URL = "jdbc:mysql://localhost:3306/student_system?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

3. 编译运行项目：

```powershell
javac -encoding UTF-8 -cp ".;lib\mysql-connector-j-9.7.0.jar" Student.java StudentDao.java StudentSystem.java
java -cp ".;lib\mysql-connector-j-9.7.0.jar" StudentSystem
```

## 项目收获

通过本项目，练习了 Java 面向对象、集合、MySQL 基础 SQL、JDBC 数据库连接、PreparedStatement 防 SQL 注入、ResultSet 结果集处理，以及简单的 DAO 分层思想。

## 后续优化方向

- 使用配置文件或环境变量管理数据库连接信息
- 引入数据库连接池，如 HikariCP 或 Druid
- 增加 Service 层，进一步拆分业务逻辑
- 使用 try-catch 提供更友好的异常提示
- 后续升级为 Spring Boot Web 后端接口项目
