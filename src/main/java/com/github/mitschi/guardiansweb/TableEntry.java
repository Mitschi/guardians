package com.github.mitschi.guardiansweb;

public class TableEntry {
    private String name;
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return String.format("%s,%s,\n", name, url);
    }
}
