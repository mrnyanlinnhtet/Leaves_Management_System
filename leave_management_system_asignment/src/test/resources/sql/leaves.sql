insert into account (name, role, email, password) values ('Min Lwin', 'Teacher', 'lwinmin@gmail.com', 'lwin');
insert into account (name, role, email, password) values ('Kyaw Lwin', 'Teacher', 'kyaw@gmail.com', 'kyaw');
insert into account (name, role, email, password) values ('Khant Zaw', 'Teacher', 'khant@gmail.com', 'khant');
insert into account (name, role, email, password) values ('Nyan Linn Htet', 'Student', 'nyan@gmail.com', 'nyan');
insert into account (name, role, email, password) values ('Aye Myat Mon', 'Student', 'aye@gmail.com', 'aye');
insert into account (name, role, email, password) values ('Nyi Nyi', 'Student', 'Nyi@gmail.com', 'nyi');


insert into teacher values (1, '09782929282', '2022-08-01');
insert into teacher values (2, '0972626827', '2022-09-01');
insert into teacher values (3, '08928282711', '2022-10-01');

insert into student values (4, '09988877779', 'Computer Science Major');
insert into student values (5, '09988877711', 'PHD');
insert into student values (6, '09988877733', 'MIT Student');

insert into classes (teacher_id, start_date, months, description) values (1, '2022-07-10', 3, 'JavaEE');
insert into classes (teacher_id, start_date, months, description) values (2, '2022-08-10', 3, 'Spring');
insert into classes (teacher_id, start_date, months, description) values (3, '2022-09-10', 3, 'JavaSE');


insert into registration values (1, 4, '2022-07-01');
insert into registration values (1, 5, '2022-07-01');
insert into registration values (1, 6, '2022-07-01');
insert into registration values (2, 6, '2022-07-01');
insert into registration values (2, 5, '2022-07-01');

INSERT INTO leaves VALUES('2022-11-23',1,4,'2022-11-23',3,'For PHD Exam'),
('2022-11-24',2,5,'2022-11-24',2,'For Emegercy Case');

INSERT INTO leaves_day VALUES('2022-11-23','2022-11-23',1,4),
('2022-11-24','2022-11-24',2,5);

