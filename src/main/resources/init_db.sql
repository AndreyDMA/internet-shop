CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8;

CREATE TABLE `test`.`items`
(
    `item_id` INT           NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(225)  NULL,
    `price`   DECIMAL(6, 2) NOT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE `test`.`orders`
(
    `order_id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`order_id`)
);

CREATE TABLE `test`.`orders_items`
(
    `orders_items_id` INT NOT NULL AUTO_INCREMENT,
    `order_id`        INT NOT NULL,
    `item_id`         INT NOT NULL,
    PRIMARY KEY (`orders_items_id`),
    INDEX `order_items_orders_fk_idx` (`order_id` ASC) VISIBLE,
    INDEX `orders_items_items_fk_idx` (`item_id` ASC) VISIBLE,
    CONSTRAINT `orders_items_orders_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `test`.`orders` (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `orders_items_items_fk`
        FOREIGN KEY (`item_id`)
            REFERENCES `test`.`items` (`item_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `test`.`users`
(
    `user_id` INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NULL,
    `surname`  VARCHAR(45) NULL,
    `login`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(250) NOT NULL,
    `token`    VARCHAR(45) NULL,
    `salt`     BLOB NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `login_UNIQUE` (`login`)
);

ALTER TABLE `test`.`orders`
    ADD COLUMN `user_id` INT NOT NULL AFTER `order_id`,
    ADD INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `test`.`orders`
    ADD CONSTRAINT `orders_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `test`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE `test`.`buckets`
(
    `bucket_id` INT NOT NULL AUTO_INCREMENT,
    `user_id`   INT NOT NULL,
    PRIMARY KEY (`bucket_id`),
    INDEX `buckets_users_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `buckets_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `test`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `test`.`buckets_items`
(
    `buckets_items_id` INT NOT NULL AUTO_INCREMENT,
    `bucket_id`        INT NOT NULL,
    `item_id`          INT NOT NULL,
    PRIMARY KEY (`buckets_items_id`),
    INDEX `buckets_items_items_fk_idx` (`item_id` ASC) VISIBLE,
    INDEX `buckets_items_buckets_fk_idx` (`bucket_id` ASC) VISIBLE,
    CONSTRAINT `buckets_items_items_fk`
        FOREIGN KEY (`item_id`)
            REFERENCES `test`.`items` (`item_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `buckets_items_buckets_fk`
        FOREIGN KEY (`bucket_id`)
            REFERENCES `test`.`buckets` (`bucket_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `test`.`roles`
(
    `role_id` INT         NOT NULL AUTO_INCREMENT,
    `role_name`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `test`.`roles_users`
(
    `roles_users_id` INT NOT NULL AUTO_INCREMENT,
    `role_id`        INT NOT NULL,
    `user_id`        INT NOT NULL,
    PRIMARY KEY (`roles_users_id`),
    INDEX `roles_users_roles_fk_idx` (`role_id` ASC) VISIBLE,
    INDEX `roles_users_users_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `roles_users_roles_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `test`.`roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `roles_users_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `test`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO `test`.`users` (`name`, `surname`, `login`, `password`) VALUES ('First', 'User', 'first', '1');
INSERT INTO `test`.`users` (`name`, `surname`, `login`, `password`) VALUES ('Second', 'User', 'second', '2');
INSERT INTO `test`.`users` (`name`, `surname`, `login`, `password`) VALUES ('Admin', 'Employe', 'admin', '111');

INSERT INTO `test`.`items` (`name`, `price`) VALUES ('phone', '200.0');
INSERT INTO `test`.`items` (`name`, `price`) VALUES ('tv', '500.0');
INSERT INTO `test`.`items` (`name`, `price`) VALUES ('pc', '1000.0');

INSERT INTO `test`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `test`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `test`.`orders` (`user_id`) VALUES ('2');

INSERT INTO `test`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '1');
INSERT INTO `test`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '2');
INSERT INTO `test`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '3');
INSERT INTO `test`.`orders_items` (`order_id`, `item_id`) VALUES ('3', '1');
INSERT INTO `test`.`orders_items` (`order_id`, `item_id`) VALUES ('3', '3');

INSERT INTO `test`.`roles` (`role_name`) VALUES ('USER');
INSERT INTO `test`.`roles` (`role_name`) VALUES ('ADMIN');

INSERT INTO `test`.`roles_users` (`role_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `test`.`roles_users` (`role_id`, `user_id`) VALUES ('1', '2');
INSERT INTO `test`.`roles_users` (`role_id`, `user_id`) VALUES ('2', '3');


