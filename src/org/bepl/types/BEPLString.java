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

public final class BEPLString extends BEPLType<String> {
    private String value;

    public BEPLString(String value) {
        // Removes the beginning quotes from the string.
        if (value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"')
            this.value = value.substring(1, value.length() - 1);
        else
            this.value = value;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }
    
    public BEPLString clone() {
        return new BEPLString(value);
    }
    
    public String getType() {
    	return "String";
    }
}
