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
DROP TABLE IF EXISTS `facility_cost`;
DROP TABLE IF EXISTS `salary_on_cost`;
DROP TABLE IF EXISTS `audit_fee`;
SET FOREIGN_KEY_CHECKS = 1;


-- Researchers
CREATE TABLE `researcher`
(
	`staff_id` INTEGER(6) UNSIGNED NOT NULL UNIQUE,
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
    `contract_type` ENUM(
		'NONE',

		'NON_CASUAL', 'CASUAL', 'RHD'
	),
    `staff_type_non_casual` ENUM(
		'NONE',

		'ACADEMIC', 'PROFESSIONAL', 'RESEARCH_ASSISTANT'
	),
    `staff_type_casual` ENUM(
		'NONE',

		'ACADEMIC', 'PROFESSIONAL', 'TEACHING', 'CONSERVATORIUM'
	),
    `classification_non_casual` ENUM(
		'NONE', 

		'LEVEL_A', 'LEVEL_B', 'LEVEL_C', 'LEVEL_D', 'LEVEL_E',

		'HEO_LEVEL_1', 'HEO_LEVEL_2', 'HEO_LEVEL_3', 'HEO_LEVEL_4', 'HEO_LEVEL_5',
		'HEO_LEVEL_6', 'HEO_LEVEL_7', 'HEO_LEVEL_8', 'HEO_LEVEL_9', 'HEO_LEVEL_10',

		'RA_1', 'RA_2', 'RA_3'
	),
    `classification_casual` ENUM(
		'NONE',

		'LECTURING', 'TUTORING_ACADEMIC', 'MARKING', 'MUSIC_ACCOMPANYING',

		'CLINICAL_NURSE', 'RESEARCH_ACADEMIC', 'OTHER_ACADEMIC',

		'RESEARCH', 'PROFESSIONAL', 'PIECEWORK',

		'ELICOS', 'IELTS', 'TUTORING', 'ATAS_TUTORING',

		'AMEB'
	),
    `classification_rhd` ENUM(
	    'NONE',

		'APA', 'APAI', 'TOP_UP'
	),
    `pay_code` ENUM(
		'NONE',

		'CAL1', 'CAL2', 'CAL3', 'CAL4',
		'CAT1', 'CAT2', 'CAT3', 'CAT4',
		'CAM1', 'CAM2', 'CAM3',
		'CMU1', 'CMU2',
		'CAN1', 'CAN2', 'CAN3', 'CAN4',
		'CJRF',
		'CAO1', 'CAO2',

		'CRA1', 'CRA2', 'CRA3',
		'CJG1', 'CJG2', 'CJG3', 'CJG4',
		'CG01', 'CG01L', 'CG02', 'CG03', 'CG04', 'CG06', 'CG07', 'CG08', 'CG09', 'CG10', 'CG54',
		'CHMA1', 'CHMA2', 'CHMA3', 'CHMA4', 'CHMA5',

		'ELC1', 'CTE01', 'CTE02',
		'ELCIW', 'ELCTW', 'ELCEW', 'ELCIS', 'ELCTS', 'ELCES',
		'CTUT1', 'CTUT2',
		'RTUTU', 'RTUTG', 'RMENT', 'RGRP',

		'CMSS', 'CMOES', 'CMTE1'
	),
    `step` ENUM(
		'NONE',

		'A1', 'A2', 'A3', 'A4', 'A5', 'A6', 'A7', 'A8',
		'B1', 'B2', 'B3', 'B4', 'B5', 'B6',
		'C1', 'C2', 'C3', 'C4', 'C5', 'C6',
		'D1', 'D2', 'D3', 'D4',
		'E1', 'E2',

		'HEO_1_1', 'HEO_1_2', 'HEO_1_3',
		'HEO_2_1', 'HEO_2_2',
		'HEO_3_1', 'HEO_3_2', 'HEO_3_3', 'HEO_3_4', 'HEO_3_5',
		'HEO_4_1', 'HEO_4_2', 'HEO_4_3', 'HEO_4_4',
		'HEO_5_1', 'HEO_5_2', 'HEO_5_3', 'HEO_5_4', 'HEO_5_5',
		'HEO_6_1', 'HEO_6_2', 'HEO_6_3', 'HEO_6_4', 'HEO_6_5',

		'HEO_7_1', 'HEO_7_2', 'HEO_7_3', 'HEO_7_4', 'HEO_7_5',
		'HEO_8_1', 'HEO_8_2', 'HEO_8_3', 'HEO_8_4', 'HEO_8_5', 'HEO_8_6',
		'HEO_9_1', 'HEO_9_2', 'HEO_9_3', 'HEO_9_4', 'HEO_9_5',
		'HEO_10_1', 'HEO_10_2',

		'RA_1_1',
		'RA_2_1',
		'RA_3_1'
	),
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
    `project_id` INTEGER(6) UNSIGNED,
    `expense_type` ENUM(
		'NONE', 'TRAVEL', 'LABORATORY_HIRE', 'CONSUMABLES',
		'EQUIPMENT_PURCHASES', 'EXTERNAL_CONTRACTOR', 'OTHER_COSTS',
		'AUDIT_FEES', 'RHD_NON_STIPEND_COSTS', 'FACILITY_COSTS',
		'PARTNER_ORGANISATION'
	),
    `description` VARCHAR(256),
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

	-- Facility  costs
	`facility_cost_id` INTEGER(6) UNSIGNED,

	-- Audit fees
	`audit_fee_id` INTEGER(6) UNSIGNED,

	-- Partner Organisation
    `organisation` VARCHAR(256),
    -- `organisation` ENUM('UMELB', '?', 'NONE'),
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
	`id` INTEGER(6) UNSIGNED NOT NULL AUTO_INCREMENT,
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
	`id` INTEGER(6) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(256),
	`executive_dean_id` INTEGER(6) UNSIGNED,
    PRIMARY KEY (`id`)
) Engine=InnoDB;

