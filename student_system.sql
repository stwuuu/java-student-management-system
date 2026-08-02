CREATE DATABASE IF NOT EXISTS student_system;

USE student_system;

CREATE TABLE IF NOT EXISTS student (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20),
    age INT,
    address VARCHAR(50)
);

INSERT INTO student (id, name, age, address) VALUES
('001', '张三', 20, '广州'),
('003', '小美', 19, '郑州'),
('004', '小丽', 20, '杭州');
