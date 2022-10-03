/*
 * WARNING:
 * --------
 * ONLY RUN ONCE TO INITIALIZE DATABASE.
 * SUBSEQUENT EXECUTION OF THIS SCRIPT WILL DELETE ALL DATA FROM THE DATABASE
 */

-- Ensure costing tool database exists and we are using it
CREATE schema IF NOT EXISTS `warp`;
USE `warp`;

-- Remove existing tables in the warp database clashing with our new tables 
SET REFERENTIAL_INTEGRITY FALSE;
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
SET REFERENTIAL_INTEGRITY TRUE;


-- Researchers
CREATE TABLE `researcher`
(
	`staff_id` INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
	`title` ENUM('NONE', 'MR', 'MISS', 'MRS', 'MS', 'DR', 'PROF', 'TBA'),
	`first_name` VARCHAR(50),
	`last_name` VARCHAR(50),
	PRIMARY KEY (`staff_id`)
);


-- Contribution of project to contract hours
CREATE TABLE `contribution`
(
	`contract_id` INTEGER  NOT NULL,
	`project_id` INTEGER  NOT NULL,
	`role` VARCHAR(25),
    `in_kind_%` DOUBLE
		CHECK (`in_kind_%` BETWEEN 0 AND 100),
    `wage_adjustment` DOUBLE,						-- per contract or project?
    `on_cost_rate` DOUBLE,							-- per contract or project?
	PRIMARY KEY (`contract_id`, `project_id`)
);


-- Annual contribution of project to contract hours
CREATE TABLE `annual_contribution`
(
	`contract_id` INTEGER  NOT NULL,
	`project_id` INTEGER  NOT NULL,
	`year` INTEGER NOT NULL,
    `units` DOUBLE,
	PRIMARY KEY (`contract_id`, `project_id`, `year`)
);


--  Terms under which a researcher is employed
CREATE TABLE `contract`
(
    `id` INTEGER  NOT NULL UNIQUE AUTO_INCREMENT,
    `researcher_id` INTEGER  NOT NULL,
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
    `salary` DOUBLE
		CHECK (`salary` >= 0),
    `hourly_rate` DOUBLE
		CHECK (`hourly_rate` >= 0),
	`unit_id` INTEGER ,
    PRIMARY KEY (`id`)
);


-- Project
CREATE TABLE `project`
(
    `id` INTEGER  NOT NULL UNIQUE AUTO_INCREMENT,
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
    `utas_cash` DOUBLE,
    `utas_dvcr_cash` DOUBLE,
    `partner_cash` DOUBLE,
    `entity` ENUM('NONE', 'IMAS')												-- values?
		DEFAULT 'NONE',
    `crowd_funding_provider` ENUM('?', 'NONE'),									-- values?

	-- Specific to Category 1 projects
	`category_1_subtype` ENUM('NONE'),

	-- Specific to Consultancy projects
	`profit_margin` DOUBLE,

	-- Codes
	-- Could be one table, but not sure how to enforce maximum of three
	-- Field of Research
    `for_code_1` INTEGER,
    `for_%_1` DOUBLE
		CHECK (`for_%_1` BETWEEN 0 AND 100),
    `for_code_2` INTEGER,
    `for_%_2` DOUBLE
		CHECK (`for_%_2` BETWEEN 0 AND 100),
    `for_code_3` INTEGER,
    `for_%_3` DOUBLE
		CHECK (`for_%_3` BETWEEN 0 AND 100),
	-- Socio-Economic Objective
    `seo_code_1` INTEGER,
    `seo_%_1` DOUBLE
		CHECK (`seo_%_1`  BETWEEN 0 and 100),
    `seo_code_2` INTEGER,
    `seo_%_2` DOUBLE
		CHECK (`seo_%_2`  BETWEEN 0 and 100),
    `seo_code_3` INTEGER,
    `seo_%_3` DOUBLE
		CHECK (`seo_%_3`  BETWEEN 0 and 100),
	-- Types of Activity
	`applied_research` DOUBLE,
	`experimental_development` DOUBLE,
	`strategic_basic` DOUBLE,
	`pure_basic` DOUBLE,

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
    `rhd_unit_id` INTEGER ,

    PRIMARY KEY (`id`)
);

-- Expenses
CREATE TABLE `expense`
(
    `id` INTEGER  NOT NULL UNIQUE AUTO_INCREMENT,
    `project_id` INTEGER  NOT NULL,
    `expense_type` ENUM('TRAVEL', 'FACILITY_HIRE', 'EQUIPMENT', 'CONSUMABLES',
						'PARTNER_ORGANISATIONS', 'EXTERNAL_CONTRACTING',
						'RHD_NON-STIPEND_COSTS', 'OTHER', 'NONE'),
    `cost_per_unit` DOUBLE,
    `in_kind_%` DOUBLE,

	-- Travel
    `num_travellers` INTEGER,
    `departure` DATE,
    `return` DATE,
    `fare` DOUBLE,
    `car_hire` DOUBLE,
    `meals` DOUBLE,
    `accommodation` DOUBLE,

	-- Facility  Hire
    `facility` ENUM('NONE', 'LABS_R_US'),
    `time_unit` ENUM('NONE', 'MINUTES', 'HOURS', 'DAYS', 'WEEKS', 'MONTHS'),

	-- Partner Organisation
    `organisation` ENUM('UMELB', '?', 'NONE'),
    PRIMARY KEY (`id`)

);

-- Annual Expenses
CREATE TABLE `annual_expense`
(
	`expense_id` INTEGER  NOT NULL,
	`year` INTEGER NOT NULL,
	`units` DOUBLE,
    PRIMARY KEY (`expense_id`, `year`)
);

-- Director Endorsement
CREATE TABLE `director_endorsement`
(
	`project_id` INTEGER  NOT NULL,
	`unit_id` INTEGER  NOT NULL,
	`project_costs` BOOLEAN,
	`facilities` BOOLEAN,
	`blessing` BOOLEAN,
	`workload` BOOLEAN,
	`conflict_of_interest` BOOLEAN,
	`split` DOUBLE,
	`endorser_id` INTEGER ,
	`endorsement` BOOLEAN,
	`endorsement_date` DATE,
    PRIMARY KEY (`project_id`, `unit_id`)
);

-- Unit
CREATE TABLE `unit`
(
	`id` INTEGER  NOT NULL,
	`name` VARCHAR(256),
	`abbreviation` VARCHAR(256),
	`head_id` INTEGER  NOT NULL,
    PRIMARY KEY (`id`)
);

-- College Endorsement
CREATE TABLE `college_endorsement`
(
	`project_id` INTEGER  NOT NULL,
	`college_id` INTEGER  NOT NULL,
	`endorser_id` INTEGER ,
	`endorsement` BOOLEAN,
	`endorsement_date` DATE,
    PRIMARY KEY (`project_id`, `college_id`)
);

-- College
CREATE TABLE `college`
(
	`id` INTEGER  NOT NULL,
	`name` VARCHAR(256),
	`executive_dean_id` INTEGER ,
    PRIMARY KEY (`id`)
);

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