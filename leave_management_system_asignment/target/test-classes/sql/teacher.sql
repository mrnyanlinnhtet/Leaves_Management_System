
INSERT INTO account(name,role,email,password)VALUES('Min Lwin','Teacher','lwinmin@gmail.com','lwin'),
('Waifer Kolar','Teacher','waifer@gmail.com','waifer'),
('Khant Zaw','Teacher','khantzaw@gmail.com','khant');

INSERT INTO teacher(id,phone,assign_date)VALUES(1,'0911111','2022-09-09'),
(2,'0922222','2022-10-10'),
(3,'0933333','2022-12-01');

INSERT INTO classes(teacher_id,start_date,months,description)VALUES(1,'2022-08-12',3,'Java SE'),
(1,'2022-07-10',6,'Spring and Angular'),
(2,'2022-01-11',4,'Flutter');