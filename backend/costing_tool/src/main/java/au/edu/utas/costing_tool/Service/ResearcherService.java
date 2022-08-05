package au.edu.utas.costing_tool.Service;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// =============================================================================
// Package Imports
// =============================================================================

import au.edu.utas.costing_tool.DAO.ResearcherDAO;
import au.edu.utas.costing_tool.Model.Researcher;

@Service
public class ResearcherService
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ResearcherDAO researcherDAO;
    public ResearcherDAO resDAO() {return this.researcherDAO;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherService(ResearcherDAO researcherDAO)
    {
        this.researcherDAO = researcherDAO;
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    public List<Researcher> listAllResearchers()
    {
        return resDAO().readAll();
    }

    public Researcher findResearcher(Long id)
    {
        return resDAO().readOne(id);
    }
}