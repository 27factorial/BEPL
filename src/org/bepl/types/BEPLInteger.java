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

public final class BEPLInteger extends BEPLType<Integer> {
    private Integer value;

    public BEPLInteger(Integer value) {
        this.value = value;
    }

    public void setValue(Integer newValue) {
        this.value = newValue;
    }

    public Integer getValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
    
    public BEPLInteger clone() {
        return new BEPLInteger(value);
    }
    
    public String getType() {
    	return "Integer";
    }
}
