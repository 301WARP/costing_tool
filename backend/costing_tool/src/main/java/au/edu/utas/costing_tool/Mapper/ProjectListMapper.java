package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.ProjectListDTO;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.Project;


@Mapper
public interface ProjectListMapper
{
    @Mapping(target="id", source="project.id")
    @Mapping(target="name", source="project.name")
    @Mapping(target="startDate", source="project.startDate")
    @Mapping(target="endDate", source="project.endDate")
    @Mapping(   target="role", source="project.contributions",
                qualifiedByName="roleMap")
    @Mapping(   target="contractType", source="project.contributions",
                qualifiedByName="contractMap")
    ProjectListDTO
    map(Project project, @Context Long userId);


    @Named("roleMap")
    static
    String
    roleMap(List<Contribution> contributions, @Context Long userId)
    {
        if (userId == null)
            return null;

        return contributions    
            .stream()
            .filter(c -> c  .getContract()
                            .getResearcher()
                            .getStaffID()
                            .equals(userId))
            .map(c -> c.getRole())
            .findFirst()
            .orElse(null);
    }


    @Named("contractMap")
    static
    String
    contractMap(List<Contribution> contributions, @Context Long userId)
    {
        if (userId == null)
            return null;

        return contributions    
            .stream()
            .filter(c -> c  .getContract()
                            .getResearcher()
                            .getStaffID()
                            .equals(userId))
            .map(c -> c .getContract()
                        .getContractType()
                        .toString())
            .findFirst()
            .orElse(null);
    }
}