package com.acme.statusmgr.beans.simple;

import com.acme.statusmgr.beans.ServerStatusInterface;

/**
 * Represents a server status that includes an operations detail.
 */
public class SimpleDetailedServerStatusWithOperations extends SimpleDetailedServerStatus
{
    public SimpleDetailedServerStatusWithOperations(ServerStatusInterface baseServerStatus)
    { super(baseServerStatus); }

    /**
     * Overrides the base server status's getStatusDesc, adding
     * on to it the operations detail
     * @return a String representing the server status with operations detail
     */
    @Override
    public String generateStatusDesc() { return detailedServerStatus.generateStatusDesc() +", and"+ detailedServerStatus.accessServerManager().getCurrentOperations(); }
}