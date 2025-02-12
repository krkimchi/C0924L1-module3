create database if not exists query_student_management;
use query_student_management;

create table class (
    id int primary key auto_increment,
    name varchar(100) not null,
    start_date date,
    status int
);

create table teacher (
     id int primary key auto_increment,
     name varchar(100) not null,
     age int not null check(age > 0),
     country varchar(100) not null
);

create table student (
    id int primary key auto_increment,
    name varchar(100) not null,
    address varchar(255),
    phone varchar(15),
    status int,
    class_id int,
    foreign key (class_id) references class(id)
);

create table subject (
    id int primary key auto_increment,
    name varchar(100) not null,
    credit int not null,
    status int
);

create table mark (
    id int primary key auto_increment,
    subject_id int,
    student_id int,
    mark int,
    exam_times int,
    foreign key (subject_id) references subject(id),
    foreign key (student_id) references student(id)
);

insert into class (name, start_date, status) values
('a1', '2008-12-20', 1),
('a2', '2008-12-22', 1),
('b3', curdate(), 0);

insert into student (name, address, phone, status, class_id) values
('hung', 'ha noi', '0912113113', 1, 1),
('hoa', 'hai phong', '', 1, 1),
('manh', 'hcm', '0123123123', 0, 2);

insert into subject (name, credit, status) values
('cf', 5, 1),
('c', 6, 1),
('hdj', 5, 1),
('rdbms', 10, 1);

insert into mark (subject_id, student_id, mark, exam_times) values
(1, 1, 8, 1),
(1, 2, 10, 2),
(2, 3, 12, 1);