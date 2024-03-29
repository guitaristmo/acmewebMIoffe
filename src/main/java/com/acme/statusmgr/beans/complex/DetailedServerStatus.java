package com.acme.statusmgr.beans.complex;

import com.acme.statusmgr.beans.ServerStatusInterface;

import com.acme.servermgr.ServerManager;

/**
 * Abstract base class for all detailed Server Status objects.
 * Implements the ServerStatusInterface so that all deriving
 * objects can be returned by the status controller.
 * Follows the decorator pattern to allow deriving objects
 * to modify the statusDesc of the basic server status object.
 * <p>
 * It reroutes methods of the basic server status object which
 * do not need to be touched by deriving objects back to the
 * base server status object.
 */
public abstract class DetailedServerStatus implements ServerStatusInterface
{
    ServerStatusInterface detailedServerStatus;

    public DetailedServerStatus(ServerStatusInterface baseServerStatus) { detailedServerStatus = baseServerStatus; }

    public void setStatusDesc(String statusDesc){detailedServerStatus.setStatusDesc(statusDesc);}

    public long getId() { return detailedServerStatus.getId(); }

    public String getStatusDesc(){return detailedServerStatus.getStatusDesc();}

    public String getContentHeader() {
        return detailedServerStatus.getContentHeader();
    }

    abstract public String generateStatusDesc();

    @Override
    public ServerManager accessServerManager() { return detailedServerStatus.accessServerManager(); }
}