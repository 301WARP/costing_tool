package au.edu.utas.costing_tool.DAO;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Researcher;


@Component
public class ResearcherDAO implements DAO<Researcher, Long>
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final EntityManager em;


    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherDAO(EntityManager em)
    {
        this.em = em;
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    @Override
    public void create(Researcher researcher)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(researcher);
        t.commit();
    }

    @Override
    public Researcher readOne(Long id)
    {
        return em.find(Researcher.class, id);
    }

    @Override
    public List<Researcher> readAll()
    {
        String query = "select r from Researcher r";

        return em
            .createQuery(query, Researcher.class)
            .getResultList();
    }

    @Override
    public void update(Researcher researcher)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.merge(researcher);
        t.commit();
    }

    @Override
    public void delete(Researcher researcher)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.remove(researcher);
        t.commit();
    }
}