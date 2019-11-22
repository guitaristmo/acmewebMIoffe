package com.acme.statusmgr.beans.complex;

import com.acme.Application;
import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatusInterface;

/**
 * A POJO that represents Server Status and can be used to generate JSON for that status
 */
public class ServerStatus implements ServerStatusInterface {

    private  long id;
    private String contentHeader;
    private String statusDesc = "Unknown";

    /**
     * This class now uses an instance of ServerManager
     */
    private ServerManager serverManager;

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        // Obtain and save reference to the instance of ServerManager
        serverManager = (ServerManager) Application.getApplicationContext().getBean("serverManager");
    }

    public long getId() {
        return id;
    }

    public String getContentHeader() {

        return contentHeader;
    }

    public String getStatusDesc() {
        return serverManager.getCurrentServerStatus();
    }

    public ServerManager accessServerManager(){return serverManager;}
}
