create database if not exists quan_ly_ban_hang;
use quan_ly_ban_hang;

create table customers (
  customer_id int primary key auto_increment,
  customer_name varchar(100) not null,
  customer_age int
);

create table products (
  product_id int primary key auto_increment,
  product_name varchar(100) not null,
  product_price decimal(10,2) not null
);

create table orders (
  order_id int primary key auto_increment,
  customer_id int,
  order_date date not null,
  foreign key (customer_id) references customers(customer_id)
);

create table order_details (
  order_details_id int auto_increment,
  order_id int,
  product_id int,
  order_quantity int not null,
  primary key (order_details_id),
  foreign key (order_id) references orders(order_id),
  foreign key (product_id) references products(product_id)
);
