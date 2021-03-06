CREATE DATABASE store;
USE store;

SET SQL_SAFE_UPDATES = 0;
delete from product_type where product_id not in ( select product_id from order_history where delivery_date > '2020-03-24'); 
SET SQL_SAFE_UPDATES = 1;

INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Nikon D850',198400,'Nikon','Camera','Black',5.0,1);
(select product_id from product_type where product_name = 'Iphone XS' and supplier_id = 1 and colour = 'Black');

Insert into product_type(product_name, price, brand_name, type_1, colour, rating , supplier_id) values ('Iphone XS', 90000, 'Apple', 'mobile', 'Black', 4.0, 1);Insert into mobiles(product_id,os,primary_camera,processor,screen_size,battery,ram) values ((select product_id from product_type where product_name = 'Iphone XS' and supplier_id = 1 and colour = 'Black'), 'IOS', '12mp 3x Camera', 'A13 bionic chip', '5.5 inch', '3100 mah', '4GB');Insert into inventory values ((select product_id from product_type where product_name = 'Iphone XS' and supplier_id = 1 and colour = 'Black'), 0, 1);

select * from product_type where type_1 = 'Mobile';
Update product_type set price = price* 0.6 where type_1 = 'mobile';

select product_id from order_history where delivery_date > '2020-03-24';
SET SQL_SAFE_UPDATES = 0;

SET SQL_SAFE_UPDATES = 1;

drop view supplier_info;

create view supplier_info as
select supplier_id, username, rating
from account_supplier;
select * from supplier_info
where supplier_id not in (select supplier_id from requests);

select * from Product_type;

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

DELIMITER $$

Delete from product_type where product_id not in (select product_id from order_history where delivery_date > '2020-03-24');


CALL del_prod;

CREATE TABLE Account_Company
(
 company_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username    varchar(50) NOT NULL UNIQUE KEY,
 password    varchar(50) NOT NULL,
 brand    varchar(50) NOT NULL
) AUTO_INCREMENT = 1 COMMENT = 'Basic information about Company Officials';

INSERT INTO Account_Company (username,password,brand) VALUES ('Tim','timcook','Apple');
INSERT INTO Account_Company (username,password,brand) VALUES ('Samson','samsonhu','ASUS');
INSERT INTO Account_Company (username,password,brand) VALUES ('Conway','conwaylee','BenQ');
INSERT INTO Account_Company (username,password,brand) VALUES ('Edgar','edgarvelzen','BEYERDYNAMIC');
INSERT INTO Account_Company (username,password,brand) VALUES ('Phil','philhess','Bose');

SHOW VARIABLES LIKE "secure_file_priv";

select * from Account_User;

select distinct(brand_name) from product_type;

CREATE TABLE Account_User
(
 customer_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username    varchar(50) NOT NULL UNIQUE KEY,
 password    varchar(50) NOT NULL
) AUTO_INCREMENT = 1 COMMENT = 'Basic information about Users';

select * from Account_User;
update Account_User set password = "durvish17231" where customer_id = 1;

CREATE TABLE Account_Supplier
(
 supplier_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username varchar(50) NOT NULL UNIQUE KEY,
 password varchar(50) NOT NULL ,
 rating float8 NOT NULL -- out of 5
) AUTO_INCREMENT = 1 COMMENT = 'Basic information about Supplier';

CREATE TABLE Product_Type
(
 product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 product_name varchar(50) NOT NULL,
 price int NOT NULL ,
 brand_name varchar(50) NOT NULL ,
 type_1 varchar(20) NOT NULL ,
 colour varchar(20) NOT NULL ,
 rating float8 NOT NULL ,
 supplier_id  int NOT NULL ,

-- UNIQUE KEY AK1_Customer_CustomerName (product_name),
KEY FK_Id_Product_Type_supplier_id (supplier_id),
CONSTRAINT FK_Product_Type_supplier_id FOREIGN KEY FK_Id_Product_Type_supplier_id (supplier_id) REFERENCES Account_Supplier (supplier_id)
) AUTO_INCREMENT=1 COMMENT='Keeps a general record of the Product_Type';

