package com.acme.statusmgr.beans;

import com.acme.diskmgr.DiskManager;

/**
 * A POJO that represents Disk Status and can be used to generate JSON for that status
 */
public class DiskStatus {

    private long id;
    private String contentHeader;
    private String diskCommand = "chkdsk c:";
    private String diskCommandOutput;

    /**
     * Construct a DiskStatus using info passed in for identification, and obtaining current
     * disk status from diskManager.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public DiskStatus(long id, String contentHeader)
    {
        this.id = id;
        this.contentHeader = contentHeader;
    }

    public long getId() {
        return id;
    }

    public String getContentHeader() { return contentHeader; }

    public String getDiskCommand(){return diskCommand;}

    public String getDiskCommandOutput(){return diskCommandOutput;}

    public void setDiskCommandOutput(String diskCommandOutput){this.diskCommandOutput = diskCommandOutput;}

    public String generateDiskCommandOutput(){return DiskManager.checkDiskStatus();}
}