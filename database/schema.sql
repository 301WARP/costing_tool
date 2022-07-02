/*
 * WARNING:
 * --------
 * ONLY RUN ONCE TO INITIALIZE DATABASE.
 * SUBSEQUENT EXECUTION OF THIS SCRIPT WILL DELETE ALL DATA FROM THE DATABASE
 */

-- Ensure costing tool database exists and we are using it
CREATE database IF NOT EXISTS `warp`;
USE `warp`;

-- Remove existing tables in the abs database clashing with our new tables 
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `annual_expense`;
DROP TABLE IF EXISTS `expense`;
DROP TABLE IF EXISTS `project`;
DROP TABLE IF EXISTS `contribution`;
DROP TABLE IF EXISTS `contract`;
DROP TABLE IF EXISTS `researcher`;
SET FOREIGN_KEY_CHECKS = 1;


-- Researchers
CREATE TABLE `researcher`
(
	`staff_id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
	`title` ENUM('Mr', 'Miss', 'Mrs', 'Ms', 'Dr', 'Prof') NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`staff_id`)
) Engine=InnoDB;


-- Yearly contribution of project to contract hours
CREATE TABLE `contribution`
(
	`contract_id` INTEGER(6) UNSIGNED NOT NULL,
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`year` YEAR NOT NULL,
	`role` VARCHAR(25) NOT NULL,
    `fte` DOUBLE(5, 2),
	`hours` DOUBLE(6, 2),
    `in_kind_%` DOUBLE(5,2) NOT NULL
		CHECK (`in_kind_%` BETWEEN 0 AND 100),
	PRIMARY KEY (`contract_id`, `project_id`, `year`)
) Engine=InnoDB;
    --`in_kind_$` DOUBLE(9,2) NOT NULL				-- unnecessary?
		--CHECK (`in_kind_$` >= 0),


--  Terms under which a researcher is employed
CREATE TABLE `contract`
(
    `id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `researcher_id` INTEGER(6) UNSIGNED NOT NULL,
    `staff_type` ENUM('NonCasual', 'Casual', 'RHD'),
    `classification_non_casual` ENUM('A', 'B', 'C', 'D', 'E'),					-- values?
    `classification_casual` ENUM('RA1', 'RA2', 'RA3', 'RA4', 'RA5'),				-- values?
    `classification_rhd` ENUM('APA', 'TopUp'),									-- values?
    `pay_code` ENUM('1', '2', '3'),												-- values?
    `step` ENUM('1', '2', '3'),													-- values?
    `salary` DOUBLE(9,2)
		CHECK (`salary` >= 0),
    `hourly_rate` DOUBLE(9,2)
		CHECK (`hourly_rate` >= 0),
    `wage_adjustment` DOUBLE(9,2),						-- per contract or project?
    `on_cost_rate` DOUBLE(9,2),					-- per contract or project?
    PRIMARY KEY (`id`)
) Engine=InnoDB;


