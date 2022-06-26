drop database store;
CREATE DATABASE store;
USE store;

SET SESSION sql_mode = '';
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS; 
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE Account_User
(
 customer_id int AUTO_INCREMENT PRIMARY KEY,
 username    varchar(50) NOT NULL UNIQUE KEY,
 password    varchar(50) NOT NULL
) AUTO_INCREMENT = 1 COMMENT = 'Basic information about Users';
SET FOREIGN_KEY_CHECKS=0;   
drop table Account_Company;
SET FOREIGN_KEY_CHECKS=1;
select * from Account_Company;

update account_user set password = 'durvish17231' where customer_id = 1;

CREATE TABLE Account_Supplier
(
 supplier_id int AUTO_INCREMENT PRIMARY KEY,
 username varchar(50) NOT NULL UNIQUE KEY,
 password varchar(50) NOT NULL ,
 rating float8 NOT NULL -- out of 5
) AUTO_INCREMENT = 1 COMMENT = 'Basic information about Supplier';


CREATE TABLE Account_Company
(
 company_id int AUTO_INCREMENT PRIMARY KEY,
 username    varchar(50) NOT NULL UNIQUE KEY,
 password    varchar(50) NOT NULL,
 brand    varchar(50) NOT NULL
) AUTO_INCREMENT = 1 COMMENT = 'Basic information about Company Officials';


CREATE TABLE Product_Type
(
 product_id int AUTO_INCREMENT PRIMARY KEY,
 product_name varchar(200) NOT NULL,
 price int NOT NULL ,
 brand_name varchar(50) NOT NULL ,
 type_1 varchar(50) NOT NULL ,
 colour varchar(50) NOT NULL ,
 rating float8 NOT NULL ,
 supplier_id  int NOT NULL ,

-- UNIQUE KEY AK1_Customer_CustomerName (product_name),
KEY FK_Id_Product_Type_supplier_id (supplier_id),
CONSTRAINT FK_Product_Type_supplier_id FOREIGN KEY FK_Id_Product_Type_supplier_id (supplier_id) REFERENCES Account_Supplier (supplier_id)
) AUTO_INCREMENT=1 COMMENT='Keeps a general record of the Product_Type';
select * from Product_Type;

CREATE TABLE Requests
(
 listing int AUTO_INCREMENT PRIMARY KEY,
 customer_id int NOT NULL ,
 supplier_id int NOT NULL ,	
 product_id  int NOT NULL ,
 quantity    int NOT NULL ,
 date_order  date NOT NULL ,

CONSTRAINT FK_Request_customer_id FOREIGN KEY FK_Id_Request_customer_id (customer_id) REFERENCES Account_user (customer_id),
KEY FK_Id_Request_supplier_id (supplier_id),
CONSTRAINT FK_Request_supplier_id FOREIGN KEY FK_Id_Request_supplier_id (supplier_id) REFERENCES Account_supplier (supplier_id),
KEY FK_Id_Request_product_id (product_id),
CONSTRAINT FK_Request_product_id FOREIGN KEY FK_Id_Request_product_id (product_id) REFERENCES Product_type (product_id)
) AUTO_INCREMENT=1 COMMENT = 'Keeps a record of the Pending Request to the Supplier';

