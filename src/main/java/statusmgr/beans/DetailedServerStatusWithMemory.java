package statusmgr.beans;

import servermgr.ServerManager;

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
    public String getStatusDesc() { return detailedServerStatus.getStatusDesc() + ServerManager.getCurrentMemory(); }
}