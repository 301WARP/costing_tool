package au.edu.utas.costing_tool.Util;


// =============================================================================
// External Imports
// =============================================================================

import java.io.Serializable;
import java.util.Objects;


public class Pair<K,V> implements Serializable
{
    // =========================================================================
    // Properties
    // =========================================================================

    private K key;
    public K getKey() {return this.key;}
    public void setKey(K key) {this.key = key;}

    private V value;
    public V getValue() {return this.value;}
    public void setValue(V value) {this.value = value;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public Pair() {}

    public Pair(K key, V value)
    {
        this.setKey(key);
        this.setValue(value);
    }

    // =========================================================================
    // Methods
    // =========================================================================

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;

        if (this == o) return true;

        if (!(o instanceof Pair<?,?>)) return false;

        Pair<?,?> p = (Pair<?,?>)o;

        if (p.getKey().equals(this.getKey())
            && p.getValue().equals(this.getValue()))
            return true;

        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getKey(), this.getValue());
    }

    @Override
    public String toString()
    {
        return this.getKey().toString() + "=" + this.getValue().toString();
    }
}