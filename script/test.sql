SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`credentials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`credentials`
(
    `credential_id` BIGINT(20)   NOT NULL,
    `email`         VARCHAR(45)  NOT NULL,
    `login`         VARCHAR(45)  NOT NULL,
    `password`      VARCHAR(255) NOT NULL,
    `role`          VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`credential_id`),
    UNIQUE INDEX `UK_6pka8top3ggqmjvppakv4ygl8` (`email` ASC) VISIBLE,
    UNIQUE INDEX `UK_8mtpf80qfxpk6j5buih33kohf` (`login` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user`
(
    `user_id`       BIGINT(20)  NOT NULL,
    `birth_date`    DATE        NOT NULL,
    `first_name`    VARCHAR(45) NOT NULL,
    `gender`        BIT(1)      NOT NULL,
    `last_name`     VARCHAR(45) NULL DEFAULT NULL,
    `user_rating`   INT(11)     NOT NULL,
    `credential_id` BIGINT(20)  NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    INDEX `FKswouq4mvb86mer6tmnejflgro` (`credential_id` ASC) VISIBLE,
    CONSTRAINT `FKswouq4mvb86mer6tmnejflgro`
        FOREIGN KEY (`credential_id`)
            REFERENCES `mydb`.`credentials` (`credential_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`ad_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ad_type`
(
    `ad_type_id`     BIGINT(20)  NOT NULL,
    `buy_or_sale`    BIT(1)      NOT NULL,
    `category`       VARCHAR(45) NOT NULL,
    `classification` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ad_type_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`ad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ad`
(
    `ad_id`       BIGINT(20)  NOT NULL,
    `ad_date`     DATE        NOT NULL,
    `ad_message`  VARCHAR(255) NOT NULL,
    `ad_top_date` DATE        NULL DEFAULT NULL,
    `ad_type_id`  BIGINT(20)  NULL DEFAULT NULL,
    `user_id`     BIGINT(20)  NULL DEFAULT NULL,
    PRIMARY KEY (`ad_id`),
    INDEX `FKire9vd5dj9cjb9p32l1jglfny` (`ad_type_id` ASC) VISIBLE,
    INDEX `FK69ephc15qrc01y7jpai17ai3o` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FK69ephc15qrc01y7jpai17ai3o`
        FOREIGN KEY (`user_id`)
            REFERENCES `mydb`.`user` (`user_id`),
    CONSTRAINT `FKire9vd5dj9cjb9p32l1jglfny`
        FOREIGN KEY (`ad_type_id`)
            REFERENCES `mydb`.`ad_type` (`ad_type_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`chat`
(
    `chat_id`   BIGINT(20)  NOT NULL,
    `chat_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`chat_id`),
    UNIQUE INDEX `UK_oxyqpmfsj5ouvscdw20smijqu` (`chat_name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comments`
(
    `comment_id`      BIGINT(20)   NOT NULL,
    `comment_date`    DATE         NOT NULL,
    `comment_message` VARCHAR(255) NOT NULL,
    `ad_id`           BIGINT(20)   NULL DEFAULT NULL,
    `user_id`         BIGINT(20)   NULL DEFAULT NULL,
    PRIMARY KEY (`comment_id`),
    INDEX `FKnrqvcd9bwgdefb01jy1dxxjmk` (`ad_id` ASC) VISIBLE,
    INDEX `FKqi14bvepnwtjbbaxm7m4v44yg` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FKnrqvcd9bwgdefb01jy1dxxjmk`
        FOREIGN KEY (`ad_id`)
            REFERENCES `mydb`.`ad` (`ad_id`),
    CONSTRAINT `FKqi14bvepnwtjbbaxm7m4v44yg`
        FOREIGN KEY (`user_id`)
            REFERENCES `mydb`.`user` (`user_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hibernate_sequence`
(
    `next_val` BIGINT(20) NULL DEFAULT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`message`
(
    `message_id`   BIGINT(20)   NOT NULL,
    `message_date` DATE         NOT NULL,
    `text`         VARCHAR(255) NOT NULL,
    `chat_id`      BIGINT(20)   NULL DEFAULT NULL,
    `user_id`      BIGINT(20)   NULL DEFAULT NULL,
    PRIMARY KEY (`message_id`),
    INDEX `FKmejd0ykokrbuekwwgd5a5xt8a` (`chat_id` ASC) VISIBLE,
    INDEX `FKb3y6etti1cfougkdr0qiiemgv` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FKb3y6etti1cfougkdr0qiiemgv`
        FOREIGN KEY (`user_id`)
            REFERENCES `mydb`.`user` (`user_id`),
    CONSTRAINT `FKmejd0ykokrbuekwwgd5a5xt8a`
        FOREIGN KEY (`chat_id`)
            REFERENCES `mydb`.`chat` (`chat_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_chats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_chats`
(
    `chat_id` BIGINT(20) NOT NULL,
    `user_id` BIGINT(20) NOT NULL,
    INDEX `FKe51kw0n1rl9ygyckfk80t93l9` (`user_id` ASC) VISIBLE,
    INDEX `FK767da33te05aactnp1590c12n` (`chat_id` ASC) VISIBLE,
    CONSTRAINT `FK767da33te05aactnp1590c12n`
        FOREIGN KEY (`chat_id`)
            REFERENCES `mydb`.`chat` (`chat_id`),
    CONSTRAINT `FKe51kw0n1rl9ygyckfk80t93l9`
        FOREIGN KEY (`user_id`)
            REFERENCES `mydb`.`user` (`user_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

INSERT INTO mydb.credentials (credential_id, email, login, password, `role`)
VALUES (1, 'first@mail.ru', 'programmer777', '$2a$10$0TjQqVoJJDz/56W4ZDoCAOs9J7J.krxeKJfMd3uALQAUQYNxI45hi', 'ADMIN')
     , (2, 'second@mail.ru', 'sellerfromgrodno', '$2a$10$EITkosJyQjzR7NuEox5zleXqdoQxkV25G4vph/cwGadQ0K1FxdESO', 'USER')
     , (3, 'thismail@list.ru', 'Alex2000', '$2a$10$wjCFvXq6Qy8gbq3eSMVIduasGenb/QZve3KsyVufVwA1gV7uB5p8a', 'USER')
     , (4, 'testmail@gmail.com', 'Kate1990', '$2a$10$EllKK5sVs6lRSMqwiNccZOnd398LS5zTmqXfVoo7/UKQd.z6TgYw2', 'USER')
;

INSERT INTO mydb.`user` (user_id, birth_date, first_name, gender, last_name, user_rating, credential_id)
VALUES (1, '2001-10-18', 'Sergei', 1, 'Ivanov', 100, 1)
     , (2, '1995-10-04', 'Dmitry', 1, 'Petrov', 10, 2)
     , (3, '2000-10-02', 'Alex', 1, 'Sidorov', 5, 3)
     , (4, '1990-01-01', 'Kate', 0, '', 1, 4)
;

INSERT INTO mydb.ad_type (ad_type_id, buy_or_sale, category, classification)
VALUES (1, 1, 'Auto', 'BMW')
     , (2, 0, 'Auto', 'BMW')
     , (3, 1, 'Auto', 'Audi')
     , (4, 0, 'Auto', 'Audi')
     , (5, 1, 'Computers', 'PC')
     , (6, 0, 'Computers', 'PC')
     , (7, 1, 'Computers', 'Laptop')
     , (8, 0, 'Computers', 'Laptop')
;

INSERT INTO mydb.ad (ad_id, ad_date, ad_message, ad_top_date, ad_type_id, user_id)
VALUES (1, '2019-10-01', 'Продам Audi A6 2001г,2.5 дизель,5000$', NULL, 4, 2)
     , (2, '2019-10-05', 'Куплю компьютеры б/у,можно не рабочие.', NULL, 5, 1)
     , (3, '2019-10-03', 'Куплю BMW до 1990 года,можно не на ходу', NULL, 1, 3)
;

INSERT INTO mydb.comments (comment_id, comment_date, comment_message, ad_id, user_id)
VALUES (1, '2019-10-17', 'Дороговато. За 4000 забрал бы', 1, 3)
     , (2, '2019-10-17', '4500 последняя цена', 1, 2)
     , (3, '2019-10-13', 'А ноутбуки берете?', 2, 4)
     , (4, '2019-10-14', 'Нет.', 2, 1)
;

INSERT INTO mydb.chat (chat_id, chat_name)
VALUES (1, 'programmer777 and Kate1990')
;

INSERT INTO mydb.user_chats (chat_id, user_id)
VALUES (1, 1)
     , (1, 4)
;

INSERT INTO mydb.message (message_id, message_date, `text`, chat_id, user_id)
VALUES (1, '2019-10-16', 'Привет! Есть процессор intel pentium 3 800мГц. За сколько возьмешь?', 1, 4)
     , (2, '2019-10-17', 'Добрый день! Готов забрать за 10р.', 1, 1)
;

INSERT INTO mydb.hibernate_sequence (next_val)
VALUES (9)
     , (9)
     , (9)
     , (9)
     , (9)
     , (9)
     , (9)
;