package com.example.fusic.Adapters;

public class Folder {
    private String path;
    private String bId;
    private String bName;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public Folder(String path, String bId, String bName) {
        this.path = path;
        this.bId = bId;
        this.bName = bName;
    }
}
