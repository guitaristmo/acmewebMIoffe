package com.acme.statusmgr.beans.simple;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatusInterface;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Service;

/**
 * Abstract base class for all simple detailed Server Status objects.
 * Implements the ServerStatusInterface so that all deriving
 * objects can be returned by the status controller.
 * Follows the decorator pattern to allow deriving objects
 * to modify the statusDesc of the basic server status object.
 * <p>
 * It reroutes methods of the basic server status object which
 * do not need to be touched by deriving objects back to the
 * base server status object.
 * <p>
 * It does not return the the id and content header fields
 */
public abstract class SimpleDetailedServerStatus implements ServerStatusInterface
{
    ServerStatusInterface detailedServerStatus;

    public SimpleDetailedServerStatus(ServerStatusInterface baseServerStatus) { detailedServerStatus = baseServerStatus; }

    public void setStatusDesc(String statusDesc){detailedServerStatus.setStatusDesc(statusDesc);}

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public long getId() { return 0; }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getContentHeader() {
        return null;
    }

    abstract public String generateStatusDesc();

    public String getStatusDesc(){return detailedServerStatus.getStatusDesc();}

    @Override
    public ServerManager accessServerManager() { return detailedServerStatus.accessServerManager(); }
}