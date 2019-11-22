package com.acme.statusmgr.beans.simple;

import com.acme.statusmgr.beans.ServerStatusFactoryInterface;
import com.acme.statusmgr.beans.ServerStatusInterface;
import org.springframework.stereotype.Service;

/**
 * Factory for creating simple server status objects
 * and simple detailed server status objects
 * Annotation @Service allows for Swing Injection
 */
@Service
public class SimpleServerStatusFactory implements ServerStatusFactoryInterface
{
    @Override
    public ServerStatusInterface getServerStatus(long id, String contentHeader) {
        return new SimpleServerStatus(id, contentHeader);
    }

    @Override
    public ServerStatusInterface getDetailedServerStatusWithExtensions(ServerStatusInterface serverStatus) {
        return new SimpleDetailedServerStatusWithExtensions(serverStatus);
    }

    @Override
    public ServerStatusInterface getDetailedServerStatusWithMemory(ServerStatusInterface serverStatus) {
        return new SimpleDetailedServerStatusWithMemory(serverStatus);
    }

    @Override
    public ServerStatusInterface getDetailedServerStatusWithOperations(ServerStatusInterface serverStatus) {
        return new SimpleDetailedServerStatusWithOperations(serverStatus);
    }
}
