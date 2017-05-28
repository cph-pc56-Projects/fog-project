

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `fog` DEFAULT CHARACTER SET utf8 ;
USE `fog` ;

-- -----------------------------------------------------
-- Table `fog`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`delivery` (
  `delivery_id` VARCHAR(10) NOT NULL,
  `delivery_date` DATE NULL DEFAULT NULL,
  `delivery_status` INT(11) NOT NULL,
  `more_info` VARCHAR(500) NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`delivery_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fog`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`invoice` (
  `invoice_id` VARCHAR(10) NOT NULL,
  `creation_date` DATE NOT NULL,
  `total_price` DOUBLE NOT NULL,
  PRIMARY KEY (`invoice_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fog`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`users` (
  `account_id` VARCHAR(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` INT(11) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `zip_code` INT(11) NOT NULL,
  `role` INT(11) NOT NULL,
  `creation_date` DATE NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fog`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`orders` (
  `order_id` VARCHAR(10) NOT NULL,
  `creation_date` DATE NOT NULL,
  `customer_id` VARCHAR(10) NOT NULL,
  `order_status` INT(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `customer_idx` (`customer_id` ASC),
  CONSTRAINT `customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `fog`.`users` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fog`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`products` (
  `product_id` VARCHAR(10) NOT NULL,
  `price` DOUBLE NOT NULL,
  `inner_height` DOUBLE NOT NULL,
  `width` DOUBLE NOT NULL,
  `length` DOUBLE NOT NULL,
  `has_shed` INT(11) NOT NULL,
  `shed_length` DOUBLE NULL DEFAULT '0',
  `shed_width` DOUBLE NULL DEFAULT '0',
  `rooftop_type` INT(11) NOT NULL,
  `rooftop_height` DOUBLE NULL DEFAULT '0',
  `rooftop_angle` INT(11) NULL DEFAULT '0',
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fog`.`order_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`order_details` (
  `order_id` VARCHAR(10) NOT NULL,
  `product_id` VARCHAR(10) NOT NULL,
  `sales_rep_id` VARCHAR(10) NULL DEFAULT NULL,
  `delivery_id` VARCHAR(10) NULL DEFAULT NULL,
  `invoice_id` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `product_id_idx` (`product_id` ASC),
  INDEX `salesRep_idx` (`sales_rep_id` ASC),
  INDEX `delivery_idx` (`delivery_id` ASC),
  INDEX `invoice_idx` (`invoice_id` ASC),
  CONSTRAINT `delivery`
    FOREIGN KEY (`delivery_id`)
    REFERENCES `fog`.`delivery` (`delivery_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `invoice`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `fog`.`invoice` (`invoice_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `order`
    FOREIGN KEY (`order_id`)
    REFERENCES `fog`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product`
    FOREIGN KEY (`product_id`)
    REFERENCES `fog`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `salesRep`
    FOREIGN KEY (`sales_rep_id`)
    REFERENCES `fog`.`users` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
