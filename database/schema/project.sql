-- -----------------------------------------------------------------------------
-- Planning information for research project
-- -----------------------------------------------------------------------------
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
    `crowd_funding_provider` ENUM('POZIBLE', 'NONE'),

	-- Specific to Category 1 projects
	`category_1_subtype` ENUM('NONE', 'ARC', 'NHMRC_DECRA_DORA_DIA', 'OTHER'),

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


-- -----------------------------------------------------------------------------
-- Digital endorsement for project by head of school/director of unit
--     currently unused
-- -----------------------------------------------------------------------------
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


-- -----------------------------------------------------------------------------
-- Digital endorsement for project by executive dean of college
--     currently unused
-- -----------------------------------------------------------------------------
CREATE TABLE `college_endorsement`
(
	`project_id` INTEGER(6) UNSIGNED NOT NULL,
	`college_id` INTEGER(6) UNSIGNED NOT NULL,
	`endorser_id` INTEGER(6) UNSIGNED,
	`endorsement` BOOLEAN,
	`endorsement_date` DATE,
    PRIMARY KEY (`project_id`, `college_id`)
) Engine=InnoDB;
