create database if not exists library_management;
use library_management;

create table books (
   book_id int auto_increment primary key,
   book_name varchar(255) not null,
   author varchar(255) not null,
   description text,
   quantity int not null
);

create table students (
   student_id int auto_increment primary key,
   full_name varchar(255) not null,
   class varchar(50) not null
);

create table book_loans (
   loan_id int auto_increment primary key,
   loan_code varchar(10) not null unique,
   book_id int,
   student_id int,
   status boolean,
   loan_date date not null,
   return_date date,
   foreign key (book_id) references books(book_id),
   foreign key (student_id) references students(student_id)
);



