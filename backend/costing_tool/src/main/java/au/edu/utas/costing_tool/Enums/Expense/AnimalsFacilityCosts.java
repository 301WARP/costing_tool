package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum AnimalsFacilityCosts {
    AGISTMENT(
		"Agistment - mouse cages",
		14.00,
		Units.ITEMS_WEEKS
	),
    MICE_ADULT(
		"Adult mice",
		38.07,
		Units.ANIMALS
	),
    MICE_TIME_MATED(
		"Time Mated mice",
		78.35,
		Units.ANIMALS
	),
    MICE_TIME_MATED_FEMALES(
		"Own mice Time Mate females",
		21.68,
		Units.ANIMALS
	),
    MICE_NEONATE(
		"Adult mice",
		10.14,
		Units.ANIMALS
	),
    RAT_ADULT(
		"Adult rat each",
		48.11,
		Units.ANIMALS
	),
    RAT_TIME_MATED(
		"Time Mated rat each",
		111.04,
		Units.ANIMALS 
	),
    RAT_NEONATE(
		"Neonate rat each",
		14.63,
		Units.ANIMALS
	),
    MOUSE_CAGE(
		"Mouse cage",
		14.00,
		Units.ITEMS_WEEKS
	),
    RAT_CAGE(
		"Rat cage",
		20.00,
		Units.ITEMS_WEEKS
	),
    MOUSE_CAGE_QUARANTINE(
		"Quarantine mice cage",
		13.31,
		Units.ITEMS_WEEKS
	),
    MOUSE_CAGE_VIROLOGY(
		"Virology mosue cages - disposable",
		15.00,
		Units.ITEMS_WEEKS
	),
    TECHNICAL_FEE_BASIC(
		"Basic Technical Fee (tailing, earclipping, IP injections etc)",
		37.02,
		Units.HOURS
	),
    TECHNICAL_FEE_ADVANCED(
		"Advanced Technical Fee (I.V., bleeding, dissections etc)",
		64.77,
		Units.HOURS
	),
    TIME_MATE_CANCELLATION_FEE(
		"Cancellation Fee for Time mates (if less than 3 weeks notice given)",
		88.13,
		Units.CANCELLATIONS
	),
    EMBRYO_TRANSFER(
		"Embryo Transfer: actual costs to aquire transport, and house PLUS",
		555.21,
		Units.TRANSFERS
	),
    EMBRYO_CRYOPRESERVATION(
		"Embryo cryopreservation",
		2_220.83,
		Units.STRAINS
	),
    SPERM_CRYOPRESERVATION(
		"Sperm cryoprservation",
		925.34,
		Units.STRAINS
	),
    IVF(
		"IVF",
		1_665.62,
		Units.STRAINS
	),
    REDERIVATION(
		"Rederivation",
		1_850.69,
		Units.STRAINS
	),
    FOOD(
		"Food - sold to external organisations",
		63.36,
		Units.BAGS
	),
    CORN_COB_SAWSDUST(
		"Corn Cob/Sawdust - sold to external organisations",
		55.44,
		Units.BAGS
	),

    ;
    String name;
    Double costPerUnit;
    Units units;
}