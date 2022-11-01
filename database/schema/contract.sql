-- -----------------------------------------------------------------------------
--  Terms under which a researcher is employed
-- -----------------------------------------------------------------------------
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
    `classification_non_casual` ENUM(
		'NONE', 

		'LEVEL_A', 'LEVEL_B', 'LEVEL_C', 'LEVEL_D', 'LEVEL_E',

		'HEO_LEVEL_1', 'HEO_LEVEL_2', 'HEO_LEVEL_3', 'HEO_LEVEL_4',
			'HEO_LEVEL_5', 'HEO_LEVEL_6', 'HEO_LEVEL_7', 'HEO_LEVEL_8',
			'HEO_LEVEL_9', 'HEO_LEVEL_10',

		'RA_1', 'RA_2', 'RA_3'
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
    `staff_type_casual` ENUM(
		'NONE',

		'ACADEMIC', 'PROFESSIONAL', 'TEACHING', 'CONSERVATORIUM'
	),
    `classification_casual` ENUM(
		'NONE',

		'LECTURING', 'TUTORING_ACADEMIC', 'MARKING', 'MUSIC_ACCOMPANYING',
			'CLINICAL_NURSE', 'RESEARCH_ACADEMIC', 'OTHER_ACADEMIC',

		'RESEARCH', 'PROFESSIONAL', 'PIECEWORK',

		'ELICOS', 'IELTS', 'TUTORING', 'ATAS_TUTORING',

		'AMEB'
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
		'CJG01', 'CJG02', 'CJG03', 'CJG04',
		'CG01', 'CG01L', 'CG02', 'CG03', 'CG04', 'CG05', 'CG054', 'CG06', 'CG07', 'CG08', 'CG09', 'CG10',
		'CHMA1', 'CHMA2', 'CHMA3', 'CHMA4', 'CHMA5',

		'ELC1', 'CTE01', 'CTE02',
		'ELCIW', 'ELCTW', 'ELCEW', 'ELCIS', 'ELCTS', 'ELCES',
		'CTUT1', 'CTUT2',
		'RTUTU', 'RTUTG', 'RMENT', 'RGRP',

		'CMOES', 'CMTE1', 'CMTE2', 'CMTE3', 'CMTE4', 'CMTE5', 'CMTE6', 'CMTE7', 'CMTE8', 'CMSS', 'CMPE'
	),
    `classification_rhd` ENUM(
	    'NONE',

		'APA', 'APAI', 'TOP_UP'
	),
    `wage_expense` DOUBLE
		CHECK (`wage_expense` >= 0),
	`unit_id` INTEGER(6) UNSIGNED,
    PRIMARY KEY (`id`)
) Engine=InnoDB;


-- -----------------------------------------------------------------------------
-- Payment details for non-casual UTAS employees
--     static entry
-- -----------------------------------------------------------------------------
CREATE TABLE `non_casual_payment_details`
(
    `staff_type` ENUM(
		'NONE',

		'ACADEMIC', 'PROFESSIONAL', 'RESEARCH_ASSISTANT'
	),
    `classification` ENUM(
		'NONE', 

		'LEVEL_A', 'LEVEL_B', 'LEVEL_C', 'LEVEL_D', 'LEVEL_E',

		'HEO_LEVEL_1', 'HEO_LEVEL_2', 'HEO_LEVEL_3', 'HEO_LEVEL_4',
			'HEO_LEVEL_5', 'HEO_LEVEL_6', 'HEO_LEVEL_7', 'HEO_LEVEL_8',
			'HEO_LEVEL_9', 'HEO_LEVEL_10',

		'RA_1', 'RA_2', 'RA_3'
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
	`salary` DOUBLE
		CHECK (`salary` >= 0.00),
	PRIMARY KEY (`staff_type`, `classification`, `step`)
) Engine=InnoDB;


-- -----------------------------------------------------------------------------
-- Payment details for casual UTAS employees
--     static entry
-- -----------------------------------------------------------------------------
CREATE TABLE `casual_payment_details`
(
    `staff_type` ENUM(
		'NONE',

		'ACADEMIC', 'PROFESSIONAL', 'TEACHING', 'CONSERVATORIUM'
	),
    `classification` ENUM(
		'NONE',

		'LECTURING', 'TUTORING_ACADEMIC', 'MARKING', 'MUSIC_ACCOMPANYING',
			'CLINICAL_NURSE', 'RESEARCH_ACADEMIC', 'OTHER_ACADEMIC',

		'RESEARCH', 'PROFESSIONAL', 'PIECEWORK',

		'ELICOS', 'IELTS', 'TUTORING', 'ATAS_TUTORING',

		'AMEB'
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
		'CJG01', 'CJG02', 'CJG03', 'CJG04',
		'CG01', 'CG01L', 'CG02', 'CG03', 'CG04', 'CG05', 'CG054', 'CG06', 'CG07', 'CG08', 'CG09', 'CG10',
		'CHMA1', 'CHMA2', 'CHMA3', 'CHMA4', 'CHMA5',

		'ELC1', 'CTE01', 'CTE02',
		'ELCIW', 'ELCTW', 'ELCEW', 'ELCIS', 'ELCTS', 'ELCES',
		'CTUT1', 'CTUT2',
		'RTUTU', 'RTUTG', 'RMENT', 'RGRP',

		'CMOES', 'CMTE1', 'CMTE2', 'CMTE3', 'CMTE4', 'CMTE5', 'CMTE6', 'CMTE7', 'CMTE8', 'CMSS', 'CMPE'
	),
	`hourly_rate` DOUBLE
		CHECK (`hourly_rate` >= 0.00),
	PRIMARY KEY (`staff_type`, `classification`, `pay_code`)
) Engine=InnoDB;
