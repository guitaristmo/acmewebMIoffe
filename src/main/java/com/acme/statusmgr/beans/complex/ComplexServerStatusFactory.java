package com.acme.statusmgr.beans.complex;

import com.acme.statusmgr.beans.ServerStatusFactoryInterface;
import com.acme.statusmgr.beans.ServerStatusInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Factory for creating complex server status objects
 * and complex detailed server status objects
 * Autowiring makes this class the default for serverStatus factories
 */
@Service
@Primary
public class ComplexServerStatusFactory implements ServerStatusFactoryInterface
{
    @Override
    public ServerStatusInterface getServerStatus(long id, String contentHeader) {
        return new ServerStatus(id, contentHeader);
    }

    @Override
    public DetailedServerStatus getDetailedServerStatusWithExtensions(ServerStatusInterface serverStatus) {
        return new DetailedServerStatusWithExtensions(serverStatus);
    }

    @Override
    public DetailedServerStatus getDetailedServerStatusWithMemory(ServerStatusInterface serverStatus) {
        return new DetailedServerStatusWithMemory(serverStatus);
    }

    @Override
    public DetailedServerStatus getDetailedServerStatusWithOperations(ServerStatusInterface serverStatus) {
        return new DetailedServerStatusWithOperations(serverStatus);
    }
}
