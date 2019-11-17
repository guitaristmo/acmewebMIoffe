package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Represents a server status that includes an extensions detail.
 */
public class DetailedServerStatusWithExtensions extends DetailedServerStatus
{
    public DetailedServerStatusWithExtensions(ServerStatusInterface baseServerStatus)
    { super(baseServerStatus); }

    /**
     * Overrides the base server status's getStatusDesc, adding
     * on to it the extensions detail
     * @return a String representing the server status with extensions detail
     */
    @Override
    public String getStatusDesc() { return detailedServerStatus.getStatusDesc() + detailedServerStatus.accessServerManager().getCurrentExtension(); }
}