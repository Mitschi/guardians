package com.github.mitschi.guardiansweb;

public class TableEntry {
    private String country;
    private int value;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,\n", country, value);
    }
}
