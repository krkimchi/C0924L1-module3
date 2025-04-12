create database if not exists furama_resort;
use furama_resort;

create table rent_type (
    rent_type_id int primary key auto_increment,
    rent_type_name varchar(45) not null
);

create table service_type (
    service_type_id int primary key auto_increment,
    service_type_name varchar(45) not null
);

create table service (
    service_id int primary key auto_increment,
    service_name varchar(45) not null,
    service_area int,
    service_cost double not null,
    service_max_people int,
    rent_type_id int,
    service_type_id int,
    standard_room varchar(45),
    description_other_convenience varchar(45),
    pool_area double,
    number_of_floors int,
    free_service varchar(45), 
    foreign key (rent_type_id) references rent_type(rent_type_id),
    foreign key (service_type_id) references service_type(service_type_id)
);

create table attach_service (
    attach_service_id int primary key auto_increment,
    attach_service_name varchar(45) not null,
    attach_service_cost double not null,
    attach_service_unit varchar(45) 
);

create table education_degree (
    education_degree_id int primary key auto_increment,
    education_degree_name varchar(45) not null
);

create table employee_position (
    position_id int primary key auto_increment,
    position_name varchar(45) not null
);

create table division (
    division_id int primary key auto_increment,
    division_name varchar(45) not null 
);

create table user (
    username varchar(255) primary key,
    password varchar(255) not null,
    full_name varchar(45),
    type varchar(20) not null default 'customer'
);

create table role (
    role_id int primary key auto_increment,
    role_name varchar(255) not null
);

create table user_role (
    role_id int,
    username varchar(255),
    primary key (role_id, username),
    foreign key (role_id) references role(role_id),
    foreign key (username) references user(username)
);

create table employee (
    employee_id int primary key auto_increment,
    employee_name varchar(45) not null,
    employee_birthday date,
    employee_id_card varchar(45),
    employee_phone varchar(45),
    employee_email varchar(45),
    employee_salary double,
    education_degree_id int,
    position_id int,
    division_id int,
    username varchar(255),
    foreign key (education_degree_id) references education_degree(education_degree_id),
    foreign key (position_id) references employee_position(position_id),
    foreign key (division_id) references division(division_id),
    foreign key (username) references user(username)
);

create table customer_type (
    customer_type_id int primary key auto_increment,
    customer_type_name varchar(45) not null
);

create table customer (
    username varchar(255),
    customer_id int primary key auto_increment,
    customer_type_id int,
    customer_name varchar(45) not null,
    customer_birthday date,
    customer_gender bit(1),
    customer_id_card varchar(45),
    customer_phone varchar(45),
    customer_email varchar(45),
    customer_address varchar(45),
    foreign key (customer_type_id) references customer_type(customer_type_id),
    foreign key (username) references user(username)
);

create table contract (
    contract_id int primary key auto_increment,
    contract_start_date datetime not null,
    contract_end_date datetime not null,
    contract_deposit double,
    contract_total_money double,
    employee_id int,
    customer_id int,
    service_id int,
    foreign key (employee_id) references employee(employee_id),
    foreign key (customer_id) references customer(customer_id),
    foreign key (service_id) references service(service_id)
);

create table contract_detail (
    contract_detail_id int primary key auto_increment,
    contract_id int,
    attach_service_id int,
    quantity int,
    foreign key (contract_id) references contract(contract_id),
    foreign key (attach_service_id) references attach_service(attach_service_id)
);
