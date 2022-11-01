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
    -- `organisation` ENUM('UMELB', 'NONE'),
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

-- Audit fee
CREATE TABLE `audit_fee`
(
	`id` INTEGER(6) UNSIGNED NOT NULL AUTO_INCREMENT,
	`type` ENUM('EXTERNAL'),
	`cost_per_unit` DOUBLE,
	`units` ENUM('AUDITS'),
	`notes` VARCHAR(256),
	PRIMARY KEY (`id`)
) Engine=InnoDB;

