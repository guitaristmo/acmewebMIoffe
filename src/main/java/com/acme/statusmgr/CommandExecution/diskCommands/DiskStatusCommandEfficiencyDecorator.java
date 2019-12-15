package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.beans.DiskStatus;

/**
 * A DiskStatusCommand Decorator that adds efficiency to
 * the diskStatus Command.
 * It keeps track of the last diskStatus cmd run. If the
 * last one was withing 60 secs, it does not execute another
 * diskStatus command. Rather is sets the results of the
 * current diskStatus object which will be returned to have
 * the same DiskStatus message as the last diskStatus executed.
 * Otherwise, it will run the command again
 */
public class DiskStatusCommandEfficiencyDecorator extends DiskStatusCommandDecorator
{
    private static DiskStatus lastDiskStatus;

    public DiskStatusCommandEfficiencyDecorator(IDiskStatusCommand baseDiskStatusCommand) { super(baseDiskStatusCommand); }

    @Override
    public void execute()
    {
        if(lastDiskStatus == null || System.currentTimeMillis() - lastDiskStatus.getTimeExecuted() > 60_000)
        {
            super.execute();
            lastDiskStatus = super.getResult();
        }
        super.getResult().setDiskCommandOutput(lastDiskStatus.getDiskCommandOutput());
    }
}