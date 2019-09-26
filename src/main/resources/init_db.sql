CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `test`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`));

  INSERT INTO `test`.`items` (`name`, `price`) VALUES ('iPhone 11', '1000');

  UPDATE `test`.`items` SET `name` = 'iPhone 10', `price` = '500.00' WHERE (`item_id` = '1');

  DELETE FROM `test`.`items` WHERE (`item_id` = '2');