-- Facility costs
CREATE TABLE `facility_cost`
(
	`id` INTEGER(6) UNSIGNED NOT NULL AUTO_INCREMENT,
	`facility` ENUM('NONE', 'ANIMAL', 'AMC', 'CSL', 'IMAS', 'MENZIES', 'TIA'),
	`name` VARCHAR(256),
	`cost_per_unit` DOUBLE,
	`units` ENUM(
		'NONE',
		-- time
		'HOURS', 'HOURS_60', 'DAYS', 'MONTHS', 'YEARS',
		-- size
		'KM', 'SQ_MTR', 'LITRES',
		-- mass
		'GRAMS',
		-- size/time
		'SQ_MTR_WEEK',
		-- size/item
		'HA_CROP',
		-- items/time
		'ANNUM_PERCENT', 'ANNUM_PERSON', 'ANNUM_PHONE', 'ANNUM_FTE',
			'ITEMS_WEEKS',
		-- events
		'CANCELLATIONS', 'TRANSFERS',
		-- items
		'ITEMS', 'UNITS', 'SAMPLES', 'ANIMALS', 'STRAINS', 'TIPS', 'PERSON',
			'PARTICIPANTS', 'FTES',
		-- items/event
		'DIVE', 
		-- collections
		'BATCHES', 'BLOCKS', 'BAGS', 'ROW_12', 'ROW_8', 'PLATE', 'BOX_96',
			'DISCS'
	),
	`notes` VARCHAR(256),
	PRIMARY KEY (`id`)
) Engine=InnoDB;

-- Salary on cost rate
CREATE TABLE `salary_on_cost`
(
	`type` ENUM('PERMANENT_ADDITIONAL', 'PERMANENT_GOV', 'CASUAL'),
	`year` INTEGER(4),
	`percent` DOUBLE,
	PRIMARY KEY (`type`, `year`)
) Engine=InnoDB;

-- Salary on cost rate
CREATE TABLE `audit_fee`
(
	`id` INTEGER(6) UNSIGNED NOT NULL AUTO_INCREMENT,
	`type` ENUM('EXTERNAL'),
	`cost_per_unit` DOUBLE,
	`units` ENUM('AUDITS'),
	`notes` VARCHAR(256),
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
ALTER TABLE `expense` ADD FOREIGN KEY (`facility_cost_id`) REFERENCES `facility_cost` (`id`);
ALTER TABLE `expense` ADD FOREIGN KEY (`audit_fee_id`) REFERENCES `audit_fee` (`id`);
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

source ./data.sql;
source ./test_data.sql;
