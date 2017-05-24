INSERT INTO users (email, password, fName, lName, phone, adress, zipCode, role, creationDate) VALUES ('fog_admin@gmail.com', 'fog_admin', 'Admin', 'Fog', '+45 12 34 56 78', 'Fog Admin 34', 3456, 1, CURDATE());
INSERT INTO users (email, password, fName, lName, phone, adress, zipCode, role, creationDate) VALUES ('customer@gmail.com', 'customer', 'First', 'Customer', '+45 12 34 56 78', 'Customer 34', 5555, 0, CURDATE());
INSERT INTO users (email, password, fName, lName, phone, adress, zipCode, role, creationDate) VALUES ('customer2@gmail.com', 'customer2', 'Second', 'Customer', '+45 23 33 55 77', 'Customer 22', 6666, 0, CURDATE());

insert into users (email, password, fName, lName, phone, adress, zipCode, role, creationDate) VALUES ('andrian@fog.dk','69696969','Andrian','Vangelov','777-333','@home123', 2200, 1, CURDATE());

insert into users (email, password, fName, lName, phone, adress, zipCode, role, creationDate) VALUES ('patrick@fog.dk','69696969','Patrick','Fenger','111-555','@home456', 2100, 1, CURDATE());

insert into users (email, password, fName, lName, phone, adress, zipCode, role, creationDate) VALUES ('petru@fog.dk','69696969','Petru','Catana','000-999','@home789', 2300, 1, CURDATE());

insert into users (email, password, fName, lName, phone, adress, zipCode, role, creationDate) VALUES ('alexandar@fog.dk','69696969','Alexandar','Osenov','666-000','@home101', 3460, 1, CURDATE());

insert into product (height, width, length,rooftopType, hasShed) VALUES (2.5, 2.5,2.5, 1,1);
insert into product (height, width, length,rooftopType, hasShed) VALUES (2.3, 2.3,2.3, 0,0);
INSERT into orders (price, creationDate,customer_id, orderStatus) VALUES (3.5,CURDATE(), 2, 0);
INSERT into orders (price, creationDate,customer_id, orderStatus) VALUES (4.5,CURDATE(), 2, 0);
INSERT into orders (price, creationDate,customer_id, orderStatus) VALUES (5.5,CURDATE(), 3, 0);
INSERT into orders (price, creationDate,customer_id, orderStatus) VALUES (2.5,CURDATE(), 3, 0);
INSERT into orders (price, creationDate,customer_id, orderStatus) VALUES (7.5,CURDATE(), 2, 1);
INSERT into orders (price, creationDate,customer_id, orderStatus) VALUES (10.5,CURDATE(), 2, 2);
insert into orderdetails (product_id, order_id) VALUES (2,1);
insert into orderdetails (product_id, order_id) VALUES (1,2);
insert into orderdetails (product_id, order_id) VALUES (1,3);
insert into orderdetails (product_id, order_id) VALUES (2,4);
insert into orderdetails (product_id, order_id) VALUES (2,5);
insert into orderdetails (product_id, order_id) VALUES (2,6);
insert into invoice (product, creationDate, price) VALUES (1, curdate(), 69.69);
insert into delivery (deliveryDate, devStatus, order_id) VALUES (curdate(), 0, 6);
UPDATE orderdetails SET salesRep_id = 1, delivery_id =1, invoice_id = 1 WHERE order_id = 1;
UPDATE orderdetails SET salesRep_id = 1, delivery_id =1, invoice_id = 1 WHERE order_id = 2;
UPDATE orderdetails SET salesRep_id = 1, delivery_id =1, invoice_id = 1 WHERE order_id = 3;
UPDATE orderdetails SET salesRep_id = 1, delivery_id =1, invoice_id = 1 WHERE order_id = 4;
UPDATE orderdetails SET salesRep_id = 1, delivery_id =1, invoice_id = 1 WHERE order_id = 5;
UPDATE orderdetails SET salesRep_id = 1, delivery_id =1, invoice_id = 1 WHERE order_id = 6;
