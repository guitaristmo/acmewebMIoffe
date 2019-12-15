package com.acme.statusmgr.beans;


/**
 * A POJO that represents Disk Status requested by
 * a user. Builds on top of Disk status and adds
 * request id and content header.
 * It can be used to generate JSON for that status
 */
public class RequestedDiskStatus extends DiskStatus{

    private long id;
    private String contentHeader;

    /**
     * Construct a DiskStatus using info passed in for identification, and obtaining current
     * disk status from diskManager.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public RequestedDiskStatus(long id, String contentHeader)
    {
        this.id = id;
        this.contentHeader = contentHeader;
    }

    public long getId() {
        return id;
    }

    public String getContentHeader() { return contentHeader; }
}