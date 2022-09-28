/*
 * WARNING:
 * --------
 * ONLY RUN ONCE TO INITIALIZE DATABASE.
 * SUBSEQUENT EXECUTION OF THIS SCRIPT WILL DELETE ALL DATA FROM THE DATABASE
 */

-- Ensure costing tool database exists and we are using it
CREATE database IF NOT EXISTS `warp`;
USE `warp`;

-- Remove existing tables in the warp database clashing with our new tables 
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `annual_expense`;
DROP TABLE IF EXISTS `expense`;
DROP TABLE IF EXISTS `project`;
DROP TABLE IF EXISTS `annual_contribution`;
DROP TABLE IF EXISTS `contribution`;
DROP TABLE IF EXISTS `contract`;
DROP TABLE IF EXISTS `researcher`;
DROP TABLE IF EXISTS `director_endorsement`;
DROP TABLE IF EXISTS `college_endorsement`;
DROP TABLE IF EXISTS `unit`;
DROP TABLE IF EXISTS `college`;
SET FOREIGN_KEY_CHECKS = 1;


-- Researchers
CREATE TABLE `researcher`
(
	`staff_id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
	`title` ENUM('NONE', 'MR', 'MISS', 'MRS', 'MS', 'DR', 'PROF', 'TBA'),
	`first_name` VARCHAR(50),
	`last_name` VARCHAR(50),
	PRIMARY KEY (`staff_id`)
) Engine=InnoDB;


-- Contribution of project to contract hours
CREATE TABLE `contribution`
(
	`contract_id` INTEGER(6) UNSIGNED NOT NULL,
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`role` VARCHAR(25),
    `in_kind_%` DOUBLE(5,2)
		CHECK (`in_kind_%` BETWEEN 0 AND 100),
    `wage_adjustment` DOUBLE(9,2),						-- per contract or project?
    `on_cost_rate` DOUBLE(9,2),							-- per contract or project?
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
    `contract_type` ENUM('NON_CASUAL', 'CASUAL', 'RHD'),
    `staff_type_non_casual` ENUM(	'ACADEMIC', 'PROFESSIONAL',
									'RESEARCH_ASSISTANT'),
    `staff_type_casual` ENUM(	'ACADEMIC', 'PROFESSIONAL',
								'NON_AWARD_TEACHING', 'CONSERVATORIUM_AMEB'),
    `classification_non_casual` ENUM('A', 'B', 'C', 'D', 'E'),					-- values?
    `classification_casual` ENUM('RA1', 'RA2', 'RA3', 'RA4', 'RA5'),			-- values?
    `classification_rhd` ENUM('APA', 'TopUp'),									-- values?
    `pay_code` ENUM('1', '2', '3'),												-- values?
    `step` ENUM('1', '2', '3'),													-- values?
    `salary` DOUBLE(9,2)
		CHECK (`salary` >= 0),
    `hourly_rate` DOUBLE(9,2)
		CHECK (`hourly_rate` >= 0),
	`unit_id` INTEGER(6) UNSIGNED,
    PRIMARY KEY (`id`)
) Engine=InnoDB;


-- Project
CREATE TABLE `project`
(
    `id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `name` VARCHAR(256),
    `description` VARCHAR(256),
    `lead_researcher_name` VARCHAR(256),
    `lead_researcher_organisation` VARCHAR(256),
    `category` ENUM('ONE', 'TWO', 'THREE', 'FOUR', 'CONSULTANCY', 'EXEMPTION'),
    `amc_menzies` ENUM('AMC', 'MENZIES', 'NONE')
		DEFAULT 'NONE',
    `amc_national_centre` ENUM('NCMEH', 'NCPS', 'NONE')
		DEFAULT 'NONE',
    `start_date` DATE,
	`end_date` DATE,
    `year_end_type` ENUM('CALENDAR', 'FINANCIAL')
		DEFAULT 'CALENDAR',
    `utas_cash` DOUBLE(9,2),
    `utas_dvcr_cash` DOUBLE(9,2),
    `partner_cash` DOUBLE(9,2),
    `entity` ENUM('NONE', 'IMAS')												-- values?
		DEFAULT 'NONE',
    `crowd_funding_provider` ENUM('?', 'NONE'),									-- values?

	-- Specific to Category 1 projects
	`category_1_subtype` ENUM('NONE'),

	-- Specific to Consultancy projects
	`profit_margin` Double(5,2),

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

	-- Details
	`herdc` VARCHAR(256),
	`funding_body` VARCHAR(256),
	`scheme` VARCHAR(256),
	`contact_name` VARCHAR(256),
	`contact_email` VARCHAR(256),

	-- Ethics
    `human` BOOLEAN,
    `human_ref` VARCHAR(256),
    `animal` BOOLEAN,
    `animal_ref` VARCHAR(256),
    `drugs` BOOLEAN,
    `clinical_trial` BOOLEAN,

	-- Endorsements
	-- CI Endoresement
    `ci_endorsement` BOOLEAN,
    `ci_endorsement_date` DATE,
    `risk_assessment` BOOLEAN,
    `utas_insurance` BOOLEAN,
    `defence_strategic_goods` BOOLEAN,
    `conflict_of_interest` BOOLEAN,
    `foreign_principals` BOOLEAN,

	-- External Researchers
    `external_researchers` MEDIUMTEXT,

	-- RHD Information
    `rhd_involvement` ENUM('NONE', 'UNPAID', 'CASUAL', 'SCHOLARSHIP'),
    `rhd_unit_id` INTEGER(6) UNSIGNED,

    PRIMARY KEY (`id`)
) Engine=InnoDB;

