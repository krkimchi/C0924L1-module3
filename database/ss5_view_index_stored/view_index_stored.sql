create database if not exists view_index_stored;
use view_index_stored;

create table products (
    id int auto_increment primary key,
    product_code varchar(50) unique,
    product_name varchar(100),
    product_price decimal(10,2),
    product_amount int,
    product_description text,
    product_status enum('active', 'inactive') default 'active'
);

-- chèn dữ liệu mẫu --
insert into products (product_code, product_name, product_price, product_amount, product_description, product_status)
values
('p001', 'laptop dell', 1500.00, 10, 'laptop dell core i7', 'active'),
('p002', 'laptop hp', 1400.00, 5, 'laptop hp core i5', 'active'),
('p003', 'macbook pro', 2000.00, 3, 'macbook pro m2', 'inactive');

-- tạo unique index trên bảng products --
create unique index idx_product_code on products(product_code);

-- tạo composite index trên bảng products --
create index idx_productname_price on products(product_name, product_price);

-- sử dụng câu lệnh explain để biết được câu lệnh sql thực thi như nào -- 
explain select * from products where product_code = 'p001';

-- so sánh câu truy vấn trước và sau khi tạo index --
explain select * from products where product_name = 'laptop dell' and product_price = 1500.00;

-- tạo view -- 
create view product_view as
select product_code, product_name, product_price, product_status
from products;

-- sửa đổi view (drop và tạo lại) --
drop view if exists product_view;

create view product_view as
select product_code, product_name, product_price, product_status, product_amount
from products;

-- xoá view -- 
drop view if exists product_view;

-- tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng products -- 
delimiter //
create procedure get_all_products()
begin
    select * from products;
end //
delimiter ;

call get_all_products();

-- tạo store procedure thêm một sản phẩm mới -- 
delimiter //
create procedure add_product(
    in p_code varchar(50),
    in p_name varchar(100),
    in p_price decimal(10,2),
    in p_amount int,
    in p_description text,
    in p_status varchar(10)
)
begin
    insert into products (product_code, product_name, product_price, product_amount, product_description, product_status)
    values (p_code, p_name, p_price, p_amount, p_description, p_status);
end //
delimiter ;

call add_product('p004', 'asus rog', 1800.00, 7, 'gaming laptop', 'active');

-- tạo store procedure sửa thông tin sản phẩm theo id -- 
delimiter //
create procedure update_product(
    in p_id int,
    in p_name varchar(100),
    in p_price decimal(10,2),
    in p_amount int,
    in p_description text,
    in p_status varchar(10)
)
begin
    update products
    set product_name = p_name,
        product_price = p_price,
        product_amount = p_amount,
        product_description = p_description,
        product_status = p_status
    where id = p_id;
end //
delimiter ;

call update_product(1, 'laptop dell xps', 1600.00, 8, 'dell xps 13', 'active');

-- tạo store procedure xoá sản phẩm theo id --
delimiter //
create procedure delete_product(
    in p_id int
)
begin
    delete from products where id = p_id;
end //
delimiter ;

call delete_product(3);
