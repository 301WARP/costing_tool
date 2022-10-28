package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum MenziesFacilityCosts implements FacilityCosts{
    // Menzies Facility Costs
    CLINIC_LARGE(
		"Clinic user 3rd floor - Large",
		600.00,
		Units.YEARS
	),
    CLINIC_MEDIUM(
		"Clinic user 3rd floor - Medium",
		350.00,
		Units.YEARS
	),
    CLINIC_SMALL(
		"Clinic user 3rd floor - Small",
		200.00,
		Units.YEARS
	),
    PHLEBOTOMY_TRAINING(
		"Phlebotomy training - Internal",
		400.00,
		Units.PERSON
	),
    RESEARCH_NURSE(
		"Research Nurse - Phlebotemis (HEO 5)",
		0.00,
		Units.HOURS
	),
    LAB(
		"Lab users 5th floor",
		100.00,
		Units.ANNUM_PERSON
	),
    CYTOMETER_SORTER_UTAS(
		"Flow cytometer sorter - UTAS reseracher rate",
		50.00,
		Units.HOURS
	),
    CYTOMETER_SORTER_NON_UTAS(
		"Flow cytometer sorter - non-UTAS user rate",
		100.00,
		Units.HOURS
	),
    CYTOMETER_ANALYSER_UTAS(
		"Flow cytometer analyser - UTAS reseracher rate",
		30.00,
		Units.HOURS
	),
    CYTOMETER_ANALYSER_UTAS_ASSISTED(
		"Flow cytometer analyser - Assisted UTAS reseracher rate",
		60.00,
		Units.HOURS
	),
    CYTOMETER_ANALYSER_NON_UTAS(
		"Flow cytometer analyser - Non-UTAS user rate",
		60.00,
		Units.HOURS
	),
    CYTOMETER_ANALYSER_NON_UTAS_ASSISTED(
		"Flow cytometer analyser - Assisted Non-UTAS user rate",
		120.00,
		Units.HOURS
	),
    FACS(
		"5mL non-sterile FACS tubes (500 per bag)",
		35.00,
		Units.HOURS
	),
    DATA_ANALYSIS_TRAINING(
		"Data analysis training - Non-UTAS user rate",
		50.00,
		Units.HOURS
	),
    MICROSCOPE_PE(
		"PE Spinning Disk microscope",
		15.00,
		Units.HOURS
	),
    MICROSCOPE_ZEISS(
		"Zeiss Confocal microscope",
		8.00,
		Units.HOURS
	),
    MICROSCOPE_NIKON(
		"Nikon Live Cell microscope",
		8.00,
		Units.HOURS
	),
    MICROSCOPE_OLYMPUS(
		"Olympus BX50 microscope",
		8.00,
		Units.HOURS
	),
    MICROSCOPE_LEICA_LB2(
		"Leica DM LB2 microscope",
		8.00,
		Units.HOURS
	),
    MICROSCOPE_LEICA_IRB(
		"Leica DM LB2 microscope",
		12.50,
		Units.HOURS
	),
    MICROSCOPE_WIDEFIELD(
		"Widefield Microscope (Leica and Olympus)",
		35.00,
		Units.HOURS
	),
    MICROSCOPE_MULTIPHOTON(
		"Scientifica Multiphoton microscope",
		40.00,
		Units.HOURS
	),
    MICROSCOPE_TRANSMISSION_ELECTRON(
		"Transmission Electron microscope",
		50.00,
		Units.HOURS
	),
    PROECESSING_COST(
		"Cost of processing",
		50.00,
		Units.SAMPLES
	),
    SECTIONING_COST(
		"Cost of sectioning",
		30.00,
		Units.HOURS
	),
    CONTRASTING_GRID_COST(
		"Cost of contrasting grids with heavy metals",
		50.00,
		Units.BLOCKS
	),
    COMPUTER_LAPTOP(
		"Std. Computer/laptop (inc. software)",
		1_800.00,
		Units.UNITS
	),
    COMPUTER_DESKTOP(
		"Std. Computer/desktop (inc. software)",
		1_600.00,
		Units.UNITS
	),
    WIDESCREEN_LCD(
		"Widescreen LCD",
		400.00,
		Units.UNITS
	),
    STATIONERY(
		"Stationery",
		1_300.00,
		Units.YEARS
	),
    TELEPHONE_COST(
		"Telephone cost - per staff",
		450.00,
		Units.YEARS
	),
    CLEAN_MAIL(
		"\"Clean Mail\" postage",
		0.95,
		Units.ITEMS
	),
    UNCLEAN_MAIL(
		"Postage (not within the clean mail format)",
		1.00,
		Units.ITEMS
	),
    PARCEL_SATCHEL_500G(
		"Parcel Post satchel 500g",
		8.25,
		Units.ITEMS
	),
    PARCEL_SATCHEL_3KG(
		"Parcel Post satchel 3kg",
		13.40,
		Units.ITEMS
	),
    LETTER_C5(
		"Express Post Letter C5 (500 g)",
		6.00,
		Units.ITEMS
	),
    LETTER_B4(
		"Express Post Letter B4 (500g)",
		7.20,
		Units.ITEMS
	),
    EXPRESS_SATCHEL_500G(
		"Express Post satchel 500g",
		10.55,
		Units.ITEMS
	),
    EXPRESS_SATCHEL_3KG(
		"Express Post satchel 3kg",
		14.00,
		Units.ITEMS
	),
    LETTER_LARGE(
		"Large Letters - C5 and B4 <20mm, 260mm x 360mm",
		0.00,
		Units.NONE
	),
    // TODO(Andrew): what are these?
    MASS_125G(
		"up to 125g",
		1.90,
		Units.ITEMS
	),
    MASS_126G_250G(
		"126g - 250g",
		2.85,
		Units.ITEMS
	),
    MASS_251G_500G(
		"251g - 500g",
		4.75,
		Units.ITEMS
	),
    PARCEL_REGULAR(
		"Regular Parcels >20mm",
		0.00,
		Units.NONE
	),
    MASS_500G(
		"up to 500g",
		6.43,
		Units.ITEMS
	),
    MASS_500G_22KG_TASMANIA(
		"500g - 22kg (within Tasmania only)",
		7.49,
		Units.ITEMS
	),
    MASS_500G_22KG_INTERSTATE(
		"500g - 22kg (Interstate)",
		12.85,
		Units.ITEMS
	),
    MAILING_TUBES(
		"Mailing Tubes - Parcel - Airmail up to 500g - Standard",
		0.00,
		Units.NONE
	),
    NZ_ZONE(
		"NZ - (Asia Pacific) - Zone 1",
		19.73,
		Units.ITEMS
	),
    CHINA_ZONE(
		"CHINA - (Asia Pacific - Zone 2",
		21.64,
		Units.ITEMS
	),
    USA_ZONE(
		"USA - (Rest of the world) - Zone 4",
		23.72,
		Units.ITEMS
	),
    REPLY_PAID(
		"Reply Paid",
		0.00,
		Units.NONE
	),
    SMALL_DL(
		"Small DL",
		0.65,
		Units.ITEMS
	),
    // TODO(Andrew): repeat of MASS_125G, etc. here
    PACKAGES_500G(
		"Packages up to 500G",
		7.45,
		Units.ITEMS
	),
    QUESTIONNAIRE_PRINTING(
		"Questionnaire printing",
		0.60,
		Units.ITEMS
	),
    BLOOD_SAMPLE(
		"Blood sample - test tube",
		2.00,
		Units.PARTICIPANTS
	),
    BMD_SCAN(
		"Bone Mineral Density (BMD) scan (Menzies equipment)",
		80.00,
		Units.ITEMS
	),
    MRI(
		"MRI",
		400.00,
		Units.ITEMS
	),
    X_RAY(
		"X-ray (Hand, hip, knee)",
		120.00,
		Units.ITEMS
	),
    CT_SCAN_HR(
		"High Resolution CT Scan",
		300.00,
		Units.ITEMS
	),
    CT_SCAN_MICRO(
		"MicroCT Scan (Menzies equipment)",
		300.00,
		Units.HOURS
	),
    CATERING(
		"Catering",
		7.50,
		Units.PARTICIPANTS
	),
    FLEET_VEHICLE(
		"Fleet Vehicle (Institute car more than 2 liters)",
		0.66,
		Units.KM
	),
    HIRE_VEHICLE(
		"Hire Vehicle Large Sedan",
		60.00,
		Units.DAYS
	),
    TELEFORM_DESIGN(
		"Teleform questionnaire design (based upon 10 pages per questionnaire)",
		60.00,
		Units.HOURS_60
	),
    TELEFORM_VERIFICATION(
		"Teleform scanning and verifying (based upon 10 pages per questionnaire)",
		40.00,
		Units.HOURS
	),
    STATISTICAL_SUPPORT_SENIOR(
		"Statistical support - Senior biostatistician",
		150.00,
		Units.HOURS
	),
    STATISTICAL_SUPPORT_OTHER(
		"Statistical support - Other biostatisticians",
		100.00,
		Units.HOURS
	),
    HEALTH_ECONOMICS_SUPPORT_PROFESSOR(
		"Health economics support - Professsor",
		150.00,
		Units.HOURS
	),
    HEALTH_ECONOMICS_SUPPORT_OTHER(
		"Health economics support - Other health economists",
		100.00,
		Units.HOURS
	),
    DATABASE_DEVELOPMENT(
		"Database development and support",
		75.00,
		Units.HOURS
	),
    GENEALOGIST(
		"Genealogist",
		60.00,
		Units.HOURS
	),

    ;
    String name;
    Double costPerUnit;
    Units units;
}