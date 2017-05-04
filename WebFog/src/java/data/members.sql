create database jfog;
use jfog;

CREATE TABLE members (
  id int(10) unsigned NOT NULL auto_increment,
  email varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  fname varchar(45) NOT NULL,
  lname varchar(45) NOT NULL,
  phone varchar(45) NOT NULL,
  address varchar(45) NOT NULL,
  zipcode int(10) NOT NULL,
  regdate date NOT NULL,
  accrole varchar(20),
  status varchar(10),
  PRIMARY KEY  (id)
);

ALTER TABLE members AUTO_INCREMENT=1000;

insert into members(email, password, fname, lname, phone, address, zipcode, regdate, accrole, status) 
values ('andrian@fog.dk','andrian','Andrian','Vangelov','777-333','@home123', 2200, CURDATE(),'admin', 'positive');

insert into members(email, password, fname, lname, phone, address, zipcode, regdate, accrole, status) 
values ('patrick@fog.dk','patrick','Patrick','Fenger','111-555','@home456', 2100, CURDATE(),'admin', 'positive');

insert into members(email, password, fname, lname, phone, address, zipcode, regdate, accrole, status) 
values ('petru@fog.dk','petru','Petru','Catana','000-999','@home789', 2300, CURDATE(),'admin', 'positive');

insert into members(email, password, fname, lname, phone, address, zipcode, regdate, accrole, status) 
values ('alexander@fog.dk','alexander','Alexander','Osenov','666-000','@home101', 3460, CURDATE(),'admin', 'positive');

insert into members(email, password, fname, lname, phone, address, zipcode, regdate, accrole, status) 
values ('customer@fog.dk','customer','Customer','AlAnCaFe','555-zero-zero-5','@street206', 1000, CURDATE(),'user', 'positive');