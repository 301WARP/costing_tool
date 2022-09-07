package au.edu.utas.costing_tool.DAO;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

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
    @PersistenceContext
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
    public Contribution create(Contribution contribution)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(contribution);
        t.commit();

        return contribution;
    }

    @Override
    public Contribution readOne(ContributionID id)
    {
        return em.find(Contribution.class, id);
    }

    @Override
    public List<Contribution> readAll()
    {
        String query = "select c from Contribution c";

        return em
            .createQuery(query, Contribution.class)
            .getResultList();
    }

    @Override
    public Contribution update(Contribution contribution)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.merge(contribution);
        t.commit();

        return contribution;
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