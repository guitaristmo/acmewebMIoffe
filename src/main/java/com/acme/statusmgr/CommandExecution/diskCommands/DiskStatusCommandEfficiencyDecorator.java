package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.DiskStatusUnavailableException;
import com.acme.statusmgr.beans.DiskStatus;

/**
 * A DiskStatusCommand Decorator that adds efficiency to
 * the diskStatus Command.
 * It keeps track of the last diskStatus cmd run. If the
 * last one was withing 60 secs, it does not execute another
 * diskStatus command. Rather is sets the results of the
 * current diskStatus object which will be returned to have
 * the same DiskStatus message as the last diskStatus executed.
 * If previous data is too old, it will return available after
 * having started another diskStatus Command.
 */
public class DiskStatusCommandEfficiencyDecorator extends DiskStatusCommandDecorator
{
    private static DiskStatus lastDiskStatus;

    public DiskStatusCommandEfficiencyDecorator(IDiskStatusCommand baseDiskStatusCommand) { super(baseDiskStatusCommand); }

    /**
     * Checks if last diskStatus was checked within the minute.
     * If so, it sets the result of diskStatus to the old result.
     * Otherwise, it spins up a new thread to run the diskStatus command
     * and throws an error that the diskStatus is currently unavailable.
     */
    @Override
    public void execute()
    {
        if(lastDiskStatus != null && System.currentTimeMillis() - lastDiskStatus.getTimeExecuted() < 60_000)
            super.getResult().setDiskCommandOutput(lastDiskStatus.getDiskCommandOutput());
        else
        {
            Thread diskStatusThread = new Thread(new GetDiskStatusThread());
            diskStatusThread.start();
            throw new DiskStatusUnavailableException();
        }
    }

    /**
     * Private inner class that calls execute
     * on the base DiskStatus Command Object.
     * Implements runnable and is meant to be
     * run on a different thread.
     */
    private class GetDiskStatusThread implements Runnable
    {
        @Override
        public void run()
        {
            baseDiskStatusCommand.execute();
            lastDiskStatus = baseDiskStatusCommand.getResult();
        }
    }
}