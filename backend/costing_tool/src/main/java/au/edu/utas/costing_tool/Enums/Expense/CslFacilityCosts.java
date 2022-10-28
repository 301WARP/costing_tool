package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CslFacilityCosts {
    LIQUID_NITROGEN(
		"Supply of liquid nitrogen",
		3.05,
		Units.LITRES
	),
    COMPUTING_SUPPORT(
		"Computing Support",
		72.00,
		Units.HOURS
	),
    CENTRAL_ELECTRONICS_WORKSHOP(
		"Central Electronics Workshop - Labour",
		72.00,
		Units.HOURS
	),
    MECHANICAL_WORKSHOP(
		"Mechanical Workshop - Labour",
		64.00,
		Units.HOURS
	),
    LAB_TECH_SUPPORT(
		"General Laboratory Technical Support",
		60.65,
		Units.HOURS
	),
    ELEMENTAL_ANALYSIS_STANDARD(
		"Elemental Analysis - standard",
		20.00,
		Units.SAMPLES
	),
    ELEMENTAL_ANALYSIS_PRE_PACKED(
		"Elemental Analysis - pre-packed",
		16.00,
		Units.SAMPLES
	),
    ELEMENTAL_ANALYSIS_HIGH_PRECISION(
		"Elemental Analysis - high precision",
		30.00,
		Units.SAMPLES
	),
    ELEMENTAL_ANALYSIS_SPECIAL(
		"Elemental Analysis - special rate",
		13.00,
		Units.SAMPLES
	),
    ELEMENTAL_ANALYSIS_COAL(
		"Elemental Analysis - coal analysis",
		20.00,
		Units.SAMPLES
	),
    ELECTRON_MICROPROBE_NORMAL(
		"Electron Microprobe (JEOL JXA 8530) - Normal",
		42.50,
		Units.HOURS
	),
    ELECTRON_MICROPROBE_AFTER_HOURS(
		"Electron Microprobe (JEOL JXA 8530) - After Hours",
		28.50,
		Units.HOURS
	),
    ELECTRON_MICROPROBE_CARBON(
		"Electron Microprobe (JEOL JXA 8530) - carbon coating",
		6.50,
		Units.BATCHES
	),
    ELECTRON_MICROPROBE_SPUTTER(
		"Electron Microprobe (JEOL JXA 8530) - sputter coating (Au or Pr per batch)",
		6.50,
		Units.BATCHES
	),
    FEI_MLA_NORMAL(
		"FEI MLA 650 - normal",
		42.50,
		Units.HOURS
	),
    FEI_MLA_AFTER_HOURS(
		"FEI MLA 650 - after hours",
		28.50,
		Units.HOURS
	),
    FEI_MLA_CARBON(
		"FEI MLA 650 - carbon coating",
		6.50,
		Units.BATCHES
	),
    FEI_MLA_SPUTTER(
		"FEI MLA 650 - sputter coating (Au or Pt)",
		6.50,
		Units.BATCHES
	),
    FSESM_NORMAL(
		"FSESM Hitachi SU-70 - normal",
		42.50,
		Units.HOURS
	),
    FSESM_AFTER_HOURS(
		"FSESM Hitachi SU-70 - after hours",
		28.50,
		Units.HOURS
	),
    FSESM_CARBON(
		"FSESM Hitachi SU-70 - carbon coating",
		6.50,
		Units.BATCHES
	),
    FSESM_SPUTTER(
		"FSESM Hitachi SU-70 - sputter coating (Au or Pt)",
		6.50,
		Units.BATCHES
	),
    MICROXRF_NORMAL(
		"MicroXRF (Horiba XRT-7000V) - normal",
		26.50,
		Units.HOURS
	),
    MICROXRF_AFTER_HOURS(
		"MicroXRF (Horiba XRT-7000V) - after hours",
		18.50,
		Units.HOURS
	),
    EMXRM_LEADER(
		"EMXRM - STAFF SUPPORT (Facility Leader)",
		90.95,
		Units.HOURS
	),
    EMXRM_ANALYST(
		"EMXRM - STAFF SUPPORT (Lab Analyst)",
		63.36,
		Units.HOURS
	),
    ICP_MS_ANALYSIS(
		"ICP-MS Facility - Analysis",
		39.00,
		Units.HOURS
	),
    ICP_MS_LEADER(
		"ICPMS STAFF SUPPORT (Facility Leader)",
		90.95,
		Units.HOURS
	),
    FRAGMENT_ANALYSER_INJECTIONS(
		"Molecular Genetics - Fragment Analyser (injections)",
		1.60,
		Units.ROW_12
	),
    FRAGMENT_ANALYSER_PLATES(
		"Molecular Genetics - Fragment Analyser (plates)",
		3.70,
		Units.PLATE
	),
    FRAGMENT_ANALYSER_GENOMIC(
		"Molecular Genetics - Fragment Analyser (genomic reagent)",
		4.00,
		Units.SAMPLES
	),
    FRAGMENT_ANALYSER_DNA(
		"Molecular Genetics - Fragment Analyser (DNA fragment reagent)",
		2.50,
		Units.SAMPLES
	),
    FRAGMENT_ANALYSER_RNA(
		"Molecular Genetics - Fragment Analyser (RNA reagent)",
		4.00,
		Units.SAMPLES
	),
    // TODO(ANDREW): units seem wrong
    GENETICS_LAB(
		"Molecular Genetics - Genetics lab usage (quarterly)",
		235.00,
		Units.SAMPLES
	),
    GENETICS_LIQUID_NITROGEN(
		"Molecular Genetics - liquid nitrogen (100ml)",
		1.20,
		Units.SAMPLES
	),
    PIRO_NORMAL(
		"PIRO Liquid handling robot - normal",
		5.22,
		Units.HOURS
	),
    PIRO_50UL(
		"PIRO Liquid handling robot - 50ul",
		15.35,
		Units.BOX_96
	),
    PIRO_200UL(
		"PIRO Liquid handling robot - 200ul",
		15.35,
		Units.BOX_96
	),
    PCR(
		"PCR machine usage",
		4.00,
		Units.HOURS
	),
    ROTORGENE_NORMAL(
		"Rotogene - Realtime PCR - normal",
		4.40,
		Units.DISCS
	),
    ROTORGENE_CORBETT(
		"Rotogene - Realtime PCR - Corbett genedisc",
		12.79,
		Units.DISCS
	),
    ROTORGENE_CORBETT_FILM(
		"Rotogene - Realtime PCR - Corbett genedisc sealing film",
		1.28,
		Units.SAMPLES
	),
    BECKMAN_SAMPLES(
		"Sequencer Beckman CEQ - samples",
		4.09,
		Units.SAMPLES
	),
    BECKMAN_ROWS(
		"Sequencer Beckman CEQ - rows",
		30.69,
		Units.ROW_8
	),
    BECKMAN_PLATES(
		"Sequencer Beckman CEQ - plates",
		0.23,
		Units.TIPS
	),
    MYRA(
		"MYRA robot tips",
		12.79,
		Units.DISCS
	),
    BIOSYSTEMS_SEQUENCER(
		"Applied Biosystems sequencer",
		1.28,
		Units.SAMPLES
	),
    CAS_ROBOT(
		"CAS 1200N Robot",
		5.22,
		Units.HOURS
	),
    CAS_TIPS(
		"CAS 1200N 100ul tips",
		11.76,
		Units.BOX_96
	),
    GENETICS_LEADER(
		"Molecular Genetics - STAFF SUPPORT (Faciltiy Leader)",
		82.39,
		Units.HOURS
	),
    GENETICS_BIOLOGIST(
		"Molecular Genetics - STAFF SUPPORT (Molecular Biologist)",
		63.36,
		Units.HOURS
	),
    NMR_400_NORMAL(
		"Nuclear Magnetic Resonance 400 MHz - normal usage",
		42.00,
		Units.HOURS
	),
    NMR_400_AFTER_HOURS(
		"Nuclear Magnetic Resonance 400 MHz - after hours usage",
		24.50,
		Units.HOURS
	),
    // TODO(Andrew): missing units seem erroneous
    NMR_600_NORMAL(
		"Nuclear Magnetic Resonance 600 MHz - normal usage",
		42.00,
		Units.NONE
	),
    // TODO(Andrew): missing units seem erroneous
    NMR_600_AFTER_HOURS(
		"Nuclear Magnetic Resonance 600 MHz - after hours usage",
		24.50,
		Units.NONE
	),
    NMR_600_SAMPLES(
		"Nuclear Magnetic Resonance 600 MHz - samples for NMR QA",
		22.50,
		Units.SAMPLES
	),
    NMR_LEADER(
		"Nuclear Magnetic Resonance - STAFF SUPPORT (Facility Leader)",
		83.36,
		Units.HOURS
	),
    MASS_SPEC_GC(
		"Mass Spectrometer Consumables - GC Insert",
		1.75,
		Units.SAMPLES
	),
    MASS_SPEC_VIALS(
		"Mass Spectrometer Consumables - HLPC Vials",
		1.75,
		Units.SAMPLES
	),
    UPLC_NORMAL(
		"UPLC - Ultra Performance Liquid Chromatography - normal",
		35.00,
		Units.HOURS
	),
    UPLC_HR(
		"UPLC 1 HR @ normal hourly rate",
		33.76,
		Units.SAMPLES
	),
    UPLC_FILTERS(
		"UPLC - Ultra Performance Liquid Chromatography - Syringe filters",
		2.45,
		Units.SAMPLES
	),
    UPLC_CARTRIDGE(
		"UPLC - Ultra Performance Liquid Chromatography - C18 cartridge",
		7.10,
		Units.SAMPLES
	),
    UPLC_XEVO_NORMAL(
		"UPLC-Xevo triple quadrupole MS - normal",
		41.00,
		Units.HOURS
	),
    UPLC_XEVO_GC(
		"UPLC-Xevo triple quadrupole MS - GC insert",
		1.75,
		Units.SAMPLES
	),
    UPLC_XEVO_VIALS(
		"UPLC-Xevo triple quadrupole MS - HPLC Vials",
		1.75,
		Units.SAMPLES
	),
    UPLC_XEVO_FILTERS(
		"UPLC-Xevo triple quadrupole MS - Syringe filters",
		2.45,
		Units.SAMPLES
	),
    UPLC_XEVO_CARTRIDGE(
		"UPLC-Xevo triple quadrupole MS - C18 cartridge",
		7.10,
		Units.SAMPLES
	),
    UPLC_XEVO_ESI(
		"UPLC-Xevo triple quadrupole MS - Xevo MS-ESI",
		22.00,
		Units.SAMPLES
	),
    UPLC_XEVO_NEG(
		"UPLC-Xevo triple quadrupole MS - Xevo MS-ESI-NEG",
		22.00,
		Units.SAMPLES
	),
    UPLC_XEVO_APCI(
		"UPLC-Xevo triple quadrupole MS - Xevo MS-APCI",
		22.00,
		Units.SAMPLES
	),
    UPLC_XEVO_MSMS(
		"UPLC-Xevo triple quadrupole MS - Xevo MS-MSMS",
		22.00,
		Units.SAMPLES
	),
    UPLC_XEVO_HPLC(
		"UPLC-Xevo triple quadrupole MS - Xevo MS-HPLC",
		22.00,
		Units.SAMPLES
	),
    VARIAN_GC_NORMAL(
		"Varian Gas Chromatography - normal",
		27.62,
		Units.SAMPLES
	),
    VARIAN_GC_HOURLY(
		"Varian GC - hourly rate",
		28.50,
		Units.SAMPLES
	),
    VARIAN_MS_NORMAL(
		"Varian Quadrupole Mass Spectrometer - normal",
		35.00,
		Units.HOURS
	),
    ORGANIC_MS_LEADER(
		"Organic MS - STAFF SUPPORT (Facility Leader)",
		83.36,
		Units.HOURS
	),
    ORGANIC_MS_TECHNICAL(
		"Organic MS - STAFF SUPPORT (Technical)",
		83.36,
		Units.HOURS
	),
    LTQ_NORMAL(
		"High Flow LTQ Orbitrap Mass Spectrometer - normal",
		42.97,
		Units.SAMPLES
	),
    LTQ_INFUSION(
		"High Flow LTQ Orbitrap Mass Spectrometer - Accurate Mass Measurement (Infusion)",
		24.04,
		Units.SAMPLES
	),
    LTQ_TRYPSIN(
		"High Flow LTQ Orbitrap Mass Spectrometer - Trypsin (Proteomics Grade)",
		56.27,
		Units.SAMPLES
	),
    LTQ_VIALS(
		"High Flow LTQ Orbitrap Mass Spectrometer - HPLC Vials",
		1.69,
		Units.SAMPLES
	),
    QEXACTIVE_NORMAL(
		"Nano Flow QExactive HF - normal",
		65.00,
		Units.SAMPLES
	),
    QEXACTIVE_C18_CLEANUPUP(
		"Nano Flow QExactive HF - ZipTip C18 Peptide Sample clean-up",
		16.50,
		Units.SAMPLES
	),
    QEXACTIVE_TRYPSIN(
		"Nano Flow QExactive HF - Trypsin (Proteomics Grade)",
		58.00,
		Units.SAMPLES
	),
    QEXACTIVE_VIALS(
		"Nano Flow QExactive HF - HPLC Vials",
		1.75,
		Units.SAMPLES
	),
    PROTEOMICS_LEADER(
		"Proteomics MS - STAFF SUPPORT (Facility Leader)",
		98.55,
		Units.HOURS
	),
    EDEN_NORMAL(
		"3D printing Eden 260VC - Normal",
		21.99,
		Units.SAMPLES
	),
    EDEN_PRINTING(
		"3D printing Eden 260VC - grams of printing material",
		0.44,
		Units.GRAMS
	),
    EDEN_SUPPLY(
		"3D printing Eden 260VC - grams of suply material",
		0.24,
		Units.GRAMS
	),
    ISOPRIME_PRE(
		"Isoprime Stable Isotope Facility - pre packed",
		23.50,
		Units.SAMPLES
	),
    ISOPRIME_CSL(
		"Isoprime Stable Isotope Facility - CSL packed",
		28.50,
		Units.SAMPLES
	),
    ISOPRIME_CHALLENGING(
		"Isoprime Stable Isotope Facility - challenging sample surcharge",
		1.70,
		Units.SAMPLES
	),
    ISOPRIME_BULK(
		"Isoprime Stable Isotope Facility - bulk rate (>100 SAMPLES)",
		17.00,
		Units.SAMPLES
	),
    MULTIFLOW_STANDARD(
		"Multiflow standard",
		17.00,
		Units.SAMPLES
	),
    MULTIFLOW_BULK(
		"Multiflow (>100 SAMPLES)",
		13.50,
		Units.SAMPLES
	),
    OPTIMA_CO2(
		"Optima Stable Isotope Facility - CO2 analysis",
		25.00,
		Units.SAMPLES
	),
    OPTIMA_CO2_MODIFIED(
		"Optima Stable Isotope Facility - CO2 analysis (modified)",
		34.00,
		Units.SAMPLES
	),
    OPTIMA_GRAPHITE(
		"Optima Stable Isotope Facility - Graphite (Organic C analysis)",
		58.00,
		Units.SAMPLES
	),
    OPTIMA_CO2_INSTITUTIONS(
		"Optima Stable Isotope Facility - CO2 analysis for institutions",
		25.00,
		Units.SAMPLES
	),
    SIRA_S04(
		"Sira Stable Isotope Facility - SO4 Analsyis",
		58.00,
		Units.SAMPLES
	),
    SIRA_S02(
		"Sira Stable Isotope Facility - SO2 Analsyis",
		41.00,
		Units.SAMPLES
	),
    SIRA_S02_MODIFIED(
		"Sira Stable Isotope Facility - SO2 Analsyis (modified)",
		58.00,
		Units.SAMPLES
	),
    SIRA_PREPARATION(
		"Sira Stable Isotope Facility - Sample Preparation",
		37.00,
		Units.SAMPLES
	),
    SIRA_CLEANING(
		"Sira Stable Isotope Facility - SO2 Analysis (Cleaning laser slides)",
		7.70,
		Units.SAMPLES
	),
    ISOPREP_O2_18(
		"The Isoprep Stable Isotope Facility - O2-18 analysis",
		14.00,
		Units.SAMPLES
	),
    IRMS_LEADER(
		"IRMS - STAFF SUPPORT (Facility Leader)",
		78.31,
		Units.HOURS
	),
    IRMS_ASSISTANT(
		"IRMS - STAFF SUPPORT (Lab Assistant)",
		50.44,
		Units.HOURS
	),
    FT_IVS_NORMAL(
		"FT Infrared Vibrational Spectrometer - normal",
		40.00,
		Units.SAMPLES
	),
    FT_IVS_AFTER_HOURS(
		"FT Infrared Vibrational Spectrometer - after hours",
		24.50,
		Units.SAMPLES
	),
    FT_IVS_ATTENUATED_USER(
		"FT Infrared Vibrational Spectrometer - Attenuated total reflectance (user operated)",
		8.00,
		Units.SAMPLES
	),
    FT_IVS_ATTENUATED_CSL(
		"FT Infrared Vibrational Spectrometer - Attenuated total reflectance (CSL operated)",
		13.00,
		Units.SAMPLES
	),
    FT_IVS_DIFFUSE_USER(
		"FT Infrared Vibrational Spectrometer - Diffuse reflectance (user operated)",
		8.00,
		Units.SAMPLES
	),
    FT_IVS_DIFFUSE_CSL(
		"FT Infrared Vibrational Spectrometer - Diffuse reflectance (CSL operated)",
		13.00,
		Units.SAMPLES
	),
    FT_IVS_DIFFUSE_AUTO(
		"FT Infrared Vibrational Spectrometer - Diffuse reflectance (autosampler)",
		3.00,
		Units.SAMPLES
	),
    FT_IVS_LIBRARY(
		"FT Infrared Vibrational Spectrometer - Commercial Library search",
		30.00,
		Units.SAMPLES
	),
    FT_NIVS_NORMAL(
		"FT Near Infrared Vibrational Spectrometer - normal",
		40.00,
		Units.SAMPLES
	),
    FT_NIVS_AFTER_HOURS(
		"FT Near Infrared Vibrational Spectrometer - after hours",
		24.50,
		Units.SAMPLES
	),
    FT_NIVS_NIR_PROBE(
		"FT Near Infrared Vibrational Spectrometer - NIR analysis (probe)",
		3.00,
		Units.SAMPLES
	),
    FT_NIVS_NIR_VIAL(
		"FT Near Infrared Vibrational Spectrometer - NIR analysis (vial)",
		4.00,
		Units.SAMPLES
	),
    FT_NIVS_NIR_CSL(
		"FT Near Infrared Vibrational Spectrometer - NIR analysis (CSL operated)",
		8.00,
		Units.SAMPLES
	),
    RAMAN_NORMAL(
		"Raman Vibrational Spectrometer - normal",
		40.00,
		Units.SAMPLES
	),
    RAMAN_AFTER_HOURS(
		"Raman Vibrational Spectrometer - after hours",
		24.50,
		Units.SAMPLES
	),
    RAMAN_USER(
		"Raman Vibrational Spectrometer - Raman sample analysis (user operated)",
		8.00,
		Units.SAMPLES
	),
    RAMAN_CSL(
		"Raman Vibrational Spectrometer - Raman sample analysis (user operated)",
		13.00,
		Units.SAMPLES
	),
    RAMAN_LIBRARY(
		"Raman Vibrational Spectrometer - Commercial Library search",
		30.00,
		Units.SAMPLES
	),
    RAMAN_CHEMOMETRICS(
		"Raman Vibrational Spectrometer - Analysis for chemometrics",
		3.00,
		Units.SAMPLES
	),
    VS_LEADER(
		"Vibrational Spectrometer - STAFF SUPPORT (Facility Leader)",
		85.94,
		Units.HOURS
	),
    PSA_ASSISTED(
		"Particle Size Analysis (Assisted)",
		16.50,
		Units.HOURS
	),
    PSA_INDEPENDENT(
		"Particle Size Analysis (Independent)",
		8.00,
		Units.HOURS
	),
    PSA_INDEPENDENT_BULK(
		"Particle Size Analysis (Independent, minimum 30 samples/week)",
		5.30,
		Units.HOURS
	),
    PSA_FULL_SUPPORT(
		"Particle Size Analysis - full staff support",
		63.36,
		Units.HOURS
	),







    ;
    String name;
    Double costPerUnit;
    Units units;
}