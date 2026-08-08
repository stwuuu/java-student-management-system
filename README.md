# Java Student Management System

一个基于 Java 控制台、MySQL 和 JDBC 实现的学生管理系统，支持学生信息的添加、删除、修改和查询。

## 项目介绍

本项目是一个 Java 后端基础练习项目，从最初的控制台交互逐步升级到 MySQL 数据库存储，并拆分出 DAO 层和 Service 层。

系统启动时会从 MySQL 数据库读取学生信息，保存到 `ArrayList<Student>` 集合中。用户可以通过控制台菜单完成学生信息的增删改查操作，数据库操作由 `StudentDao` 统一负责，业务逻辑由 `StudentService` 负责。

## 技术栈

- Java
- MySQL
- JDBC
- PreparedStatement
- ResultSet
- ArrayList
- DAO + Service 分层
- Properties 配置文件

## 功能列表

- 添加学生
- 删除学生
- 修改学生
- 查询学生
- 学号重复校验
- 学号格式校验
- 姓名长度校验
- 年龄输入合法性校验
- 地址长度校验
- MySQL 数据持久化
- 数据库配置文件读取

## 项目结构

```text
Student.java                 学生实体类，封装 id、name、age、address
StudentDao.java              数据访问层，负责连接 MySQL 并执行 SQL
StudentService.java          业务逻辑层，负责学号校验、添加、删除、修改等业务流程
StudentSystem.java           控制台交互层，负责菜单、输入和输出提示
student_system.sql           数据库建表脚本
db.properties.example        数据库配置示例文件
lib/                         MySQL JDBC 驱动
practice/                    学习过程中的练习代码
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

## 运行方法

1. 创建 MySQL 数据库

```sql
CREATE DATABASE student_system DEFAULT CHARACTER SET utf8mb4;
```

2. 导入项目中的 `student_system.sql`

可以在 MySQL 中执行该 SQL 文件，创建 `student` 表和初始数据。

3. 创建数据库配置文件

复制 `db.properties.example`，重命名为 `db.properties`，并修改自己的数据库密码：

```properties
db.url=jdbc:mysql://localhost:3306/student_system?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8&allowPublicKeyRetrieval=true
db.username=root
db.password=your_password
```

注意：`db.properties` 中保存的是本机真实数据库密码，已经加入 `.gitignore`，不要上传到 GitHub。

4. 编译项目

```powershell
javac -encoding UTF-8 -cp ".;lib\mysql-connector-j-9.7.0.jar" Student.java StudentDao.java StudentService.java StudentSystem.java
```

5. 运行项目

```powershell
java -cp ".;lib\mysql-connector-j-9.7.0.jar" StudentSystem
```

## 项目收获

通过本项目，练习了 Java 面向对象、集合、控制台输入校验、MySQL 基础 SQL、JDBC 数据库连接、PreparedStatement 防止 SQL 注入、ResultSet 结果集处理，以及 DAO + Service 分层思想。

项目也从文件存储逐步升级为数据库存储，并进一步把数据库连接信息从 Java 源码中拆出，使用配置文件管理，避免把真实密码提交到 GitHub。

## 后续优化方向

- 使用环境变量进一步保护数据库密码
- 引入数据库连接池，例如 HikariCP 或 Druid
- 增加日志记录
- 增加更完善的异常分类提示
- 后续升级为 Spring Boot Web 后端接口项目
