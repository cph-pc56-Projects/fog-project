create database fogusers;
use fogusers;

CREATE TABLE members (
  ssn int(10) unsigned NOT NULL auto_increment,
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  address varchar(45) NOT NULL,
  phone varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  regdate date NOT NULL,
  atype varchar(10),
  status varchar(10),
  PRIMARY KEY  (ssn)
);

ALTER TABLE members AUTO_INCREMENT=1234;