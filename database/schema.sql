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
DROP TABLE IF EXISTS `non_casual_payment_details`;
DROP TABLE IF EXISTS `casual_payment_details`;
SET FOREIGN_KEY_CHECKS = 1;


-- -----------------------------------------------------------------------------
-- Table schemata
-- -----------------------------------------------------------------------------

SOURCE ./schema/contract.sql;
SOURCE ./schema/contribution.sql;
SOURCE ./schema/entities.sql;
SOURCE ./schema/expense.sql;
SOURCE ./schema/project.sql;
SOURCE ./schema/researcher.sql;


-- -----------------------------------------------------------------------------
-- Foreign keys
-- -----------------------------------------------------------------------------

ALTER TABLE `contract` ADD FOREIGN KEY (`researcher_id`) REFERENCES `researcher` (`staff_id`);
ALTER TABLE `contract` ADD FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`);
ALTER TABLE `contract` ADD FOREIGN KEY (
	`staff_type_non_casual`,
	`classification_non_casual`,
	`step`
) REFERENCES `non_casual_payment_details` (
	`staff_type`,
	`classification`,
	`step`
);
ALTER TABLE `contract` ADD FOREIGN KEY (
	`staff_type_casual`,
	`classification_casual`,
	`step`
) REFERENCES `casual_payment_details` (
	`staff_type`,
	`classification`,
	`pay_code`
);

ALTER TABLE `contribution` ADD FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `contribution` ADD FOREIGN KEY (`on_cost_rate`) REFERENCES `salary_on_cost` (`type`);

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
-- Data
-- -----------------------------------------------------------------------------

SOURCE ./data.sql;


-- -----------------------------------------------------------------------------
-- Test Data
-- -----------------------------------------------------------------------------

SOURCE ./test_data.sql;