CREATE TABLE Inventory
(
 product_id int NOT NULL PRIMARY KEY,
 quantity int NOT NULL ,
 supplier_id int NOT NULL ,

KEY FK_Id_Inventory_product_id (product_id),
CONSTRAINT FK_Inventory_product_id FOREIGN KEY FK_Id_Inventory_product_id (product_id) REFERENCES Product_Type (product_id) ON DELETE CASCADE,
KEY FK_Id_Inventory_supplier_id (supplier_id),
CONSTRAINT FK_Inventory_supplier_id FOREIGN KEY FK_Id_Inventory_supplier_id (supplier_id) REFERENCES Account_Supplier (supplier_id) ON DELETE CASCADE
) COMMENT='Basic information about Inventory i.e. The Stock available for any product in the store' ;
-- ALTER TABLE ProduInventoryct_Type ADD CONSTRAINT `appointments_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ;


CREATE TABLE Contact
(
 phone_number bigint NOT NULL ,
 email varchar(50) NOT NULL ,
 locality varchar(50) NOT NULL ,
 pincode int NOT NULL ,
 city varchar(50) NOT NULL ,
 state varchar(50) NOT NULL ,
 country varchar(50) NOT NULL ,
 customer_id int ,
 supplier_id int ,

CONSTRAINT PK_Contact PRIMARY KEY (phone_number, email),
KEY FK_Id_Contact_customer_id (customer_id),
CONSTRAINT FK_Contact_customer_id FOREIGN KEY FK_Id_Contact_customer_id (customer_id) REFERENCES Account_User (customer_id) ON DELETE CASCADE,
KEY FK_Id_Contact_supplier_id (supplier_id),
CONSTRAINT FK_Contact_supplier_id FOREIGN KEY FK_Id_Contact_supplier_id (supplier_id) REFERENCES Account_Supplier (supplier_id) ON DELETE CASCADE
) COMMENT='Contact Information of different suppliers' ;

CREATE TABLE Checkout
(
 listing int AUTO_INCREMENT PRIMARY KEY ,
 customer_id int NOT NULL ,
 quantity int NOT NULL ,
 product_id int NOT NULL ,

CONSTRAINT FK_Checkout_customer_id FOREIGN KEY FK_Id_Checkout_customer_id (customer_id) REFERENCES Account_User (customer_id),
KEY FK_Id_Checkout_product_id (product_id),
CONSTRAINT FK_Checkout_product_id FOREIGN KEY FK_Id_Checkout_product_id (product_id) REFERENCES Product_Type (product_id)
) AUTO_INCREMENT = 1 COMMENT='Keeps a record of the Checkout Products' ;

CREATE TABLE Camera
(
 product_id int NOT NULL PRIMARY KEY,
 type varchar(50) NOT NULL,
 resolution varchar(20) NOT NULL ,
 
KEY FK_Id_Camera_product_id (product_id),
CONSTRAINT FK_Camera_product_id FOREIGN KEY FK_Id_Camera_product_id (product_id) REFERENCES Product_Type (product_id) ON DELETE CASCADE
) COMMENT = 'Basic information about Camera Type Product';


CREATE TABLE Headphones
(
 type varchar(20) NOT NULL ,
 product_id int NOT NULL PRIMARY KEY,

KEY FK_Id_Headphone_product_id (product_id),
CONSTRAINT FK_Headphone_product_id FOREIGN KEY FK_Id_Headphone_product_id (product_id) REFERENCES Product_Type (product_id) ON DELETE CASCADE
) COMMENT='Basic information about Headphones' ;


CREATE TABLE Keyboards_Mouse
(
 product_id int NOT NULL PRIMARY KEY,
 connectivity_type varchar(20) NOT NULL ,
 backlight varchar(20),
 button_type varchar(20),
 
KEY FK_Id_Keyboards_Mouse_product_id (product_id),
CONSTRAINT FK_Keyboards_Mouse_product_id FOREIGN KEY FK_Id_Keyboards_Mouse_product_id (product_id) REFERENCES Product_Type (product_id) ON DELETE CASCADE
) COMMENT='Basic information about Keyboard and Mouse' ;


CREATE TABLE Mobiles
(
 product_id int NOT NULL PRIMARY KEY,
 os varchar(20) NOT NULL,
 primary_camera varchar(50) NOT NULL ,
 processor varchar(50) NOT NULL ,
 screen_size varchar(50) NOT NULL ,
 battery varchar(50) NOT NULL ,
 ram varchar(50) NOT NULL ,
 
-- UNIQUE KEY AK1_Supplier_CompanyName (OS),
KEY FK_Id_Mobiles_product_id (product_id),
CONSTRAINT FK_Mobiles_product_id FOREIGN KEY FK_Id_Mobiles_product_id (product_id) REFERENCES Product_Type (product_id) ON DELETE CASCADE
) COMMENT='Basic information about Mobile Phones';
select * from product_type;
delete from product_type where product_id = 7107;

CREATE TABLE Laptop_Pc
(
 product_id int NOT NULL PRIMARY KEY,
 os varchar(20) NOT NULL ,
 processor varchar(50) NOT NULL ,
 harddisk_type varchar(20) NOT NULL ,
 harddisk_cap varchar(20) NOT NULL ,
 type varchar(20) NOT NULL ,
 ram varchar(20) NOT NULL ,
 screen_size varchar(20) NOT NULL ,

-- UNIQUE KEY AK1_Order_OrderNumber (os),
KEY FK_Id_Laptop_Pc_product_id (product_id),
CONSTRAINT FK_Laptop_Pc_product_id FOREIGN KEY FK_Id_Laptop_Pc_product_id (product_id) REFERENCES Product_Type (product_id) ON DELETE CASCADE
) COMMENT='Laptops and PCs Product Information';


CREATE TABLE Order_History
(
 order_id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 customer_id int NOT NULL ,
 supplier_id int NOT NULL,
 date_order date NOT NULL ,
 time time NOT NULL,
 product_name varchar(50) NOT NULL ,
 product_id int NOT NULL ,
 quantity int NOT NULL,
 delivery_date date NOT NULL ,
 return_status varchar(50) NOT NULL ,
 

KEY FK_Id_Order_History_customer_id (customer_id),
CONSTRAINT FK_Order_History_customer_id FOREIGN KEY FK_Id_Order_History_customer_id (customer_id) REFERENCES Account_user (customer_id),
KEY FK_Id_Order_History_product_id (product_id),
CONSTRAINT FK_Order_History_product_id FOREIGN KEY FK_Id_Order_History_product_id (product_id) REFERENCES Product_type (product_id)
) AUTO_INCREMENT =  1 COMMENT = 'Keeps a record of a user\' orders ';
drop table Order_History;
select * from Order_History;
-- update order_history set return_status = 'Delivered' where customer_id = 1 and date_order = '2020-04-26';

CREATE TABLE Televisions
(
 product_id  int NOT NULL PRIMARY KEY,
 type varchar(20) NOT NULL ,
 screen_size varchar(20) NOT NULL ,
 isSmart varchar(20) NOT NULL ,
 resolution varchar(20) NOT NULL ,
 
KEY FK_Id_Televisions_product_id (product_id),
CONSTRAINT FK_Televisions_product_id FOREIGN KEY FK_Id_Televisions_product_id (product_id) REFERENCES Product_type (product_id) ON DELETE CASCADE
) COMMENT='Information about Televisions';
-- select * from Televisions;

-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Account_user.csv"
-- INTO TABLE Account_User 
-- FIELDS TERMINATED BY ','
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;


-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Account_supplier.csv"
-- INTO TABLE Account_Supplier 
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;


-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Account_company.csv"
-- INTO TABLE Account_company 
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/product_type.csv"
-- INTO TABLE Product_type
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;



-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Camera.csv"
-- INTO TABLE camera
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Televisions.csv"
-- INTO TABLE Televisions
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;


-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/keyboards_mouse.csv"
-- INTO TABLE Keyboards_Mouse
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Laptop.csv"
-- INTO TABLE Laptop_pc
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/headphones.csv"
-- INTO TABLE headphones
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/mobiles.csv"
-- INTO TABLE mobiles
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;


-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/inventory.csv"
-- INTO TABLE Inventory
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"' 
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;


-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/request.csv"
-- INTO TABLE requests
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"' 
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

-- LOAD DATA INFILE "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/contact.csv"
-- INTO TABLE Contact
-- FIELDS TERMINATED BY ',' 
-- ENCLOSED BY '"' 
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;


#drop trigger del_prod;

DELIMITER $$
create trigger del_prod
after Delete on Product_type
for each row
begin
delete from Camera where Camera.product_id = OLD.product_id;
delete from Keyboards_mouse where Keyboards_mouse.product_id = OLD.product_id;
delete from Laptop_pc where Laptop_pc.product_id = OLD.product_id;
delete from Mobiles where Mobiles.product_id = OLD.product_id;
delete from Headphones where Headphones.product_id = OLD.product_id;
delete from Televisions where Televisions.product_id = OLD.product_id;
delete from inventory where inventory.product_id = OLD.product_id;
end $$

DELIMITER ;

DELIMITER $$
create trigger supplier_update
after delete on account_supplier
for each row
begin
delete from  product_type where product_type.supplier_id = OLD.supplier_id;
delete from inventory where inventory.supplier_id = OLD.supplier_id;
end $$
DELIMITER ;


create index prod_price
on Product_type(price);

create index id_search
on Product_type(product_id);

create index cust_search
on account_user(customer_id);

create index supplier_search
on account_supplier(supplier_id);

create index inventory_search
on Inventory(product_id, supplier_id);

insert into account_user(username, password) values ('bhu', 'bhuv');

 #query 1
 select * from requests
 inner join product_type
 on requests.product_id = product_type.product_id
 where product_type.type_1 = 'Mobiles' or product_type.type_1 = 'television';

#query 2
select * from requests 
 inner join product_type
 on requests.product_id = product_type.product_id
where product_type.type_1 = 'camera' and quantity = (
 select max(quantity) from requests
 inner join product_type
 on requests.product_id = product_type.product_id
 where product_type.type_1 = 'camera');

#query 3
select supplier_id, username, rating from Account_Supplier
order by rating;

#query 4
select Account_Supplier.supplier_id, username, Account_Supplier.rating from Account_Supplier
inner join product_type
on Account_Supplier.supplier_id = product_type.supplier_id
where product_type.type_1='mobiles' and Account_Supplier.rating = (
select max(Account_Supplier.rating) from Account_Supplier
inner join product_type
on Account_Supplier.supplier_id = product_type.supplier_id
where product_type.type_1='mobiles');

#query 5
select * from product_type
natural join mobiles
where product_type.brand_name = 'acer'; 

#query 6 - List the Mobile Phones from alcatel in ascending order of their price
select * from product_type
natural join mobiles
where product_type.brand_name = 'alcatel'
order by product_type.price; 

#query 7 - list all orders which were delivered on 20th april 2020
select * from order_history
where delivery_date = '2020-04-20';

#query 8 - Stop producing hence Remove the product, that has been ordered less than a piece in a month?

-- select * from prod;

#query 9 - Insert a new mobile
insert into product_type(product_name, price, brand_name, type_1, colour, rating , supplier_id) values ('Iphone XS+', 90000, 'Apple', 'mobile', 'Black', 4, 26);
insert into mobiles values ((select product_id from product_type where product_name = 'Iphone XS+' and supplier_id = 26 and colour = 'Black'), 'ios', '12mp triple camera setup', 'A13 bionic chip', '5.5 inch', '3100 mah', '4GB');
insert into inventory values ((select product_id from product_type where product_name = 'Iphone XS+' and supplier_id = 26 and colour = 'Black'), 0, 26);

-- 

#query 10 - List all the free suppliers' info i.e. having no pending orders that are ready to deliver the goods?
create view supplier_info as
select supplier_id, username, rating
from account_supplier;
select * from supplier_info
where supplier_id not in (select supplier_id from requests);


#query 11 - update the price of mobiles by giving a 40% discount
SET SQL_SAFE_UPDATES = 0;
update product_type
set price = price* 0.6
where type_1 = 'mobile';
SET SQL_SAFE_UPDATES = 1;


#query 12 - print products that are out of stock
select * from product_type
where product_id = (select product_id from inventory where quantity=0);

#query 13 - list suppliers that supply samsung mobiles
select * from supplier_info
where supplier_id  = (
select supplier_id from product_type 
where type_1 = 'mobile' and brand_name = 'samsung'); 

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SHOW VARIABLES LIKE "secure_file_priv";
select * from Account_User;
select * from Account_Supplier;
select * from Account_Company;
select * from product_type;
select * from inventory;
select P.*,I.quantity from product_type P, inventory I where P.product_id = I.product_id and type_1 in (select (P.type_1) from product_type P,order_history O where P.product_id = O.product_id and customer_id = 1) order by rand() LIMIT 10;
select supplier_id from product_type where brand_name = 'alcatel';

SET SQL_SAFE_UPDATES = 0;
delete from product_type where product_id not in (select product_id from order_history where delivery_date > '2020-03-24');
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
delete from product_type where product_id = 7106;
SET SQL_SAFE_UPDATES = 1;


select * from product_type;
select * from inventory;
select * from Mobiles;

select * from order_history;
select product_id from order_history where delivery_date > '2020-03-24';
delete from product_type where product_id in (select product_id from order_history where delivery_date > '2020-03-24');

select * from televisions;
select * from requests;
select B.customer_id,B.product_id,sum(B.quantity) as required,A.quantity as available from inventory as A ,checkout as B where A.product_id=B.product_id and B.customer_id = "+customerid+" group by(B.product_id);
select A.*,B.quantity from Product_Type A, Inventory B where A.product_id = B.product_id;

insert into product_type(product_name, price, brand_name, type_1, colour, rating , supplier_id) values ('Apple TV 4K UHD', 45000, 'Apple', 'televisions', 'Black', 4, 10);
insert into televisions values ((select product_id from product_type where product_name = 'Apple TV 4K UHD' and supplier_id = 10 and colour = 'Black'), 'Ultra UHD', '56', 'Yes', '4K');
insert into inventory values ((select product_id from product_type where product_name = 'Apple TV 4K UHD' and supplier_id = 10 and colour = 'Black'), 7, 10); 

-- insert into mobiles values ((select product_id from product_type where product_name = 'Apple TV 4K UHD' and supplier_id = 10 and colour = 'Black'), 'ios', '12mp triple camera setup', 'A13 bionic chip', '5.5 inch', '3100 mah', '4GB');
insert into inventory values ((select product_id from product_type where product_name = 'Iphone XS+' and supplier_id = 26 and colour = 'Black'), 0, 26); 
insert into televisions values ((select product_id from product_type where product_name = 'Apple TV 4K UHD' and supplier_id = 20 and colour = 'White'), 'Ultra UHD', '56', 'Yes', '4K');

select * from account_user;
