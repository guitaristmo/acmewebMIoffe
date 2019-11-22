package com.acme.statusmgr.beans;

/**
 * Interface for all serverStatus factories
 * Declares the contract for creating server status
 * objects and detailed server status objects
 */
public interface ServerStatusFactoryInterface
{
    ServerStatusInterface getServerStatus(long id, String contentHeader);
    ServerStatusInterface getDetailedServerStatusWithExtensions(ServerStatusInterface serverStatus);
    ServerStatusInterface getDetailedServerStatusWithMemory(ServerStatusInterface serverStatus);
    ServerStatusInterface getDetailedServerStatusWithOperations(ServerStatusInterface serverStatus);
}