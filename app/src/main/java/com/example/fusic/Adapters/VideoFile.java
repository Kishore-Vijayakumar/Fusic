package com.example.fusic.Adapters;

import java.io.Serializable;

public class VideoFile implements Serializable {
    private String path;
    private String vdname;
    private String vdid;
    private String bName;
    private String bId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVdname() {
        return vdname;
    }

    public void setVdname(String vdname) {
        this.vdname = vdname;
    }

    public String getVdid() {
        return vdid;
    }

    public void setVdid(String vdid) {
        this.vdid = vdid;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public VideoFile(String path, String vdname, String vdid, String bName, String bId) {

        this.path = path;
        this.vdname = vdname;
        this.vdid = vdid;
        this.bName=bName;
        this.bId=bId;
    }
}
