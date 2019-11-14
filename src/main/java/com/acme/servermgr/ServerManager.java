package com.acme.servermgr;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project
 */
public class ServerManager {

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    static public String getCurrentServerStatus() {
        return "Server is up";
    }

    /**
     * Get the current operating status of this server
     * @return a descriptive string about the servers operating status
     */
    static public String getCurrentOperations(){ return ", and is operating normally";}

    /**
     * Get the extensions this server is currently using
     * @return a descriptive string about the current extensions
     */
    static public String  getCurrentExtension()
    {return  ", and is using these extensions - [Hypervisor, Kubernetes, RAID-6]";}

    /**
     * Get the current memory status of this server
     * @return a descriptive string about the servers memory status
     */
    static public String getCurrentMemory(){return ", and its memory is Running low";}

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    static public Boolean isOperatingNormally()
    {
        return true;
    }
}
