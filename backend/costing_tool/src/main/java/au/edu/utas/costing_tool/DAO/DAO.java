package au.edu.utas.costing_tool.DAO;


import java.util.Collection;


public interface DAO<T, ID>
{
    public T create(T entity);

    public T readOne(ID id);

    public Collection<T> readAll();

    public T update(T entity);

    public void delete(T entity);
}