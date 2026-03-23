create database qlsv;
use qlsv;
create table SinhVien(
	id INT PRIMARY KEY auto_increment,
	ten nvarchar(100),
    tuoi int,
    dtb double
) ;
INSERT INTO SinhVien(id,ten,tuoi,dtb)
VALUES(1,N'Phạm Minh Huy',21,10),
	(2,N'Minh Huy',21,8),
    (3,N'Phạm Huy',21,9);
    
SELECT * from SinhVien;