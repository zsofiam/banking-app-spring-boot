DROP DATABASE `bank_directory`;

CREATE DATABASE IF NOT EXISTS `bank_directory`;
USE `bank_directory`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT,
    `name`            		varchar(255) DEFAULT NULL,
    `email`                 varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `number`            varchar(255) DEFAULT NULL,
    `balance`    		decimal(19, 2) DEFAULT NULL,
    `user_id`           bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

INSERT INTO user (id, name, email)
VALUES (1, 'Andrea', 'andrea@gmail.com');
INSERT INTO user (id, name, email)
VALUES (2, 'Balint', 'balint@gmail.com');
INSERT INTO user (id, name, email)
VALUES (3, 'Cecil', 'cecil@gmail.com');

INSERT INTO account
    (number, balance, user_id)
VALUES (10000001, 100000.00, 1),
       (10000002, 200000.00, 1),
       (10000003, 300000.00, 1),
       (20000001, 400000.00, 2),
       (20000002, 500000.00, 2),
       (20000003, 600000.00, 2),
       (30000001, 700000.00, 3),
       (30000001, 800000.00, 3),
       (30000001, 900000.00, 3)
      ;
      
CREATE TABLE `transaction`
(
    `id`               bigint NOT NULL AUTO_INCREMENT,
    `amount`           decimal(19, 2) DEFAULT NULL,
    `comment` 			varchar(30) NOT NULL,
    `account_id`           bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
);