CREATE TABLE Requests
(
 
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
) COMMENT = 'Keeps a record of the Pending Request to the Supplier';

drop table requests;
select * from Requests;
CREATE TABLE Inventory
(
 product_id int NOT NULL PRIMARY KEY,
 quantity int NOT NULL ,
 supplier_id int NOT NULL ,

KEY FK_Id_Inventory_product_id (product_id),
CONSTRAINT FK_Inventory_product_id FOREIGN KEY FK_Id_Inventory_product_id (product_id) REFERENCES Product_Type (product_id),
KEY FK_Id_Inventory_supplier_id (supplier_id),
CONSTRAINT FK_Inventory_supplier_id FOREIGN KEY FK_Id_Inventory_supplier_id (supplier_id) REFERENCES Account_Supplier (supplier_id)
) COMMENT='Basic information about Inventory i.e. The Stock available for any product in the store' ;

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
 -- flag int NOT NULL, -- It is 1 for customer and 0 for supplier

CONSTRAINT PK_Contact PRIMARY KEY (phone_number, email),
KEY FK_Id_Contact_customer_id (customer_id),
CONSTRAINT FK_Contact_customer_id FOREIGN KEY FK_Id_Contact_customer_id (customer_id) REFERENCES Account_User (customer_id),
KEY FK_Id_Contact_supplier_id (supplier_id),
CONSTRAINT FK_Contact_supplier_id FOREIGN KEY FK_Id_Contact_supplier_id (supplier_id) REFERENCES Account_Supplier (supplier_id)
) COMMENT='Contact Information of different suppliers' ;

drop table contact;
select * from contact;

CREATE TABLE Checkout
(
 listing int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 customer_id int NOT NULL ,
 quantity int NOT NULL ,
 product_id int NOT NULL ,

CONSTRAINT FK_Checkout_customer_id FOREIGN KEY FK_Id_Checkout_customer_id (customer_id) REFERENCES Account_User (customer_id),
KEY FK_Id_Checkout_product_id (product_id),
CONSTRAINT FK_Checkout_product_id FOREIGN KEY FK_Id_Checkout_product_id (product_id) REFERENCES Product_Type (product_id)
) AUTO_INCREMENT = 1 COMMENT='Keeps a record of the Checkout Products' ;
 select * from account_user;
select * from Request;

drop table Checkout;

CREATE TABLE Camera
(
 product_id int NOT NULL PRIMARY KEY,
 type varchar(50) NOT NULL,
 resolution varchar(20) NOT NULL ,
 
KEY FK_Id_Camera_product_id (product_id),
CONSTRAINT FK_Camera_product_id FOREIGN KEY FK_Id_Camera_product_id (product_id) REFERENCES Product_Type (product_id)
) COMMENT = 'Basic information about Camera Type Product';



CREATE TABLE Headphones
(
 type varchar(20) NOT NULL ,
 product_id int NOT NULL PRIMARY KEY,

KEY FK_Id_Headphone_product_id (product_id),
CONSTRAINT FK_Headphone_product_id FOREIGN KEY FK_Id_Headphone_product_id (product_id) REFERENCES Product_Type (product_id)
) COMMENT='Basic information about Headphones' ;

CREATE TABLE Keyboards_Mouse
(
 connectivity_type varchar(20) NOT NULL ,
 backlight varchar(20) NOT NULL ,
 button_type varchar(20) NOT NULL ,
 product_id int NOT NULL PRIMARY KEY,

KEY FK_Id_Keyboards_Mouse_product_id (product_id),
CONSTRAINT FK_Keyboards_Mouse_product_id FOREIGN KEY FK_Id_Keyboards_Mouse_product_id (product_id) REFERENCES Product_Type (product_id)
) COMMENT='Basic information about Keyboard and Mouse' ;

