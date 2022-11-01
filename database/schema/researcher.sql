-- -----------------------------------------------------------------------------
-- Researcher employed by UTAS
-- -----------------------------------------------------------------------------
CREATE TABLE `researcher`
(
	`staff_id` INTEGER(6) UNSIGNED NOT NULL UNIQUE,
	`title` ENUM('NONE', 'MR', 'MISS', 'MRS', 'MS', 'DR', 'PROF', 'TBA'),
	`first_name` VARCHAR(50),
	`last_name` VARCHAR(50),
	PRIMARY KEY (`staff_id`)
) Engine=InnoDB;
