package com.github.mitschi.guardiansweb;

public class TableEntry {
    private String name;
    private String url;

    public void setName(String name) {
        if (!name.equals(""))
            this.name = name;
    }

    public void setUrl(String url) {
        if (!url.equals(""))
            this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,\n", name, url);
    }
}
