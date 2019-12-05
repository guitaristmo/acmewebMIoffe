package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Interface for all objects representing Server Status.
 * To be used for all classes which want to be returned as a server status
 * in the status controller
 */
public interface ServerStatusInterface
{
    long getId();

    String getContentHeader();

    String getStatusDesc();

    String generateStatusDesc();

    void setStatusDesc(String statusDesc);

    ServerManager accessServerManager();
}