CREATE TABLE Mobiles
(
 product_id int NOT NULL PRIMARY KEY,
 os varchar(20) NOT NULL,
 primary_camera varchar(20) NOT NULL ,
 processor varchar(50) NOT NULL ,
 screen_size varchar(20) NOT NULL ,
 battery varchar(20) NOT NULL ,
 ram varchar(20) NOT NULL ,
 
-- UNIQUE KEY AK1_Supplier_CompanyName (OS),
KEY FK_Id_Mobiles_product_id (product_id),
CONSTRAINT FK_Mobiles_product_id FOREIGN KEY FK_Id_Mobiles_product_id (product_id) REFERENCES Product_Type (product_id)
) COMMENT='Basic information about Mobile Phones';

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
CONSTRAINT FK_Laptop_Pc_product_id FOREIGN KEY FK_Id_Laptop_Pc_product_id (product_id) REFERENCES Product_Type (product_id)
) COMMENT='Laptops and PCs Product Information';

CREATE TABLE Order_History
(
 order_id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 customer_id int NOT NULL ,
 date date NOT NULL ,
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

DROP table order_history;
select * from order_history;

CREATE TABLE Televisions
(
 product_id  int NOT NULL PRIMARY KEY,
 type varchar(20) NOT NULL ,
 screen_size varchar(20) NOT NULL ,
 isSmart bit(1) NOT NULL ,
 resolution varchar(20) NOT NULL ,
 
KEY FK_Id_Televisions_product_id (product_id),
CONSTRAINT FK_Televisions_product_id FOREIGN KEY FK_Id_Televisions_product_id (product_id) REFERENCES Product_type (product_id)
) COMMENT='Information about Televisions';



INSERT INTO Admin (username,password) VALUES ('root','root');
INSERT INTO Admin (username,password) VALUES ('market','market');

select * from Account_User;

INSERT INTO Account_User (username,password) VALUES ('Vikram','vikramgoyal');
INSERT INTO Account_User (username,password) VALUES ('Sneha','snehachaubey');
INSERT INTO Account_User (username,password) VALUES ('Black Widow','blackwidow');
INSERT INTO Account_User (username,password) VALUES ('Captain America','captainamerica');
INSERT INTO Account_User (username,password) VALUES ('Iron Man','ironman');
INSERT INTO Account_User (username,password) VALUES ('Thor','thor');
INSERT INTO Account_User (username,password) VALUES ('Ant Man','antman');
INSERT INTO Account_User (username,password) VALUES ('Black Panther','blackpanther');
INSERT INTO Account_User (username,password) VALUES ('Pepper Potts','pepperpotts');
INSERT INTO Account_User (username,password) VALUES ('Super Man','superman');
INSERT INTO Account_User (username,password) VALUES ('Mei Kang','meikang');

select * from order_history ;

INSERT INTO Account_Supplier (username,password,rating) VALUES ('madeforcustomer','madeforcustomer',-1);
INSERT INTO Account_Supplier (username,password) VALUES ('market','market');
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Aman','amanparnami',4.2);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Vaibhav','vaibhavjayant',2.8);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Nakul','nakulgupta',5.0);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Shivam','shivamshanker',3.2);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Pranjal','pranjalkaura',4.4);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Rishi','rishisharma',5.0);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Garima','garimagautam',4.4);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Isha','ishagautam',3.9);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Chris','chrisanthony',4.0);
INSERT INTO Account_Supplier (username, password,rating) VALUES ('Steve','stevejobs',3.0);
delete from Account_Supplier where username = 'madeforcustomer';


select * from inventory;
delete from product_type where product_id = 62;
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Nikon D850',198400,'Nikon','Camera','Black',5.0,1);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Canon EOS 5D Mark IV',194237,'Canon','Camera','Black',4.5,9);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Nikon D500',121400,'Nikon','Camera','Black',5.0,3);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Nikon D7500',66890,'Nikon','Camera','Black',4.5,7);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Canon EOS 200D II',46980,'Canon','Camera','Black',4.7,5);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Nikon D3400',26490,'Nikon','Camera','Black',4.2,6);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Canon EOS 800D',26490,'Canon','Camera','Black',5.0,4);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Nikon D750',98499,'Nikon','Camera','Black',5.0,8);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Canon EOS 7D Mark II',106546,'Canon','Camera','Black',5.0,2);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Pentax K-1',198400,'Pentax','Camera','Black',3.8,10);

