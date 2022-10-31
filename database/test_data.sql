/*
 * Test data for warp database.
 */

-- Switch to warp database
USE warp;

-- Researchers
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (1, 'DR', 'First', 'Last');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (2, 'DR', 'Test', 'User');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (3, 'PROF', 'Test', 'Professor');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (4, 'MR', 'Another', 'Last');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (5, 'MS', 'Another', 'Last');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (6, 'MR', 'First', 'Also');


-- Units
INSERT INTO `unit`
(
	`id`,
	`name`,
	`abbreviation`,
	`head_id`
) VALUES (
	1,
	'Institute for Marine and Antartic Studies',
	'IMAS',
	1
);


-- Colleges
INSERT INTO `college`
(
	`id`,
	`name`,
	`executive_dean_id`
) VALUES (
	1,
	'College of Sciences and Engineering',
	1
);


-- Project
INSERT INTO `project`
(
	`name`,
	`description`,
	-- `lead_researcher_id`,
	`lead_researcher_name`,
	`lead_researcher_organisation`,
	`category`,
	`amc_menzies`,
	`amc_national_centre`,
	`start_date`,
	`end_date`,
	`year_end_type`,
	`utas_cash`,
	`utas_dvcr_cash`,
	`partner_cash`,
	`entity`,
	`crowd_funding_provider`,

	-- Specific to Category 1 projects
	`category_1_subtype`,

	-- Specific to Consultancy projects
	`profit_margin`,

	-- Details
	`herdc`,
	`funding_body`,
	`scheme`,
	`contact_name`,
	`contact_email`,

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
	`human`, `human_ref`,
	`animal`, `animal_ref`,
	`drugs`,
	`clinical_trial`,

	-- Endorsements
	-- CI Endoresement
	`ci_endorsement`, `ci_endorsement_date`,
	`risk_assessment`,
	`utas_insurance`,
	`defence_strategic_goods`,
	`conflict_of_interest`,
	`foreign_principals`,

	-- External Researchers
	`external_researchers`,

	-- RHD Information
	`rhd_involvement`,
	`rhd_unit_id`
) VALUES (
	'Test Project',									-- project name
	'A project to test the system.',				-- project description
	-- '1',											-- lead researcher id
	'Dr. First Last',								-- lead researcher name
	'IMAS',											-- lead researcher organisation
	'ONE',											-- category
	'NONE',											-- AMC/Menzies
	'NONE',											-- AMC National Centre
	'2022-01-31',									-- start date
	'2023-12-31',									-- end date
	'Calendar',										-- year-end type
	'25000.50',										-- UTAS cash
	'0.00',											-- UTAS DVC-R cash
	'0.00',											-- partner organisation cash
	'IMAS',											-- entity
	'NONE',											-- crowd funding provider

	'ARC',											-- category 1 subtype
	'50.00',										-- profit margin

	-- Details
	'herdc string',									-- herdc
	'funding body name',							-- funding body name
	'scheme string',								-- scheme
	'Mr. Contact Name',								-- contact name
	'Contact.Name@email.com',						-- contact email

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
	FALSE, NULL,									-- human
	FALSE, NULL,									-- animals
	FALSE,											-- drugs
	FALSE,											-- clinical trial

	-- Endorsements
	-- CI Endoresement
	TRUE, '2022-01-01',								-- CI endorsement
	TRUE,											-- risk assessment
	TRUE,											-- UTAS insurance
	TRUE,											-- defence strategic goods
	TRUE,											-- conflict of interest
	TRUE,											-- foregin principals

	-- External Researchers
	'Dr. First Last,ANU;Prof. Also Researcher,UNSW;Mrs. Anne Researcher,UQ',

	-- RHD Information
	'SCHOLARSHIP',
	'1'
);


--  Terms under which a researcher is employed
INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'1',											-- contract id
	'1',											-- researcher id
	'NON_CASUAL',									-- contract type
	'ACADEMIC',										-- non-casual staff type
	NULL,											-- casual staff type
	'LEVEL_A',										-- non-casual classification
	NULL,											-- casual classification
	NULL,											-- RHD classification
	'A1',											-- step
	NULL,											-- pay code
	'100000.00',									-- salary
	NULL											-- hourly rate
);

INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'2',											-- contract id
	'1',											-- researcher id
	'CASUAL',										-- contract type
	NULL,											-- non-casual staff type
	'PROFESSIONAL',									-- casual staff type
	NULL,											-- non-casual classification
	'RESEARCH_ACADEMIC',							-- casual classification
	NULL,											-- RHD classification
	NULL,											-- step
	'CRA1',											-- pay code
	NULL,											-- salary
	'50.00'											-- hourly rate
);

INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'3',											-- contract id
	'1',											-- researcher id
	'RHD',											-- contract type
	NULL,											-- non-casual staff type
	NULL,											-- casual staff type
	NULL,											-- non-casual classification
	NULL,											-- casual classification
	'APA',											-- RHD classification
	NULL,											-- step
	NULL,											-- pay code
	'25000.00',										-- salary
	NULL											-- hourly rate
);

INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'4',											-- contract id
	'3',											-- researcher id
	'NON_CASUAL',									-- contract type
	'ACADEMIC',										-- non-casual staff type
	NULL,											-- casual staff type
	'LEVEL_E',											-- non-casual classification
	NULL,											-- casual classification
	NULL,											-- RHD classification
	NULL,											-- step
	NULL,											-- pay code
	'150000.00',									-- salary
	NULL											-- hourly rate
);

INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'5',											-- contract id
	'5',											-- researcher id
	'RHD',											-- contract type
	NULL,											-- non-casual staff type
	NULL,											-- casual staff type
	NULL,											-- non-casual classification
	NULL,											-- casual classification
	'APA',											-- RHD classification
	NULL,											-- step
	NULL,											-- pay code
	'40000.00',										-- salary
	NULL											-- hourly rate
);

INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'6',											-- contract id
	'6',											-- researcher id
	'CASUAL',										-- contract type
	NULL,											-- non-casual staff type
	'PROFESSIONAL',									-- casual staff type
	NULL,											-- non-casual classification
	'LECTURING',									-- casual classification
	NULL,											-- RHD classification
	NULL,											-- step
	NULL,											-- pay code
	NULL,											-- salary
	'70.0'											-- hourly rate
);

INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'7',											-- contract id
	'2',											-- researcher id
	'NON_CASUAL',									-- contract type
	'ACADEMIC',										-- non-casual staff type
	NULL,											-- casual staff type
	'LEVEL_B',										-- non-casual classification
	NULL,											-- casual classification
	NULL,											-- RHD classification
	NULL,											-- step
	NULL,											-- pay code
	'90000.0',										-- salary
	NULL											-- hourly rate
);

INSERT INTO `contract`
(
	`id`,
	`researcher_id`,
	`contract_type`,
	`staff_type_non_casual`,
	`staff_type_casual`,
	`classification_non_casual`,
	`classification_casual`,
	`classification_rhd`,
	`step`,
	`pay_code`,
	`salary`,
	`hourly_rate`
) VALUES (
	'8',											-- contract id
	'4',											-- researcher id
	'NON_CASUAL',									-- contract type
	'ACADEMIC',										-- non-casual staff type
	NULL,											-- casual staff type
	'LEVEL_B',										-- non-casual classification
	NULL,											-- casual classification
	NULL,											-- RHD classification
	NULL,											-- step
	NULL,											-- pay code
	'90000.0',										-- salary
	NULL											-- hourly rate
);


-- contribution of project to contract
INSERT INTO `contribution`
(
	`contract_id`,
	`project_id`,
	`role`,
	`in_kind_%`,
	`wage_adjustment`,
	`on_cost_rate`
) VALUES (
	'1',											-- contract id
	'1',											-- project id
	'CI',											-- project role
	'50.00',										-- in kind percent
	'1000.00',										-- wage adjustment
	'1'												-- on cost rate
);
/*
INSERT INTO `contribution`
(
	`contract_id`,
	`project_id`,
	`role`,
	`in_kind_%`
) VALUES (
	'2',											-- contract id
	'1',											-- project id
	'RA',											-- project role
	'0.00'											-- in kind percent
);
*/
INSERT INTO `contribution`
(
	`contract_id`,
	`project_id`,
	`role`,
	`in_kind_%`,
	`wage_adjustment`,
	`on_cost_rate`
) VALUES (
	'3',											-- contract id
	'1',											-- project id
	'RHD Student',									-- project role
	'100.00',										-- in kind percent
	'500.00',										-- wage adjustment
	'1'												-- on cost rate
);

