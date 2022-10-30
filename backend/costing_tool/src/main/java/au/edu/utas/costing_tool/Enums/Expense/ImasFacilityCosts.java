package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ImasFacilityCosts implements FacilityCosts{
    RV_NAUTICA(
		"RV Nautica base daily charge. Special condition apply",
		2_025.00,
		Units.DAYS
	),
    SEABOTIX(
		"Seabotix Remote Operated Vehicle (operational days only, not travel or standby)",
		100.00,
		Units.DAYS
	),
    UNDERWATER_TOW_CAMERA(
		"Underwater Tow camera (operational days only, not travel or standby)",
		50.00,
		Units.DAYS
	),
    ISUZU_HIAB(
		"Isuzu truck and large HiAB",
		100.00,
		Units.DAYS
	),
    RV_TRISH_A(
		"RV Trish (option A)",
		230.00,
		Units.DAYS
	),
    RV_SEPIA_A(
		"RV Sepia (Option A)",
		350.00,
		Units.DAYS
	),
    RV_ROOGANAH_A(
		"RV Rooganah (Option A)",
		712.00,
		Units.DAYS
	),
    RV_LOWINNA_A(
		"RV Lowinna (Option A)",
		712.00,
		Units.DAYS
	),
    RV_MALLANA_A(
		"RV Mallana (Option A)",
		789.00,
		Units.DAYS
	),
    RV_MORANA_A(
		"RV Morana (Option A)",
		789.00,
		Units.DAYS
	),
    RV_TAROONA_A(
		"RV Taroona (Option A)",
		944.00,
		Units.DAYS
	),
    RV_NOTILUCA_A(
		"RV Notiluca (Option A) - ref PoolCar Manual",
		2_045.00,
		Units.DAYS
	),
    HYUNDAI_MAZDA_3_MAZDA_UTE_A(
		"Hyundai, Mazda 3, Mazda ute (Option A)",
		245.00,
		Units.DAYS
	),
    HILUX_RANGER_UTE_A(
		"Hilux or Ranger ute (Option A)",
		270.00,
		Units.DAYS
	),
    LAND_CRUISER_A(
		"Land Cruiser V8 (Option A)",
		320.00,
		Units.DAYS
	),
    FORD_F250_A(
		"Ford F250 (Option A)",
		395.00,
		Units.DAYS
	),
    RV_TRISH_B_FIXED(
		"RV Trish (Flag Fee, Option B) - Fixed",
		220.00,
		Units.DAYS
	),
    RV_SEPIA_B_FIXED(
		"RV Sepia (Flag Fee, Option B) - Fixed",
		250.00,
		Units.DAYS
	),
    RV_ROOGANAH_B_FIXED(
		"RV Rooganah (Flag Fee, Option B) - Fixed",
		500.00,
		Units.DAYS
	),
    RV_LOWINNA_B_FIXED(
		"RV Losinna (Flag Fee, Option B) - Fixed",
		500.00,
		Units.DAYS
	),
    RV_MALLANA_B_FIXED(
		"RV Mallana (Flag Fee, Option B) - Fixed",
		500.00,
		Units.DAYS
	),
    RV_MORANA_B_FIXED(
		"RV Morana (Flag Fee, Option B) - Fixed",
		560.00,
		Units.DAYS
	),
    RV_TAROONA_B_FIXED(
		"RV Taroona (Flag Fee, Option B) - Fixed",
		560.00,
		Units.DAYS
	),
    HYUNDAI_MAZDA_3_MAZDA_UTE_B_FIXED(
		"Hyundai, Mazda 3, Mazda ute (Flag Fee, Option B) - Fixed",
		60.00,
		Units.DAYS
	),
    HILUX_RANGER_UTE_B_FIXED(
		"Hilux or Ranger ute (Flag Fee, Option B) - Fixed",
		80.00,
		Units.DAYS
	),
    LAND_CRUISER_B_FIXED(
		"Land Cruiser V8 (Flag Fee, Option B) - Fixed",
		120.00,
		Units.DAYS
	),
    FORD_F250_B_FIXED(
		"Ford F250 (Flag Fee, Option B) - Fixed",
		160.00,
		Units.DAYS
	),
    RV_TRISH_B_VARIABLE(
		"RV Trish (Fuel per hr, Option B) - Variable",
		12.00,
		Units.HOURS
	),
    RV_SEPIA_B_VARIABLE(
		"RV Sepia (Fuel per hr, Option B) - Variable",
		40.00,
		Units.HOURS
	),
    RV_ROOGANAH_B_VARIABLE(
		"RV Rooganah (Fuel per hr, Option B) - Variable",
		50.00,
		Units.HOURS
	),
    RV_LOWINNA_B_VARIABLE(
		"RV Lowinna (Fuel per hr, Option B) - Variable",
		50.00,
		Units.HOURS
	),
    RV_MALLANA_B_VARIABLE(
		"RV Mallana (Fuel per hr, Option B) - Variable",
		60.00,
		Units.HOURS
	),
    RV_MORANA_B_VARIABLE(
		"RV Morana (Fuel per hr, Option B) - Variable",
		60.00,
		Units.HOURS
	),
    RV_TAROONA_B_VARIABLE(
		"RV Taroona (Fuel per hr, Option B) - Variable",
		60.00,
		Units.HOURS
	),
    HYUNDAI_MAZDA_3_MAZDA_UTE_B_VARIABLE(
		"Hyundai, Mazda 3, Mazda ute (Fuel per hr, Option B) - Variable",
		0.40,
		Units.KM
	),
    HILUX_RANGER_UTE_B_VARIABLE(
		"Hilux or Ranger ute (Fuel per hr, Option B) - Variable",
		0.40,
		Units.KM
	),
    LAND_CRUISER_B_VARIABLE(
		"Land Cruiser V8 (Fuel per hr, Option B) - Variable",
		0.50,
		Units.KM
	),
    FORD_F250_B_VARIABLE(
		"Ford F250 (Fuel per hr, Option B) - Variable",
		0.60,
		Units.KM
	),
    ICT_CHARGES(
		"ICT Charges (ALL PROJECTS)",
		3_030.00,
		Units.FTES
	),
    DATA_MANAGEMENT_CHARGES(
		"Data Management Charges (ALL PROJECTS)",
		0.015,
		Units.ANNUM_PERCENT
	),
    LAB_FEE_INTENSIVE(
		"Lab User Fee (Intensive)",
		1_250.00,
		Units.FTES
	),
    LAB_FEE_BASIC(
		"Lab User Fee (Basic/Student)",
		750.00,
		Units.FTES
	),
    DIVING(
		"Diving",
		62.00,
		Units.DIVE
	),

    ;
    String name;
    Double costPerUnit;
    Units  units;
}