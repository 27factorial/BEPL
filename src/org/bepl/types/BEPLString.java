package org.bepl.types;

public final class BEPLString extends BEPLType<String> {
    public static final String TYPE = "String";
    private String value;

    public BEPLString(String value) {
        // Removes the beginning quotes from the string.
        this.value = value.substring(1, value.length() - 1);
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
}
