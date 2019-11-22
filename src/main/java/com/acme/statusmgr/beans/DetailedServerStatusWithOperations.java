package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Represents a server status that includes an operations detail.
 */
public class DetailedServerStatusWithOperations extends DetailedServerStatus
{
    public DetailedServerStatusWithOperations(ServerStatusInterface baseServerStatus)
    { super(baseServerStatus); }

    /**
     * Overrides the base server status's getStatusDesc, adding
     * on to it the operations detail
     * @return a String representing the server status with operations detail
     */
    @Override
    public String getStatusDesc() { return detailedServerStatus.getStatusDesc() + ", and"+detailedServerStatus.accessServerManager().getCurrentOperations(); }
}