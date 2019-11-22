package com.acme.statusmgr.beans.simple;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatusInterface;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A POJO that represents Server Status and can be used to generate JSON for that status
 * Used when the id and content header should not be displayed
 */
public class SimpleServerStatus implements ServerStatusInterface{

    private  long id;
    private String contentHeader;
    private String statusDesc = "Unknown";

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public SimpleServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        // Obtain current status of server
        this.statusDesc = ServerManager.getCurrentServerStatus();
    }

    public SimpleServerStatus() {

    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public long getId() {
        return 0;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getContentHeader() { return null; }


    public String getStatusDesc() {
        return statusDesc;
    }


}
