package org.bepl.types;

public abstract class BEPLType<T> {
    public static String TYPE;
    protected T value;
    
    public abstract void setValue(T newValue);
    public abstract T getValue();
    public abstract String toString();
}
