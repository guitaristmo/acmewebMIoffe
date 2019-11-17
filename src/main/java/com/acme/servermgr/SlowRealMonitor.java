package com.acme.servermgr;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Representation of the real monitor for use in
 * the actual product, made for a school project.
 */
@Service
@Primary
public class SlowRealMonitor implements MonitorableServer {
    @Override
    public String determineServerStatus() {
        boolean goodStatus = true;
        int origNumCPUs;
        Runtime rt = Runtime.getRuntime();

        origNumCPUs = rt.availableProcessors();
        wait4seconds();
        if (rt.availableProcessors() != origNumCPUs) {
            goodStatus = false;
        }

        return goodStatus ? "Server is up and running well, #CPUs available is: " + origNumCPUs
                : "Server is up with a varying number of available CPUs";
    }

    private void wait4seconds()
    {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String determineCurrentOperations() {
        Runtime rt = Runtime.getRuntime();
        return ", and is operating normally";
    }

    @Override
    public String determineCurrentExtension() {
        return ", and is using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }

    @Override
    public String determineCurrentMemory() {
        Runtime rt = Runtime.getRuntime();
        return ", and has "+rt.freeMemory()+ " free memory";
    }

    @Override
    public Boolean isOperatingNormally() {
        boolean goodStatus = true;
        int origNumCPUs;
        Runtime rt = Runtime.getRuntime();

        origNumCPUs = rt.availableProcessors();
        wait4seconds();
        if (rt.availableProcessors() != origNumCPUs) {
            goodStatus = false;
        }
        return goodStatus;
    }
}
