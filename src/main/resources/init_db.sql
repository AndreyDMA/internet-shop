CREATE TABLE `test`.`items`
(
    `item_id` INT           NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(225)  NULL,
    `price`   DECIMAL(6, 2) NOT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE `test`.`buckets`
(
    `bucket_id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`bucket_id`)
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
    `bucket_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `login_UNIQUE` (`login`),
    KEY `bucket_id_fk_idx` (`bucket_id`),
    CONSTRAINT `bucket_id_fk` FOREIGN KEY (`bucket_id`) REFERENCES `buckets` (`bucket_id`)
);

CREATE TABLE `test`.`orders`
(
    `order_id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id`  int(11) NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `orders_users_fk_idx` (`user_id`),
    CONSTRAINT `orders_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `test`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
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

INSERT INTO `test`.`items` (`name`, `price`) VALUES ('test item 1', '200.0');
INSERT INTO `test`.`items` (`name`, `price`) VALUES ('test item 2', '500.0');
INSERT INTO `test`.`items` (`name`, `price`) VALUES ('test item 3', '1000.0');

INSERT INTO `test`.`roles` (`role_name`) VALUES ('USER');
INSERT INTO `test`.`roles` (`role_name`) VALUES ('ADMIN');
