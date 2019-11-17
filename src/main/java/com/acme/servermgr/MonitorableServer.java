package com.acme.servermgr;

/**
 * Interface that defines requirements for a server monitor
 */

public interface MonitorableServer
{
    /**
     * Get general description of the server
     * @return A String with basic server info
     */
    String determineServerStatus();

    /**
     * @return A String representing current operations the server is running
     */
    String determineCurrentOperations();

    /**
     * @return A String representing current extensions the server is running
     */
    String determineCurrentExtension();

    /**
     * @return A String representing the server's current memory
     */
    String determineCurrentMemory();

    /**
     * @return a boolean representing if the server is operating normally
     */
    Boolean isOperatingNormally();
}
