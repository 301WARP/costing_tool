-- -----------------------------------------------------------------------------
-- Contribution of project to contract hours
-- -----------------------------------------------------------------------------
CREATE TABLE `contribution`
(
	`contract_id` INTEGER(6) UNSIGNED NOT NULL,
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`role` VARCHAR(25),
    `in_kind_%` DOUBLE(5,2)
		CHECK (`in_kind_%` BETWEEN 0 AND 100),
    `wage_adjustment` DOUBLE,
    `on_cost_rate` DOUBLE,
	PRIMARY KEY (`contract_id`, `project_id`)
) Engine=InnoDB;


-- -----------------------------------------------------------------------------
-- Annual contribution of project to contract hours
-- -----------------------------------------------------------------------------
CREATE TABLE `annual_contribution`
(
	`contract_id` INTEGER(6) UNSIGNED NOT NULL,
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`year` INTEGER(4) NOT NULL,
    `units` DOUBLE(6, 2),
	PRIMARY KEY (`contract_id`, `project_id`, `year`)
) Engine=InnoDB;


-- -----------------------------------------------------------------------------
-- Salary on cost rate
--     static entry
-- -----------------------------------------------------------------------------
CREATE TABLE `salary_on_cost`
(
	`type` ENUM('PERMANENT_ADDITIONAL', 'PERMANENT_GOV', 'CASUAL'),
	`year` INTEGER(4),
	`percent` DOUBLE,
	PRIMARY KEY (`type`, `year`)
) Engine=InnoDB;