INSERT INTO `contribution`
(
	`contract_id`,
	`project_id`,
	`role`,
	`in_kind_%`,
	`wage_adjustment`,
	`on_cost_rate`
) VALUES (
	'4',											-- contract id
	'1',											-- project id
	'Advisor',										-- project role
	'10.00',										-- in kind percent
	'1000.00',										-- wage adjustment
	'1'												-- on cost rate
);




-- annual contribution of project to contract
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
INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
	`units`
) VALUES (
	'1',											-- contract id
	'1',											-- project id
	'2023',											-- year
	'50.05'											-- units (here fte)
);

/*
INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
	`units`
) VALUES (
	'2',											-- contract id
	'1',											-- project id
	'2022',											-- year
	'450.00'										-- units (here hours)
);
INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
	`units`
) VALUES (
	'2',											-- contract id
	'1',											-- project id
	'2023',											-- year
	'600.20'										-- units (here hours)
);
*/

INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
	`units`
) VALUES (
	'3',											-- contract id
	'1',											-- project id
	'2022',											-- year
	'20.00'											-- units (here fte)
);
INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
	`units`
) VALUES (
	'3',											-- contract id
	'1',											-- project id
	'2023',											-- year
	'80.00'											-- units (here fte)
);

INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
	`units`
) VALUES (
	'4',											-- contract id
	'1',											-- project id
	'2022',											-- year
	'10.00'											-- units (here fte)
);
INSERT INTO `annual_contribution`
(
	`contract_id`,
	`project_id`,
	`year`,
	`units`
) VALUES (
	'4',											-- contract id
	'1',											-- project id
	'2023',											-- year
	'20.00'											-- units (here fte)
);


-- Expenses
INSERT INTO `expense`
(
	`project_id`,
	`expense_type`,
	`description`,
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
	`facility_cost_id`
) VALUES (
	1,													-- project id
	'FACILITY_COSTS',									-- expense type
	'test facility cost',								-- description
	NULL,												-- cost per unit
	70.00,												-- in kind percent

	-- Travel
	NULL,												-- number of travelers
	NULL,												-- departure date
	NULL,												-- return date
	NULL,												-- fare
	NULL,												-- car hire price
	NULL,												-- meal price
	NULL,												-- accommodation price

	-- Facility  Hire
	1													-- facility cost id
);
INSERT INTO `expense`
(
	`project_id`,
	`expense_type`,
	`description`,
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
	`facility_cost_id`
) VALUES (
	1,													-- project id
	'NONE',												-- expense type
	'test facility cost',								-- description
	NULL,												-- cost per unit
	70.00,												-- in kind percent

	-- Travel
	NULL,												-- number of travelers
	NULL,												-- departure date
	NULL,												-- return date
	NULL,												-- fare
	NULL,												-- car hire price
	NULL,												-- meal price
	NULL,												-- accommodation price

	-- Facility  Hire
	NULL												-- facility cost id
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


-- Director Endorsements
INSERT INTO `director_endorsement`
(
	`project_id`,
	`unit_id`,
	`project_costs`,
	`facilities`,
	`blessing`,
	`workload`,
	`conflict_of_interest`,
	`split`,
	`endorser_id`,
	`endorsement`,
	`endorsement_date`
) VALUES (
	1,											-- project id
	1,											-- unit id
	TRUE,										-- project costs
	TRUE,										-- faciltiies
	TRUE,										-- blessing
	TRUE,										-- workload
	TRUE,										-- conflict of interest
	20.00,										-- split
	1,											-- endorser id
	TRUE,										-- endorserment
	'2022-01-01'								-- endorserment date
);

-- College Endorsements
INSERT INTO `college_endorsement`
(
	`project_id`,
	`college_id`,
	`endorser_id`,
	`endorsement`,
	`endorsement_date`
) VALUES (
	1,											-- project id
	1,											-- college id
	1,											-- endorser id
	TRUE,										-- endorserment
	'2022-01-02'								-- endorserment date
);
