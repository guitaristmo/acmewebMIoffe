package com.acme.statusmgr.beans;

import com.acme.diskmgr.DiskManager;

/**
 * A POJO that represents Disk Status and can be used to generate JSON for that status
 */
public class DiskStatus {

    private String diskCommand = "chkdsk c:";
    private String diskCommandOutput;
    private long timeExecuted;

    public String getDiskCommand(){return diskCommand;}

    public String getDiskCommandOutput(){return diskCommandOutput;}

    public void setDiskCommandOutput(String diskCommandOutput){this.diskCommandOutput = diskCommandOutput;}

    public String generateDiskCommandOutput(){return DiskManager.checkDiskStatus();}

    public long getTimeExecuted() { return timeExecuted; }

    public void setTimeExecuted(long timeExecuted) { this.timeExecuted = timeExecuted; }
}