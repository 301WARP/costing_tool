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
DROP TABLE IF EXISTS `annual_contribution`;
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


-- Contribution of project to contract hours
CREATE TABLE `contribution`
(
	`contract_id` INTEGER(6) UNSIGNED NOT NULL,
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`role` VARCHAR(25) NOT NULL,
    `in_kind_%` DOUBLE(5,2) NOT NULL
		CHECK (`in_kind_%` BETWEEN 0 AND 100),
	PRIMARY KEY (`contract_id`, `project_id`)
) Engine=InnoDB;


-- Annual contribution of project to contract hours
CREATE TABLE `annual_contribution`
(
	`contract_id` INTEGER(6) UNSIGNED NOT NULL,
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`year` INTEGER(4) NOT NULL,
    `units` DOUBLE(6, 2),
	PRIMARY KEY (`contract_id`, `project_id`, `year`)
) Engine=InnoDB;


--  Terms under which a researcher is employed
CREATE TABLE `contract`
(
    `id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `researcher_id` INTEGER(6) UNSIGNED NOT NULL,
    `staff_type` ENUM('NON_CASUAL', 'CASUAL', 'RHD'),
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
    `category` ENUM('ONE', 'TWO', 'THREE', 'FOUR', 'CONSULTANCY', 'EXEMPTION') NOT NULL,
	`category_1_subtype` ENUM('NONE'),
    `amc_menzies` ENUM('AMC', 'MENZIES', 'NONE') NOT NULL
		DEFAULT 'NONE',
    `start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
    `year_end_type` ENUM('CALENDAR', 'FINANCIAL') NOT NULL DEFAULT 'CALENDAR',
    `utas_cash` DOUBLE(9,2) NOT NULL,
    `partner_cash` DOUBLE(9,2) NOT NULL,
    `entity` ENUM('IMAS') NOT NULL,												-- values?
    `crowd_funding_provider` ENUM('?', 'NONE') NOT NULL,									-- values?

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
    `risks_managed` BOOLEAN NOT NULL,
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
    `college_endorsement_date` DATE,

    PRIMARY KEY (`id`)
) Engine=InnoDB;

-- Expenses
CREATE TABLE `expense`
(
    `id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `project_id` INTEGER(6) UNSIGNED NOT NULL,
    `expense_type` ENUM('TRAVEL', 'FACILITY_HIRE', 'EQUIPMENT', 'CONSUMABLES',
						'PARTNER_ORGANISATIONS', 'EXTERNAL_CONTRACTING',
						'RHD_NON-STIPEND_COSTS', 'OTHER') NOT NULL,
    `cost_per_unit` DOUBLE(9,2) NOT NULL,
    `in_kind_%` DOUBLE(9,2) NOT NULL,

	-- Travel
    `num_travellers` INTEGER(4),
    `departure` DATE,
    `return` DATE,
    `fare` DOUBLE(9,2),
    `car_hire` DOUBLE(9,2),
    `meals` DOUBLE(9,2),
    `accommodation` DOUBLE(9,2),

	-- Facility  Hire
    `facility` ENUM('LABS_R_US'),
    `time_unit` ENUM('MINUTES', 'HOURS', 'DAYS', 'WEEKS', 'MONTHS'),

	-- Partner Organisation
    `organisation` ENUM('UMELB', '?', 'NONE'),
    PRIMARY KEY (`id`)

) Engine=InnoDB;

-- Annual Expenses
CREATE TABLE `annual_expense`
(
	`expense_id` INTEGER(6) UNSIGNED NOT NULL,
	`year` INTEGER(4) NOT NULL,
	`units` DOUBLE(9,2) NOT NULL,
    PRIMARY KEY (`expense_id`, `year`)
) Engine=InnoDB;

-- Specify foreign keys
ALTER TABLE `contract` ADD FOREIGN KEY (`researcher_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `project` ADD FOREIGN KEY (`lead_researcher_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `expense` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `annual_expense` ADD FOREIGN KEY (`expense_id`) REFERENCES `expense` (`id`);


-- -----------------------------------------------------------------------------
-- Test Data
-- -----------------------------------------------------------------------------

source ./test_data.sql;
