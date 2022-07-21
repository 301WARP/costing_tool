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

import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;


@Component
public class ContributionDAO implements DAO<Contribution, ContributionID>
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final EntityManager em;


    // =========================================================================
    // Constructors
    // =========================================================================

    public ContributionDAO(EntityManager em)
    {
        this.em = em;
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    @Override
    public void create(Contribution contribution)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(contribution);
        t.commit();
    }

    @Override
    public Contribution readOne(ContributionID id)
    {
        return em.find(Contribution.class, id);
    }

    @Override
    public List<Contribution> readAll()
    {
        String query = "select r from Researcher r";

        return em
            .createQuery(query, Contribution.class)
            .getResultList();
    }

    @Override
    public void update(Contribution contribution)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.merge(contribution);
        t.commit();
    }

    @Override
    public void delete(Contribution contribution)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.remove(contribution);
        t.commit();
    }
}