INSERT INTO Camera (product_id,type,resolution) VALUES (1,'Medium Format Camera','7680??4320');
INSERT INTO Camera (product_id,type,resolution) VALUES (2,'DSLR','7680??4320');
INSERT INTO Camera (product_id,type,resolution) VALUES (3,'Compact Mirrorless Camera','3840x2160');
INSERT INTO Camera (product_id,type,resolution) VALUES (4,'Medium Format Camera','7680??4320');
INSERT INTO Camera (product_id,type,resolution) VALUES (5,'Medium Format Camera','3840x2160');
INSERT INTO Camera (product_id,type,resolution) VALUES (6,'Compact Mirrorless Camera','3840x2160');
INSERT INTO Camera (product_id,type,resolution) VALUES (7,'DSLR','7680??4320');
INSERT INTO Camera (product_id,type,resolution) VALUES (8,'Medium Format Camera','7680??4320');
INSERT INTO Camera (product_id,type,resolution) VALUES (9,'Medium Format Camera','7680??4320');
INSERT INTO Camera (product_id,type,resolution) VALUES (10,'DSLR','7680??4320');

INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('BOSE QUIETCOMFORT 25',20150,'Bose','Headphone','Black',4.0,1);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('SENNHEISER MOMENTUM 2.0',15999,'SENNHEISER','Headphone','White',4.0,3);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('BEYERDYNAMIC MMX 102IE',3840,'BEYERDYNAMIC','Headphone','Silver',4.0,5);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('SONY MDR-AS800AP',12282,'SONY','Headphone','Black',4.0,7);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('PLANTRONICS BACKBEAT SENSE',10999,'PLANTRONICS','Headphone','Red',4.0,9);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('RHA S500I',6211,'RHA','Headphone','White',4.0,10);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('JABRA UC VOICE 550',5089,'JABRA','Headphone','White',4.0,8);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('SOUNDMAGIC E10S',2199,'SOUNDMAGIC','Headphone','Black',4.0,6);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('BRAINWAVZ OMEGA',999,'BRAINWAVZ','Headphone','Silver',4.0,4);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('PANASONIC RP-HXD3W',1999,'PANASONIC','Headphone','Black',4.0,2);

INSERT INTO Headphones (type,product_id) VALUES ('Wireless',11);
INSERT INTO Headphones (type,product_id) VALUES ('Wired',12);
INSERT INTO Headphones (type,product_id) VALUES ('Wireless',13);
INSERT INTO Headphones (type,product_id) VALUES ('Wired',14);
INSERT INTO Headphones (type,product_id) VALUES ('Wireless',15);
INSERT INTO Headphones (type,product_id) VALUES ('Wired',16);
INSERT INTO Headphones (type,product_id) VALUES ('Wired',17);
INSERT INTO Headphones (type,product_id) VALUES ('Wireless',18);
INSERT INTO Headphones (type,product_id) VALUES ('Wired',19);
INSERT INTO Headphones (type,product_id) VALUES ('Wireless',20);

INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Dell Km117 Wireless Keyboard Mouse',1329,'Dell','Keyboard and Mouse','Black',4.1,1); -- wireless normal tactile
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Logitech MK200',999,'Logitech','Keyboard and Mouse','Black',4.2,6); -- wired normal 
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Redgear Blaze 3',1329,'Dell','Keyboard and Mouse','Black',4.1,2); -- nordmal backlight yes
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Portronics Key2-A',999,'Portronics','Keyboard and Mouse','White',4.5,7); -- wireless no back lit tactile
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Logitech K480',2290,'Logitech','Keyboard and Mouse','Black',4.6,3); -- wireless normal
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Dell Ms116 275-BBCB',219,'Dell','Keyboard and Mouse','Black',3.3,8); -- wired
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Lenovo 300',348,'Lenovo','Keyboard and Mouse','Black',3.2,4); -- wired
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Lenovo Yoga Mouse',1329,'Lenovo','Keyboard and Mouse','Black',4.9,9); -- wireless
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Logitech G402',2198,'Logitech','Keyboard and Mouse','Black',5.0,5);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('BenQ Zowie EC 1',6439,'BenQ','Keyboard and Mouse','Black',5.0,10);

INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wireless','No Backlight','Tactile',21);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wireled','No Backlight','Tactile',22);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wired','Blue Backlight','Mechanical',23);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wireless','No Backlight','Tactile',24);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wireless','No Backlight','Tactile',25);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wired','No Backlight','Tactile',26);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wired','No Backlight','Tactile',27);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wireless','No Backlight','Tactile',28);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wired','Blue Backlight','Tactile',29);
INSERT INTO Keyboards_Mouse (connectivity_type,backlight,button_type,product_id) VALUES ('Wired','No Backlight','Tactile',30);

INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('DELL XPS 13',94536.17,'DELL','Laptop and PC','White',4.0,1);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('ASUS VIVOBOOK S15',42497.91,'ASUS','Laptop and PC','Grey',4.2,2);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('HP STREAM 14',1999,'HP','Laptop and PC','Blue',3.8,3);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('MICROSOFT SURFACE BOOK 2',137901.38,'MICROSOFT','Laptop and PC','White',4.8,4);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('MICROSOFT SURFACE BOOK 2',137901.38,'MICROSOFT','Laptop and PC','White',4.8,4);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('APPLE MACBOOK PRO',189151.18,'APPLE','Laptop and PC','White',4.3,5);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('DELL XPS 15',149728.26,'DELL','Laptop and PC','White',4.2,6);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('HUAWEI MATEBOOK 13',70882.41,'HUAWEI','Laptop and PC','White',4.1,7);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('ASUS ROG ZEPHYRUS S GX701',208783.80,'ASUS','Laptop and PC','White',4.0,8);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('HP ENVY 13',120712.99,'HP','Laptop and PC','White',4.5,9);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('APPLE MACBOOK PRO WITH TOUCH BAR',90593.88,'APPLE','Laptop and PC','Black',4.7,10);

INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (31,'Window 10','Intel Core i7','SSD','1024GB','Ultrabook','16','13.3');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (32,'Window 10','Intel Core i7','SSD','256GB','Chromebook','8','15.5');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (33,'Window 10','AMD A4 157612.84','HDD','1024GB','Ultrabook','4','14');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (34,'Window 10','Intel Core i7','SSD','512GB','Chromebook','16','3840x2160');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (35,'MAC OS','Intel Core i7','SSD','512','MacBook','16','16');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (36,'Window 10','Intel Core i7','SSD','512GB','Ultrabook','16','15');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (37,'Window 10','Intel Core i7','SSD','512GB','Convertible ','16','13');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (38,'Window 10','Intel Core i7','SSD','1024GB','Convertible ','24','17.3');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (39,'Window 10','Intel Core i7','SSD','1024GB','Ultrabook','16','13');
INSERT INTO Laptop_Pc (product_id,os,processor,harddisk_type,harddisk_cap,type,ram,screen_size) VALUES (40,'MAC OS','Intel Core i7','SSD','256GB','MacBook','16','13.3');

INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Samsung Galaxy S10 Plus',69900,'Samsung','Smart Phone','Black',4.7,1);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Huawei P30 Pro',54000,'Huawei','Smart Phone','Black',4.2,2);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('iPhone 11 Pro Max',99600,'iPhone','Smart Phone','White',4.1,3);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Samsung Galaxy Note 10 Plus',89900,'Samsung','Smart Phone','Silver',4.7,4);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('iPhone 11',64000,'iPhone','Smart Phone','White',4.0,5);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Huawei P30',49900,'Huawei','Smart Phone','White',3.9,6);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('OnePlus 7T Pro',53999,'OnePlus','Smart Phone','Blue',5.0,7);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Oppo Reno',39900,'Oppo','Smart Phone','Black',3.6,8);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Samsung Galaxy S10',69900,'Samsung','Smart Phone','Black',4.7,9);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('OnePlus 7T',69900,'OnePlus','Smart Phone','Black',5.0,10);

INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (41,'Android 9','16MP','Snapdragon 855','6.1','4100mAh','12GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (42,'Android 9','40MP','Kirin 980','6.47','4200mAh','8GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (43,'iOS 13','12MP','Apple A13 Bionic','5.8','3046mAh','4GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (44,'Android 9','16MP','Exynos 9825','6.1','4300mAh','12GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (45,'iOS 13','12MP','Apple A13 Bionic','6.1','3110mAh','4GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (46,'Android 9','40MP','Kirin 980','6.1','3650mAh','8GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (47,'Android 10','48MP','Snapdragon 855 Plus','6.1','4100mAh','8GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (48,'Android 9','48MP','Snapdragon 855','6.6','4065mAh','8GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (49,'Android 9','16MP','Snapdragon 855','6.1','4100mAh','8GB');
INSERT INTO Mobiles (product_id,os,primary_camera,processor,screen_size,battery,ram) VALUES (50,'Android 10','48MP','Snapdragon 855','6.1','4100mAh','8GB');

INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Samsung Q90R',69900,'Samsung','Television','Black',4.0,10);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('LG C9 OLED',59900,'LG','Television','Black',4.0,9);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Vizio P-Series Quantum X',92900,'OnePlus','Television','Black',4.5,8);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Samsung Q900R',99800,'Samsung','Television','Black',4.6,7);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Sony A9G Master Series',62200,'Sony','Television','Black',4.2,6);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('LG B9 OLED65B9',79100,'OnePlus','Television','Black',3.9,5);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Samsung Q70R',109900,'Samsung','Television','Black',4.1,4);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('TCL R625',59900,'TCL','Television','Black',4.2,3);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Hisense 55H9F',94300,'Hisense','Television','Black',4.8,2);
INSERT INTO Product_Type (product_name, price , brand_name , type_1 , colour , rating , supplier_id) VALUES ('Sony Bravia X950G',69900,'OnePlus','Television','Black',4.2,1);

INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (51, 'Led' , '65' , 1, '4k QLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (52, 'Led' , '55' , 1, '4k OLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (53, 'Oled' , '65' , 1 , '4k QLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (54, 'Curved' , '85' , 1 , '8k QLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (55, 'Led' , '55' , 1 , '4k OLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (56, 'Curved' , '65' , 1 , '4k OLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (57, 'Curved' , '55' , 1 , '4k QLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (58, 'Curved' , '55' , 1 , '4k QLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (59, 'Led' , '55' , 1 , '4k QLED');
INSERT INTO Televisions (product_id,type,screen_size,isSmart,resolution) VALUES (60, 'Oled' , '65' , 1 , '4k QLED');

update Account_User set password = "durvish17231" where username = 'Durvish';
update Account_User set password = "bhuvanyu17226" where username = 'Bhuvanyu';
update Account_User set password = "hemant17235" where username = 'Hemant';
update Account_User set password = "kartik17158" where username = 'Bhuvanyu';
update Account_User set password = "mayank17299" where username = 'Kartik';
update Account_User set password = "bhuvanyu17226" where username = 'Mayank';
update Account_User set password = "rajivraman" where username = 'Rajiv';
update Account_User set password = "monicaarora" where username = 'Monica';
update Account_User set password = "sankhasbasu" where username = 'Sankha';
update Account_User set password = "jeremy" where username = 'Hawk Eye';
update Account_User set password = "mark" where username = 'Hulk';

select * from Account_user;
delete from Account_user where customer_id >= 32 and customer_id<39;

INSERT INTO Account_User (username,password) VALUES ('Captain America','chrisevans');
INSERT INTO Account_User (username,password) VALUES ('Black Widow','scarlettjohansson');
INSERT INTO Account_User (username,password) VALUES ('Iron Man','robertdowney');
INSERT INTO Account_User (username,password) VALUES ('Thor','chris');
INSERT INTO Account_User (username,password) VALUES ('Hawk Eye','jeremy');
INSERT INTO Account_User (username,password) VALUES ('Hulk','mark');
INSERT INTO Account_User (username,password) VALUES ('Ant Man','paul');
INSERT INTO Account_User (username,password) VALUES ('Black Panther','chadwick');
INSERT INTO Account_User (username,password) VALUES ('Pepper Potts','ironman');
INSERT INTO Account_User (username,password) VALUES ('Super Man','iamfromdc');
update Account_User set password = "meikang" where username = 'Mei Kang';
update Account_User set password = "scarlettjohansson" where username = 'Black Widow';
update Account_User set password = "robertdowney" where username = 'Iron Man';
update Account_User set password = "chris" where username = 'Thor';
update Account_User set password = "jeremy" where username = 'Hawk Eye';
update Account_User set password = "mark" where username = 'Hulk';
update Account_User set password = "paul" where username = 'Ant Man';
update Account_User set password = "chadwick" where username = 'Black Panther';
update Account_User set password = "ironman" where username = 'Pepper Potts';
update Account_User set password = "iamfromdc" where username = 'Super Man';

select * from Account_User;

INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (1,1,13);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (2,1,1);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (3,1,30);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (4,1,24);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (5,1,43);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (6,1,3);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (7,1,54);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (8,1,23);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (9,1,31);
INSERT INTO Checkout (customer_id,quantity,product_id) VALUES (10,1,41);
-- delete from Checkout where customer_id in (1,2,3,4,5,6,7,8,9,10);

drop table Checkout;
select * from contact;
select price from product_type where product_id = 2;

INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9899888448,'durvish17231@iiitd.ac.in','Siddhartha Extension',110014,'Delhi','New Delhi','India',1,null);	
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9648337433,'bhuvanyu17226@iiitd.ac.in','sector 2',122001,'Delhi','New Delhi','India',2,null);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9999127433,'hemant17235@iiitd.ac.in','sector 39',122030,'Delhi','New Delhi','India',3,null);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9888837433,'kartik17158@iiitd.ac.in','sector 4',122009,'Delhi','New Delhi','India',4,null);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9836666433,'mayank17299@iiitd.ac.in','sector 29',122109,'Delhi','New Delhi','India',5,null);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9836577733,'aman@gmail.com','sector 36',122088,'Delhi','New Delhi','India',null,1);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9836517533,'vaibhav@gmail.com','sector 1',122022,'Delhi','New Delhi','India',null,2);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9836019333,'nakul@gmail.com','sector 98',122011,'Delhi','New Delhi','India',null,3);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9836000033,'shivam@gmail.com','sector 78',122034,'Delhi','New Delhi','India',null,4);
INSERT INTO Contact (phone_number,email,locality,pincode,city,state,country,customer_id,supplier_id) VALUES (9936427438,'pranjal@gmail.com','sector 44',122023,'Delhi','New Delhi','India',null,5);

INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (1,100,10);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (2,150,1);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (3,200,3);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (4,50,2);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (5,10,4);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (6,116,7);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (7,190,9);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (8,500,5);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (9,4,8);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (10,902,6);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (11,100,10);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (12,150,1);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (13,200,3);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (14,50,2);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (15,10,4);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (16,116,7);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (17,190,9);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (18,500,5);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (19,4,8);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (20,902,6);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (21,100,10);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (22,150,1);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (23,200,3);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (24,50,2);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (25,10,4);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (26,116,7);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (27,190,9);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (28,500,5);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (29,4,8);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (30,902,6);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (31,100,10);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (32,150,1);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (33,200,3);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (34,50,2);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (35,10,4);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (36,116,7);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (37,190,9);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (38,500,5);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (39,4,8);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (40,902,6);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (41,100,10);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (42,150,1);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (43,200,3);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (44,50,2);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (45,10,4);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (46,116,7);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (47,190,9);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (48,500,5);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (49,4,8);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (50,902,6);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (51,100,10);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (52,150,1);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (53,200,3);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (54,50,2);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (55,10,4);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (56,116,7);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (57,190,9);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (58,500,5);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (59,4,8);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (60,902,6);
INSERT INTO Inventory (product_id,quantity,supplier_id) VALUES (61,90,10);

INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (1,'2020-01-04','3:52:59.99','airpods',10,1,'2020-01-08','delivered');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (2,'2020-01-18','2:52:59.99', 'x360', 1,1,'2020-01-24','delivered');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (3,'2020-01-28','13:52:59.99', 'series4',3,1, '2020-02-03','delivered');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (4,'2020-01-06', '12:52:59.99','wpl-100',2,1, '2020-01-10','returned');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (1,'2020-01-02','4:52:59.99', 'macbook',4,1, '2020-01-06','delivered');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (6,'2020-01-15', '5:52:59.99','Mach 4', 7,1,'2020-01-20','delivered');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (7,'2020-01-19', '4:52:59.99','inspiron 14',9,1, '2020-01-25','delivered');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (8,'2020-02-01','1:51:59.99', 'mx-1270', 5,1,'2020-01-08','In transit');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (9,'2020-01-07', '5:54:59.99','Note 10',8,1, '2020-01-11','delivered');
INSERT INTO Order_History (customer_id,date,time,product_name,product_id,quantity,delivery_date,return_status) VALUES (10,'2020-01-21','4:55:59.99', 'Iphone X',6,1, '2020-01-23','delivered');

select * from order_history where customer_id = 1;
select * from product_type;
select distinct(type_1) from product_type;
select * from account_supplier;

SELECT * FROM requests ;
delete from requests where customer_id = 2;
SELECT username FROM account_supplier where supplier_id = 1;
select sum(C.quantity*P.price) from checkout as C,product_type as P where C.product_id = P.product_id and C.customer_id = 1;
update inventory set quantity = quantity + 20 where product_id = 1;
SELECT * FROM Account_Supplier A, Contact C where A.supplier_id = C.supplier_id;
select * from Account_Supplier;

select I.product_id,P.product_name,P.price,I.quantity,I.supplier_id,S.username from inventory as I,product_type as P,account_supplier as S where I.product_id = P.product_id and I.supplier_id = S.supplier_id;

select B.customer_id,B.product_id,sum(B.quantity) as required,A.quantity as available from inventory as A ,checkout as B where A.product_id=B.product_id and B.customer_id = 1 group by(B.product_id);
select product_id,sum(quantity) from requests where customer_id = 1 group by(product_id);
update inventory set quantity = 10 where product_id = 1;
delete from requests where customer_id = 1;
select * from order_history where customer_id = 1;

INSERT INTO Requests VALUES (2,1,10,1,'2020-01-04');
INSERT INTO Requests VALUES (4,2,9,1,'2020-01-03');
INSERT INTO Requests VALUES (6,3,8,2,'2020-01-17');
INSERT INTO Requests VALUES (8,4,7,1,'2020-01-24');
INSERT INTO Requests VALUES (10,5,6,2,'2020-01-18');
INSERT INTO Requests VALUES (1,6,5,3,'2020-01-12');account_company
INSERT INTO Requests VALUES (3,7,4,1,'2020-01-01');
INSERT INTO Requests VALUES (5,8,3,2,'2020-01-30');
INSERT INTO Requests VALUES (7,9,2,3,'2020-01-15');
INSERT INTO Requests VALUES (9,10,1,2,'2020-01-07');

alter table requests add flag int NOT NULL;

SHOW VARIABLES LIKE "secure_file_priv";

select * from account_user;
delete from account_user where customer_id = 21;
select A.*,B.quantity from Product_Type A,Inventory B where A.product_id = B.product_id;
select P.*,I.quantity from product_type P, inventory I where P.product_id = I.product_id and type_1 in (select (P.type_1) from product_type P,order_history O where P.product_id = O.product_id and customer_id = "+cid+") order by rating desc LIMIT 15;
account_supplier
select * from Account_Supplier;