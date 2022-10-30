package au.edu.utas.costing_tool.Enums.Expense;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Units {
    NONE("None"),

    // Time
    MINUTES("per minute"),
    HOURS("per hour"),
    DAYS("per day"),
    HOURS_60("per 60 hours"),
    WEEKS("per week"),
    MONTHS("per month"),
    YEARS("per year"),

    // Size
    KM("per kilometre"),
    SQ_MTR("per sqr meter, min charge $2500"),
    LITRES("per litre"),

    // Mass
    GRAMS("per gram"),

    // Size per Time
    SQ_MTR_WEEK("sq mtr/week"),

    // Size per Item
    HA_CROP("per ha/crop"),

    // Items
    ITEMS("per item"),
    SAMPLES("first sample"),
    UNITS("1 unit"),
    ANIMALS("per animal"),
    STRAINS("per strain"),
    TIPS("per tip"),
    PERSON("per person"),
    PARTICIPANTS("per participant"),
    FTES("per FTE on project"),

    // Items per Time
    ITEMS_YEARS("per item per year"),
    ANNUM_PHONE("Per annum per phone"),
    ANNUM_PERCENT("per FTE on project"),
    ANNUM_PERSON("per person per year"),
    ITEMS_WEEKS("per item per week"),
    ANNUM_FTE("Per annum per FTE"),

    // Events
    AUDITS("per audit"),
    TRANSFERS("per transfer"),
    CANCELLATIONS("per cancellation"),

    // Items per Events
    DIVE("per person per dive"),

    // Collections
    BATCHES("per batch"),
    BLOCKS("Per block"),
    BAGS("per bag"),
    ROW_12("per row of 12"),
    ROW_8("per row of 8"),
    PLATE("per plate"),
    BOX_96("per box of 96"),
    DISCS("per disc"),


    ;
    String name;
}