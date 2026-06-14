DROP DATABASE IF EXISTS qlsv;
create database qlsv;
use qlsv;
create table SinhVien(
	id INT PRIMARY KEY auto_increment,
	ten nvarchar(100),
    tuoi int,
    dtb double,
    avatar VARCHAR(500),
    createdAt DATETIME
) ;
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(500),
    role VARCHAR(20)
);
INSERT INTO SinhVien(id,ten,tuoi,dtb,createdAt)
VALUES(1,N'Phạm Minh Huy',21,10,'2025-01-15'),
	(2,N'Minh Huy',21,8,'2025-12-15'),
    (3,N'Phạm Huy',21,9,'2026-01-15'),
    (4,N'Nhi',9,10,'2026-02-15'),
    (5,N'Ly',9,10,'2026-01-16'),
    (6,N'Vi',21,10,'2026-03-15'),
    (7,N'Phúc',25,10,'2026-04-15'),
    (8,N'Loan',28,10,'2025-12-15'),
    (9,N'Tín',29,10,'2026-03-05'),
    (10,N'Bình',9,10,'2025-12-17');
INSERT INTO user(username, password, role) VALUES
('admin', '$2a$10$HJeFqRWPHSivXkmoDFweueH3d8hsJWFweP5e4kgCnIqAnn9DL/bPS', 'admin'),
('user1', '$2a$10$HJeFqRWPHSivXkmoDFweueH3d8hsJWFweP5e4kgCnIqAnn9DL/bPS', 'user');
use qlsv;
SELECT * from SinhVien; 
SELECT * from user;