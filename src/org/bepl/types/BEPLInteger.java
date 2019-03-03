package org.bepl.types;

public final class BEPLInteger extends BEPLType<Integer> {
    public static final String TYPE = "Integer";
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
}
