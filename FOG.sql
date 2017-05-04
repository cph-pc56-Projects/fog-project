-- MySQL Script generated by MySQL Workbench
-- Wed May  3 11:52:23 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FOG` DEFAULT CHARACTER SET utf8 ;
USE `FOG` ;

-- -----------------------------------------------------
-- Table `FOG`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`users` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `fName` VARCHAR(45) NOT NULL,
  `lName` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `adress` VARCHAR(200) NOT NULL,
  `zipCode` INT NOT NULL,
  `role` INT NOT NULL,
  `creationDate` DATE NOT NULL,
  PRIMARY KEY (`account_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FOG`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `height` DOUBLE NOT NULL,
  `width` DOUBLE NOT NULL,
  `length` DOUBLE NOT NULL,
  `rooftopType` INT NOT NULL,
  `hasShed` INT NOT NULL,
  `shedLength` DOUBLE NULL,
  `shedWidth` DOUBLE NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FOG`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `salesRep_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `Which product_idx` (`product_id` ASC),
  INDEX `Customer_idx` (`customer_id` ASC),
  INDEX `Sales Rep_idx` (`salesRep_id` ASC),
  CONSTRAINT `Product`
    FOREIGN KEY (`product_id`)
    REFERENCES `FOG`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `FOG`.`users` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Sales Rep`
    FOREIGN KEY (`salesRep_id`)
    REFERENCES `FOG`.`users` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FOG`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`delivery` (
  `delivery_id` INT NOT NULL AUTO_INCREMENT,
  `deliveryDate` DATE NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`delivery_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FOG`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`invoice` (
  `invoice_id` INT NOT NULL AUTO_INCREMENT,
  `product` INT NOT NULL,
  `creationDate` DATE NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`invoice_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FOG`.`orderDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`orderDetails` (
  `order_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `delivery_id` INT NOT NULL,
  `invoice_id` INT NULL,
  `creationDate` DATE NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `Delivery_idx` (`delivery_id` ASC),
  INDEX `Invoice_idx` (`invoice_id` ASC),
  CONSTRAINT `Order`
    FOREIGN KEY (`order_id`)
    REFERENCES `FOG`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Delivery`
    FOREIGN KEY (`delivery_id`)
    REFERENCES `FOG`.`delivery` (`delivery_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Invoice`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `FOG`.`invoice` (`invoice_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
