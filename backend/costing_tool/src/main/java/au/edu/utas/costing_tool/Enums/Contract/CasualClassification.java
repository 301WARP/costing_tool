package au.edu.utas.costing_tool.Enums.Contract;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CasualClassification {
    NONE("None"),

    // Academic
    LECTURING("Lecturing"),
    TUTORING_ACADEMIC("Tutoring (Academic)"),
    MARKING("Marking"),
    MUSIC_ACCOMPANYING("Music Accompanying"),
    CLINICAL_NURSE("Clinical Nurse Education"),
    RESEARCH_ACADEMIC("Researcher (Academic)"),
    OTHER_ACADEMIC("Other Academic Activities"),

    // Professional
    RESEARCH("Research"),
    PROFESSIONAL("Professional"),
	PIECEWORK("Piecework"),

    // Non Award Teaching
    ELICOS("ELICOS Uni.T.Consulting"),
	IELTS("IELTS Testing"),
	TUTORING("Tutoring"),
	ATAS_TUTORING("Riawunna ATAS Tutoring"),

    // Conservatorium/AMEB
    AMEB("AMEB"),
    ;

    String name;
}