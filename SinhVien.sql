DROP DATABASE IF EXISTS qlsv;
create database qlsv;
use qlsv;
create table SinhVien(
	id INT PRIMARY KEY auto_increment,
	ten nvarchar(100),
    tuoi int,
    dtb double
) ;
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(500),
    role VARCHAR(20)
);
INSERT INTO SinhVien(id,ten,tuoi,dtb)
VALUES(1,N'Phạm Minh Huy',21,10),
	(2,N'Minh Huy',21,8),
    (3,N'Phạm Huy',21,9),
    (4,N'Nhi',9,10);
INSERT INTO user(username, password, role) VALUES
('admin', '$2a$12$vwv11gWIdd72wPByjF5VTO.Q8lH1z924R9YF9lTqPXYX3U4V1P/lS', 'admin'),
('user1', '$2a$12$vwv11gWIdd72wPByjF5VTO.Q8lH1z924R9YF9lTqPXYX3U4V1P/lS', 'user');
use qlsv;
SELECT * from SinhVien;-- 
SELECT * from user;