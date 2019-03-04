/*
 * The Basic English-like Programming Language.
 * Created by Team Rocket
 * CS 143, Section 1415 @ TCC.
 * 
 * Credit to Shalitha Suranga for
 * the usage of Simplerlang in
 * early versions of BEPL.
 * Simplerlang is licensed under the MIT License.
 * https://github.com/shalithasuranga/simpler/blob/master/LICENSE
 */

package org.bepl.types;

public abstract class BEPLType<T> implements Cloneable {
    public static String TYPE;
    protected T value;
    
    public abstract void setValue(T newValue);
    public abstract T getValue();
    public abstract String toString();
    public abstract BEPLType<T> clone();
}
