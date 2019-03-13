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

import java.math.BigDecimal;

public final class BEPLNumber extends BEPLType<BigDecimal> {
    private BigDecimal value;

    public BEPLNumber(BigDecimal value) {
        this.value = value;
    }

    public void setValue(BigDecimal newValue) {
        this.value = newValue;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
    
    public BEPLNumber clone() {
        return new BEPLNumber(value);
    }
    
    public String getType() {
    	return "Integer";
    }
}
