package au.edu.utas.costing_tool.DAO;


// =============================================================================
// External Imports
// =============================================================================

import java.util.Collection;


public interface DAO<T, ID>
{
    public void create(T entity);

    public T readOne(ID id);

    public Collection<T> readAll();

    public void update(T entity);

    public void delete(T entity);
}