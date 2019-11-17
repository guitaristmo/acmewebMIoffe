package com.acme.servermgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple methods for use in school project
 */
@Component
public class ServerManager {

    /**
     * Reference to the server monitor which will be able
     * to get details about the server.
     * Swing will inject which server monitor will be used
     */
    @Autowired
    private MonitorableServer monitor;

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    public String getCurrentServerStatus() {
        return monitor.determineServerStatus();
    }

    /**
     * Get the current operating status of this server
     * @return a descriptive string about the servers operating status
     */
    public String getCurrentOperations(){ return monitor.determineCurrentOperations();}

    /**
     * Get the extensions this server is currently using
     * @return a descriptive string about the current extensions
     */
    public String  getCurrentExtension()
    {return monitor.determineCurrentExtension();}

    /**
     * Get the current memory status of this server
     * @return a descriptive string about the servers memory status
     */
    public String getCurrentMemory(){return monitor.determineCurrentMemory();}

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    public Boolean isOperatingNormally()
    {
        return monitor.isOperatingNormally();
    }
}
