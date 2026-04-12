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
    (4,N'Nhi',9,10),
    (5,N'Ly',9,10),
    (6,N'Vi',21,10),
    (7,N'Phúc',25,10),
    (8,N'Loan',28,10),
    (9,N'Tín',29,10),
    (10,N'Bình',9,10);
INSERT INTO user(username, password, role) VALUES
('admin', '$2a$10$HJeFqRWPHSivXkmoDFweueH3d8hsJWFweP5e4kgCnIqAnn9DL/bPS', 'admin'),
('user1', '$2a$10$HJeFqRWPHSivXkmoDFweueH3d8hsJWFweP5e4kgCnIqAnn9DL/bPS', 'user');
use qlsv;
SELECT * from SinhVien; 
SELECT * from user;