-- Expenses
CREATE TABLE `expense`
(
    `id` INTEGER(6) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `project_id` INTEGER(6) UNSIGNED NOT NULL,
    `expense_type` ENUM('TRAVEL', 'FACILITY_HIRE', 'EQUIPMENT', 'CONSUMABLES',
						'PARTNER_ORGANISATIONS', 'EXTERNAL_CONTRACTING',
						'RHD_NON-STIPEND_COSTS', 'OTHER', 'NONE'),
    `cost_per_unit` DOUBLE(9,2),
    `in_kind_%` DOUBLE(9,2),

	-- Travel
    `num_travellers` INTEGER(4),
    `departure` DATE,
    `return` DATE,
    `fare` DOUBLE(9,2),
    `car_hire` DOUBLE(9,2),
    `meals` DOUBLE(9,2),
    `accommodation` DOUBLE(9,2),

	-- Facility  Hire
    `facility` ENUM('NONE', 'LABS_R_US'),
    `time_unit` ENUM('NONE', 'MINUTES', 'HOURS', 'DAYS', 'WEEKS', 'MONTHS'),

	-- Partner Organisation
    `organisation` ENUM('UMELB', '?', 'NONE'),
    PRIMARY KEY (`id`)

) Engine=InnoDB;

-- Annual Expenses
CREATE TABLE `annual_expense`
(
	`expense_id` INTEGER(6) UNSIGNED NOT NULL,
	`year` INTEGER(4) NOT NULL,
	`units` DOUBLE(9,2),
    PRIMARY KEY (`expense_id`, `year`)
) Engine=InnoDB;

-- Director Endorsement
CREATE TABLE `director_endorsement`
(
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`unit_id` INTEGER(6) UNSIGNED NOT NULL,
	`project_costs` BOOLEAN,
	`facilities` BOOLEAN,
	`blessing` BOOLEAN,
	`workload` BOOLEAN,
	`conflict_of_interest` BOOLEAN,
	`split` DOUBLE(9,2),
	`endorser_id` INTEGER(6) UNSIGNED,
	`endorsement` BOOLEAN,
	`endorsement_date` DATE,
    PRIMARY KEY (`project_id`, `unit_id`)
) Engine=InnoDB;

-- Unit
CREATE TABLE `unit`
(
	`id` INTEGER(6) UNSIGNED NOT NULL,
	`name` VARCHAR(256),
	`abbreviation` VARCHAR(256),
	`head_id` INTEGER(6) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) Engine=InnoDB;

-- College Endorsement
CREATE TABLE `college_endorsement`
(
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`college_id` INTEGER(6) UNSIGNED NOT NULL,
	`endorser_id` INTEGER(6) UNSIGNED,
	`endorsement` BOOLEAN,
	`endorsement_date` DATE,
    PRIMARY KEY (`project_id`, `college_id`)
) Engine=InnoDB;

-- College
CREATE TABLE `college`
(
	`id` INTEGER(6) UNSIGNED NOT NULL,
	`name` VARCHAR(256),
	`executive_dean_id` INTEGER(6) UNSIGNED,
    PRIMARY KEY (`id`)
) Engine=InnoDB;

-- Specify foreign keys
ALTER TABLE `contract` ADD FOREIGN KEY (`researcher_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `contract` ADD FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
-- ALTER TABLE `project` ADD FOREIGN KEY (`lead_researcher_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `project` ADD FOREIGN KEY (`rhd_unit_id`) REFERENCES `unit` (`id`);
ALTER TABLE `expense` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `annual_expense` ADD FOREIGN KEY (`expense_id`) REFERENCES `expense` (`id`);
ALTER TABLE `director_endorsement` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `director_endorsement` ADD FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`);
ALTER TABLE `director_endorsement` ADD FOREIGN KEY (`endorser_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `unit` ADD FOREIGN KEY (`head_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `college_endorsement` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `college_endorsement` ADD FOREIGN KEY (`college_id`) REFERENCES `college` (`id`);
ALTER TABLE `college_endorsement` ADD FOREIGN KEY (`endorser_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `college` ADD FOREIGN KEY (`executive_dean_id`) REFERENCES `researcher` (`staff_id`);

-- -----------------------------------------------------------------------------
-- Test Data
-- -----------------------------------------------------------------------------

source ./test_data.sql;
