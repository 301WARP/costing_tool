package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum AmcFacilityCosts implements FacilityCosts {
    AUDITORIUM(
        "Auditorium",
        352.00,
        Units.NONE
    ),
    AUV_REMUS(
        "AUV - Remus",
        2_042.00,
        Units.DAYS
    ),
    BEAUTY_POINT(
        "Beauty Point Consumables (FRC)",
        299.00,
        Units.NONE
    ),
    BLUEFIN_CHARTER(
        "Bluefin - bare boat charter",
        6_574.00,
        Units.NONE
    ),
    BLUEFIN_OVERNIGHT(
        "Bluefin (full cost - overnight)",
        10_000.00,
        Units.DAYS
    ),
    BLUEFIN_DAY_SAILING(
        "Bluefin (full cost - day sailing)",
        7_500.00,
        Units.DAYS
    ),
    BLUEFIN_REPAIRS(
        "Bluefin repairs & maint",
        784.00,
        Units.NONE
    ),
    BRIDGE_OP_FACILITY(
        "Bridge Op Cubicles - facility hired instead of Bridge Simulator",
        4_000.00,
        Units.NONE
    ),
    BRIDGE_OP_SHORT_COURSE(
        "Bridge Op. Cubicles - Short Courses",
        2_382.00,
        Units.NONE
	),
    BRIDGE_OP_STAND_ALONE(
        "Bridge Op. Cubicles - Single Bridge stand alone",
        3_485.00,
        Units.NONE
	),
    BRIDGE_OP_ADD_ON(
        "Bridge Op. Cubicles - Single Bridge, as add-on to hire of main bridge",
        1_067.00,
        Units.NONE
	),
    CAVITATION_TUNNEL(
        "Cavitation Tunnel",
        4_170.00,
        Units.DAYS
	),
    CAVITATION_TUNNEL_TECHNICAL(
        "Cavitation Tunnel - Technical",
        638.00,
        Units.NONE
	),
    CLASSROOMS_LEVEL_A(
        "Classrooms - level A",
        189.00,
        Units.NONE
	),
    CLASSROOMS_LEVEL_B(
        "Classrooms - level B",
        175.00,
        Units.NONE
	),
    CLASSROOMS_LEVEL_C(
        "Classrooms - level C",
        112.00,
        Units.NONE
	),
    COMPUTER_LABS(
        "Computer Labs",
        351.22,
        Units.NONE
	),
    CONSUMABLES(
        "Consumables",
        215.43,
        Units.NONE
	),
    COUNCIL_ROOM(
        "Council Room",
        315.49,
        Units.NONE
	),
    DIESEL_ENGINE_SIMULATOR(
        "Diesel Engnie Simulator",
        1_375.29,
        Units.DAYS
	),
    ECDIS_LABORARTORY(
        "ECDIS Laboratory",
        410.44,
        Units.NONE
	),
    FIRE_CENTRE_CLEANING(
        "Fire Fighting Centre - Cleaning",
        250.15,
        Units.NONE
	),
    FIRE_CENTRE_CONSUMABLES(
        "Fire Fighting Centre - Consumables",
        538.07,
        Units.NONE
	),
    FIRE_CENTRE_HIRE(
        "Fire Fighting Centre - Daily Hire Rate",
        701.43,
        Units.DAYS
	),
    FULL_MISSION_BRIDGE(
        "Full Mission Bridge Simulator",
        4_168.74,
        Units.NONE
	),
    GMDSS(
        "GMDSS Laboratory",
        410.44,
        Units.DAYS
	),
    // TODO(Andrew): the spreadsheet has no unit - probably incorrect
    LECTURES_PBB_ERCA_ET(
        "Lectures - PPB and ERCA/ET per hour",
        133.75,
        Units.NONE
	),
    LECTURES_PRACTICAL(
        "Lectures - Practical",
        99.04,
        Units.NONE
	),
    // TODO(Andrew): the spreadsheet has no unit - probably incorrect
    LECTURES_STANDARD(
        "Lectures - Standard per hour",
        174.59,
        Units.NONE
	),
    LIFE_SMALL_BOATS(
        "Life Boats/Small Boats",
        441.07,
        Units.DAYS
	),
    MDI(
        "MDI",
        625.87,
        Units.DAYS
	),
    MEETING_ROOM(
        "Meeting Room (Science Building)",
        250.15,
        Units.NONE
	),
    TEST_BASIN(
        "Model Test Basin and Towing Tank",
        1_375.29,
        Units.DAYS
	),
    PPB_CLASSROOMS(
        "PPB Classrooms (weekly)",
        616.68,
        Units.NONE
	),
    RADAR_SIUMLATOR(
        "Radar Simulator/SOC's",
        4_000.00,
        Units.DAYS
	),
    SHIP_SIMULATOR(
        "Ship Simulator",
        4_000.00,
        Units.DAYS
	),
    SIMULATOR_MODELLING(
        "Simulator Modelling & Techniques",
        667.73,
        Units.NONE
	),
    STEVEN_BROWN(
        "Steven Brown",
        175.61,
        Units.NONE
	),
    SURVIVAL_CENTRE_CONSUMABLES(
        "Survival Centre - Consumables",
        169.49,
        Units.NONE
	),
    SURVIVAL_CENTRE_HIRE(
        "Survival Centre - Daily Hire Rate",
        596.26,
        Units.DAYS
	),
    // TODO(Andrew): should perhaps have units of days
    TECHNICIAN(
        "Technician daily rate (FRB, Fire, Survival,MTB,",
        565.63,
        Units.NONE
	),
    TOWING_TANK(
        "Towing Tank",
        1_319.00,
        Units.DAYS
	),
    TUG_SIMULATOR_STAND_ALONE(
        "Tug Simulator - stand alone for tugmaster training",
        3_626.59,
        Units.NONE
	),
    TUG_SIMULATOR_ADD_ON(
        "Tug Simulator as add-on to Bridge",
        1_066.95,
        Units.NONE
	),

    ;
    String name;
    Double costPerUnit;
    Units units;
}