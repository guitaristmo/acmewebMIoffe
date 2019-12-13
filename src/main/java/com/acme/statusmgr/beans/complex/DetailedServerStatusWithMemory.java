package com.acme.statusmgr.beans.complex;

import com.acme.statusmgr.beans.ServerStatusInterface;

/**
 * Represents a server status that includes a memory detail.
 */
public class DetailedServerStatusWithMemory extends DetailedServerStatus
{
    public DetailedServerStatusWithMemory(ServerStatusInterface baseServerStatus)
    { super(baseServerStatus); }

    /**
     * Overrides the base server status's getStatusDesc, adding
     * on to it the memory detail
     * @return a String representing the server status with memory detail
     */
    @Override
    public String generateStatusDesc() { return detailedServerStatus.generateStatusDesc() + ", and"+detailedServerStatus.accessServerManager().getCurrentMemory(); }
}