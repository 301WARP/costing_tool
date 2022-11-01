-- -----------------------------------------------------------------------------
-- Organisational Unit
-- -----------------------------------------------------------------------------
CREATE TABLE `unit`
(
	`id` INTEGER(6) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(256),
	`abbreviation` VARCHAR(256),
	`head_id` INTEGER(6) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) Engine=InnoDB;


-- -----------------------------------------------------------------------------
-- College
-- -----------------------------------------------------------------------------
CREATE TABLE `college`
(
	`id` INTEGER(6) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(256),
	`executive_dean_id` INTEGER(6) UNSIGNED,
    PRIMARY KEY (`id`)
) Engine=InnoDB;
