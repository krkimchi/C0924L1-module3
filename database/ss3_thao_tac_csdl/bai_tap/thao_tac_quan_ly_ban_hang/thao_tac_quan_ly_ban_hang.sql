create database if not exists thao_tac_quan_ly_ban_hang;
use thao_tac_quan_ly_ban_hang;

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

-- Chèn dữ liệu vào bảng customers
insert into customers (customer_id, customer_name, customer_age) values
(1, 'Minh Quan', 10),
(2, 'Ngoc Oanh', 20),
(3, 'Hong Ha', 50);

-- Chèn dữ liệu vào bảng products
insert into products (product_id, product_name, product_price) values
(1, 'May Giat', 3),
(2, 'Tu Lanh', 5),
(3, 'Dieu Hoa', 7),
(4, 'Quat', 1),
(5, 'Bep Dien', 2);

-- Chèn dữ liệu vào bảng orders
insert into orders (order_id, customer_id, order_date, total_price) values
(1, 1, '2006-03-21', null),
(2, 2, '2006-03-23', null),
(3, 1, '2006-03-16', null);

-- Chèn dữ liệu vào bảng order_details
insert into order_details (order_id, product_id, order_quantity) values
(1, 1, 3),
(1, 3, 7),
(1, 4, 2),
(2, 1, 1),
(3, 1, 8),
(2, 5, 4),
(2, 3, 3);

-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order--
select o.order_id as oid, o.order_date as odate, sum(od.order_quantity * p.product_price) as oprice 
from orders o
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id
group by o.order_id, o.order_date;

-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách -- 
select c.customer_name, p.product_name, od.order_quantity from customers c
join orders o on c.customer_id = o.customer_id
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id;

-- Hiển thị tên những khách hàng không mua bất kỳ sản phẩm nào -- 
select c.customer_name from customers c
left join orders o on c.customer_id = o.customer_id
left join order_details od on o.order_id = od.order_id
where od.order_id is null;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được tính = odQTY*pPrice) -- 
select o.order_id as oid, o.order_date as odate, sum(od.order_quantity * p.product_price) as oprice
from orders o
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id
group by o.order_id, o.order_date;
