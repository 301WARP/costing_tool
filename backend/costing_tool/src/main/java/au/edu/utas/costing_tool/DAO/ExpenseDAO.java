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

import au.edu.utas.costing_tool.Model.Expense;

@Component
public class ExpenseDAO implements DAO<Researcher, Long>
{
	// =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final EntityManager em;


    // =========================================================================
    // Constructors
    // =========================================================================

    public ExpenseDAO(EntityManager em)
    {
        this.em = em;
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @Override
    public void create(Expense expense)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(expense);
        t.commit();
    }

    @Override
    public Contribution readOne(ContributionID id)
    {
        return em.find(Expense.class, id);
    }

    // uhhh ///
    @Override
    public List<Contribution> readAll()
    {
        String query = "select r from Contribution r";

        return em
            .createQuery(query, Contribution.class)
            .getResultList();
    }

    @Override
    public void update(Expense expense)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.merge(expense);
        t.commit();
    }

    @Override
    public void delete(Expense expense)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.remove(expense);
        t.commit();
    }   
}
