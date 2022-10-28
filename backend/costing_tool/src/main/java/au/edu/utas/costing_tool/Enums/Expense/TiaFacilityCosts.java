package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TiaFacilityCosts implements FacilityCosts {
    GLASSHOUSE_SPACE(
		"Glasshouse space (no labour)",
		7.65,
		Units.SQ_MTR_WEEK
	),
    GLASSHOUSE_SERVICE(
		"Glasshouse - fully serviced",
		12.75,
		Units.SQ_MTR_WEEK
	),
    GROWTH_CHAMBERS(
		"Growth Chambers",
		4.08,
		Units.DAYS
	),
    GROWTH_CHAMBERS_SERVICE(
		"Growth Chambers fully serviced",
		8.16,
		Units.DAYS
	),
    LABORATORY_SERVICES(
		"Laboratory Services",
		306.00,
		Units.YEARS
	),
    LABORATORY_EQUPIMENT(
		"Laboratory Equipment",
		153.00,
		Units.YEARS
	),
    EQUIPMENT_NON_ELECTRONIC(
		"Equipment (non electronic)",
		510.00,
		Units.YEARS
	),
    EQUIPMENT_ELECTRONIC(
		"Equipment (electronic)",
		510.00,
		Units.YEARS
	),
    DEHYDRATOR(
		"Dehydrator",
		1.02,
		Units.HOURS
	),
    TISSUE_CULTURE_SPACE(
		"Tissue culture lab space",
		6.12,
		Units.SQ_MTR_WEEK
	),
    TISSUE_CULTURE_SERVICE(
		"Tissue Culture Lab - fully serviced",
		15.30,
		Units.SQ_MTR_WEEK
	),
    COOLROOM(
		"Coolroom",
		42.50,
		Units.MONTHS
	),
    RESEARCH_PLOTS_SMALL(
		"Research Plots <.5 ha - access only",
		16_000.00,
		Units.HA_CROP
	),
    RESEARCH_PLOTS_LARGE(
		"Research Plots >.5 ha - access only",
		14_000.00,
		Units.HA_CROP
	),
    RESEARCH_PLOTS_SERVICE(
		"Research Plots fully services excluding data collection",
		3.50,
		Units.SQ_MTR
	),
    RESEARCH_PLOTS_SERVICE_DATA(
		"Research Plots fully serviced including data collection",
		4.50,
		Units.SQ_MTR
	),
    TRACTOR(
		"Tractor and Operator",
		175.00,
		Units.HOURS
	),
    LABOUR(
		"Labour",
		60.00,
		Units.HOURS
	),
    MOBILE_PHONES(
		"Mobile Phones",
		700.00,
		Units.ANNUM_PHONE
	),
    COMPUTERS(
		"computers",
		1_500.00,
		Units.ANNUM_FTE
	),


    ;
    String name;
    Double costPerUnit;
    Units units;
}