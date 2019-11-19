package com.acme.servermgr;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Fake server monitor that does not actually
 * monitor a server, but would be used for unit tests
 */
@Service
public class FakeMonitor implements MonitorableServer
{
    @Override
    public String determineServerStatus() {
        return "Server is up";
    }

    @Override
    public String determineCurrentOperations() {
        return " is operating normally";
    }

    @Override
    public String determineCurrentExtension() {
        return " is using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }

    @Override
    public String determineCurrentMemory() {
        return " its memory is Running low";
    }

    @Override
    public Boolean isOperatingNormally() {
        return true;
    }
}
