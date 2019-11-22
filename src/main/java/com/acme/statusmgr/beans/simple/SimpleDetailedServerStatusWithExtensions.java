package com.acme.statusmgr.beans.simple;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatusInterface;

/**
 * Represents a server status that includes an extensions detail.
 */
public class SimpleDetailedServerStatusWithExtensions extends SimpleDetailedServerStatus
{
    public SimpleDetailedServerStatusWithExtensions(ServerStatusInterface baseServerStatus)
    { super(baseServerStatus); }

    /**
     * Overrides the base server status's getStatusDesc, adding
     * on to it the extensions detail
     * @return a String representing the server status with extensions detail
     */
    @Override
    public String getStatusDesc() { return detailedServerStatus.getStatusDesc() + ", and"+ServerManager.getCurrentExtension(); }
}