-- Project
CREATE TABLE `project`
(
    `id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `lead_researcher_id` INTEGER(6) UNSIGNED NOT NULL,
    `category` ENUM('1', '2', '3', '4', 'Consultancy', 'Exemption') NOT NULL,
    `amc_menzies` ENUM('AMC', 'Menzies', 'None') NOT NULL
		DEFAULT 'None',
    `start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
    `year_end_type` ENUM('Calendar', 'Financial') NOT NULL DEFAULT 'Calendar',
    `utas_cash` DOUBLE(9,2) NOT NULL,
    `partner_cash` DOUBLE(9,2) NOT NULL,
    `entity` ENUM('IMAS') NOT NULL,												-- values?
    `crowd_funding_provider` ENUM('?') NOT NULL,									-- values?

	-- Codes
	-- Could be one table, but not sure how to enforce maximum of three
	-- Field of Research
    `for_code_1` INTEGER(6),
    `for_%_1` DOUBLE(5,2)
		CHECK (`for_%_1` BETWEEN 0 AND 100),
    `for_code_2` INTEGER(6),
    `for_%_2` DOUBLE(5,2)
		CHECK (`for_%_2` BETWEEN 0 AND 100),
    `for_code_3` INTEGER(6),
    `for_%_3` DOUBLE(5,2)
		CHECK (`for_%_3` BETWEEN 0 AND 100),
	-- Socio-Economic Objective
    `seo_code_1` INTEGER(6),
    `seo_%_1` DOUBLE(5,2)
		CHECK (`seo_%_1`  BETWEEN 0 and 100),
    `seo_code_2` INTEGER(6),
    `seo_%_2` DOUBLE(5,2)
		CHECK (`seo_%_2`  BETWEEN 0 and 100),
    `seo_code_3` INTEGER(6),
    `seo_%_3` DOUBLE(5,2)
		CHECK (`seo_%_3`  BETWEEN 0 and 100),
	-- Types of Activity
	`applied_research` DOUBLE(5,2),
	`experimental_development` DOUBLE(5,2),
	`strategic_basic` DOUBLE(5,2),
	`pure_basic` DOUBLE(5,2),

	-- Ethics
    `human_medical` BOOLEAN NOT NULL,
    `human_medical_ref` INTEGER(6),
    `human_social_science` BOOLEAN NOT NULL,
    `human_social_science_ref` INTEGER(6),
    `animals` BOOLEAN NOT NULL,
    `animals_ref` INTEGER(6),
    `gmo` BOOLEAN NOT NULL,
    `gmo_ref` INTEGER(6),
    `radiation` BOOLEAN NOT NULL,
    `radiation_ref` INTEGER(6),
    `carcinogen_teratogen` BOOLEAN NOT NULL,
    `carcinogen_teratogen_ref` INTEGER(6),

	-- Endorsements
	-- CI Endoresement
    `ci_endorsement` BOOLEAN NOT NULL,
    `ci_endorsement_date` DATE,
    `risk_assessment` BOOLEAN NOT NULL,
    `satisfied_risks` BOOLEAN NOT NULL,
    `utas_insurance` BOOLEAN NOT NULL,
    `defence_strategic_goods` BOOLEAN NOT NULL,
    `conflict_of_interest` BOOLEAN NOT NULL,
    `foreign_principals` BOOLEAN NOT NULL,
	-- Diretor Endoresement
	`organisational_unit_1` VARCHAR(256) NOT NULL,
	`organisational_unit_1_split` DOUBLE(5,2) NOT NULL,
    `director_endorsement_1` BOOLEAN,
    `director_endorsement_1_date`DATE,
	`organisational_unit_2` VARCHAR(256),
	`organisational_unit_2_split` DOUBLE(5,2),
    `director_endorsement_2` BOOLEAN,
    `director_endorsement_2_date`DATE,
	-- College Endoresement
    `college_endorsement` BOOLEAN,

    PRIMARY KEY (`id`)
) Engine=InnoDB;

-- Expenses
    --`project_id` INTEGER(6) UNSIGNED NOT NULL,
    --`year` DATE NOT NULL,
    --`units_per_year` DOUBLE(9,2) NOT NULL,
    --`in_kind_$` DOUBLE(9,2) NOT NULL,
    --`accommodation` DOUBLE(9,2),					- This is just cost_per_unit
CREATE TABLE `expense`
(
    `id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `expense_type` ENUM('travel', 'facility hire', 'equipment', 'consumables', 'partner organisations', 'external contracting', 'RHD non-stipend costs', 'other') NOT NULL,
    `cost_per_unit` DOUBLE(9,2) NOT NULL,
    `in_kind_%` DOUBLE(9,2) NOT NULL,

	-- Travel
    `num_travellers` INTEGER(4),
    `departure` DATE,
    `return` DATE,
    `fare` DOUBLE(9,2),
    `car_hire` DOUBLE(9,2),

	-- Facility  Hire
    `facility` ENUM('Labs R Us'),
    `time_unit` ENUM('minutes', 'hours', 'days', 'weeks', 'months'),
    `organisation` ENUM('UMELB', '?'),
    PRIMARY KEY (`id`)

) Engine=InnoDB;
--, `project_id`, `year`),

CREATE TABLE `annual_expense`
(
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`expense_id` INTEGER(6) UNSIGNED NOT NULL,
	`year` YEAR NOT NULL,
	`units` DOUBLE(9,2) NOT NULL,
    PRIMARY KEY (`project_id`, `expense_id`, `year`)
) Engine=InnoDB;

-- Specify foreign keys
ALTER TABLE `contract` ADD FOREIGN KEY (`researcher_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `project` ADD FOREIGN KEY (`lead_researcher_id`) REFERENCES `researcher` (`staff_id`);
--ALTER TABLE `expense` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `annual_expense` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `annual_expense` ADD FOREIGN KEY (`expense_id`) REFERENCES `expense` (`id`);
