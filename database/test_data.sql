/*
 * Test data for warp database.
 */

-- Switch to warp database
USE warp;

-- Researchers
INSERT INTO `researcher` (`title`, `first_name`, `last_name`)
VALUES ('Dr', 'First', 'Last');


-- Project
INSERT INTO `project`
(
    `name`,
    `description`,
    `lead_researcher_id`,
    `category`,
    `amc_menzies`,
    `start_date`,
	`end_date`,
    `year_end_type`,
    `utas_cash`,
    `partner_cash`,
    `entity`,
    `crowd_funding_provider`,

	-- Specific to Category 1 projects
	`category_1_subtype`,

	-- Specific to Consultancy projects
	`profit_margin`,

	-- Codes
	-- Field of Research
    `for_code_1`, `for_%_1`,
    `for_code_2`, `for_%_2`,
    `for_code_3`, `for_%_3`,
	-- Socio-Economic Objective
    `seo_code_1`, `seo_%_1`,
    `seo_code_2`, `seo_%_2`,
    `seo_code_3`, `seo_%_3`,
	-- Types of Activity
	`applied_research`,
	`experimental_development`,
	`strategic_basic`,
	`pure_basic`,

	-- Ethics
    `human_medical`, `human_medical_ref`,
    `human_social_science`, `human_social_science_ref`,
    `animals`, `animals_ref`,
    `gmo`, `gmo_ref`,
    `radiation`, `radiation_ref`,
    `carcinogen_teratogen`, `carcinogen_teratogen_ref`,

	-- Endorsements
	-- CI Endoresement
    `ci_endorsement`, `ci_endorsement_date`,
    `risk_assessment`,
    `risks_managed`,
    `utas_insurance`,
    `defence_strategic_goods`,
    `conflict_of_interest`,
    `foreign_principals`,
	-- Diretor Endoresement
	`organisational_unit_1`, `organisational_unit_1_split`,
    `director_endorsement_1`, `director_endorsement_1_date`,
	`organisational_unit_2`, `organisational_unit_2_split`,
    `director_endorsement_2`, `director_endorsement_2_date`,
	-- College Endoresement
    `college_endorsement`, `college_endorsement_date`
) VALUES (
    'Test Project',									-- project name
    'A project to test the system.',				-- project description
    '1',											-- lead researcher id
    'ONE',											-- category
    'NONE',											-- AMC/Menzies
    '2022-01-31',									-- start date
	'2023-12-31',									-- end date
    'Calendar',										-- year-end type
    '25000.50',										-- UTAS cash
    '0.00',											-- partner organisation cash
    'IMAS',											-- entity
    'NONE',											-- crowd funding provider

    'NONE',											-- category 1 subtype
	'50.00',										-- profit margin

	-- Codes
	-- Field of Research
    '000000', '33.3',
	'123456', '66.7',
	NULL, NULL,
	-- Socio-Economic Objective
	'000001', '25.0',
	'100000', '25.0',
	'654321', '50.0',
	-- Types of Activity
	'20.00',										-- applied research
	'70.00',										-- experimental development
	'10.00',										-- strategic basic
	'0.00',											-- pure basic

	-- Ethics
	FALSE, NULL,								-- human medical
	FALSE, NULL,								-- human social science
	FALSE, NULL,								-- animals
	FALSE, NULL,								-- GMO
	FALSE, NULL,								-- radiation
	FALSE, NULL,								-- carcinogen/teratogen

	-- Endorsements
	-- CI Endoresement
	TRUE, '2022-01-01',							-- CI endorsement
	TRUE,											-- risk assessment
	TRUE,											-- managed risks
	TRUE,											-- UTAS insurance
	TRUE,											-- defence strategic goods
	TRUE,											-- conflict of interest
	TRUE,											-- foregin principles
	-- Diretor Endoresement
	'IMAS', '100.00', TRUE, '2022-01-12',			-- organisational unit 1
	NULL, NULL, NULL, NULL,					-- organisational unit 2
	-- College Endoresement
	NULL, NULL
);


--  Terms under which a researcher is employed
INSERT INTO `contract`
(
    `researcher_id`,
    `staff_type`,
    `classification_non_casual`,
    `classification_casual`,
    `classification_rhd`,
    `pay_code`,
    `step`,
    `salary`,
    `hourly_rate`,
    `wage_adjustment`,
    `on_cost_rate`
) VALUES (
    '1',											-- researcher id
    'NON_CASUAL',									-- staff type
    'A',											-- non-casual classification
    NULL,											-- casual classification
    NULL,											-- RHD classification
    '1',											-- pay code
    '1',											-- step
    '100000.00',									-- salary
    NULL,											-- hourly rate
    '1.00',											-- wage adjustment
    '1.00'											-- on cost rate
);


-- contribution of project to contract
INSERT INTO `contribution`
(
	`contract_id`,
	`project_id`,
	`role`,
    `in_kind_%`
) VALUES (
	'1',											-- contract id
	'1',											-- project id
	'CI',											-- project role
    '50.00'											-- in kind percent
);


-- contribution of project to contract
INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
    `units`
) VALUES (
	'1',											-- contract id
	'1',											-- project id
	'2022',											-- year
    '100.00'										-- units (here fte)
);


-- Expenses
INSERT INTO `expense`
(
	`project_id`,
    `expense_type`,
    `cost_per_unit`,
    `in_kind_%`,

	-- Travel
    `num_travellers`,
    `departure`,
    `return`,
    `fare`,
    `car_hire`,
    `meals`,
    `accommodation`,

	-- Facility  Hire
    `facility`,
    `time_unit`,
    `organisation`
) VALUES (
    1,													-- project id
    'FACILITY_HIRE',									-- expense type
    '20.0',												-- cost per unit
    '70.00',											-- in kind percent

	-- Travel
    NULL,												-- number of travelers
    NULL,												-- departure date
    NULL,												-- return date
    NULL,												-- fare
    NULL,												-- car hire price
    NULL,												-- meal price
    NULL,												-- accommodation price

	-- Facility  Hire
	'LABS_R_US',										-- facility
    'days',												-- time unit

	-- Partner Organisation
    NULL												-- organisation
);


INSERT INTO `annual_expense`
(
	`expense_id`,
	`year`,
	`units`
) VALUES (
	'1',												-- expense id
	'2022',												-- year
	'30'												-- units
);
