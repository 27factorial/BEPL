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

public final class BEPLDecimal extends BEPLType<Double> {
    private Double value;

    public BEPLDecimal(Double value) {
        this.value = value;
    }

    @Override
    public void setValue(Double newValue) {
        value = newValue;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        // Delegate to Double.
        return value.toString();
    }

    @Override
    public BEPLDecimal clone() {
        return new BEPLDecimal(value);
    }
    
    @Override
    public String getType() {
        return "String";
    }
}
