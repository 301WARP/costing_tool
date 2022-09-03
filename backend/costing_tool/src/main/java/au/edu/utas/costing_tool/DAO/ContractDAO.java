package au.edu.utas.costing_tool.DAO;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contract;


@Component
public class ContractDAO implements DAO<Contract, Long>
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private EntityManager em;


    // =========================================================================
    // Constructors
    // =========================================================================

    public ContractDAO(EntityManager em)
    {
        this.em = em;
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    @Override
    public Contract create(Contract Contract)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(Contract);
        t.commit();

        return Contract;
    }

    @Override
    public Contract readOne(Long id)
    {
        return em.find(Contract.class, id);
    }

    @Override
    public List<Contract> readAll()
    {
        String query = "select c from Contract c";

        return em
            .createQuery(query, Contract.class)
            .getResultList();
    }

    @Override
    public Contract update(Contract contract)
    {
        Consumer<EntityManager> action = a -> a.merge(contract);

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            action.accept(em);
            t.commit(); 
        }
        catch (RuntimeException e) {
            t.rollback();
            throw e;
        }

        /*
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.merge(contract);
        t.commit();
        */

        return contract;
    }

    @Override
    public void delete(Contract Contract)
    {
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.remove(Contract);
        t.commit();
    }
}