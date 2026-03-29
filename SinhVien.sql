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
    password VARCHAR(50),
    role VARCHAR(20)
);
INSERT INTO SinhVien(id,ten,tuoi,dtb)
VALUES(1,N'Phạm Minh Huy',21,10),
	(2,N'Minh Huy',21,8),
    (3,N'Phạm Huy',21,9),
    (4,N'Nhi',9,10);
INSERT INTO user(username, password, role) VALUES
('admin', MD5('123'), 'admin'),
('user1', MD5('123'), 'user');
use qlsv;
SELECT * from SinhVien;