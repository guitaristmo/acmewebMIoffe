package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.beans.RequestedDiskStatus;

/**
 * A DiskStatusCommand Decorator that adds efficiency to
 * the diskStatus Command. It keeps track of the last
 * diskStatus cmd run. If the last one was withing 10 secs,
 * it does not execute another diskStatus command, and will
 * return the old results. Otherwise, it will run the cmd again
 */
public class DiskStatusCommandEfficiencyDecorator extends DiskStatusCommandDecorator
{
    static DiskStatus lastDiskStatus;
    private long id;
    private String template;
    private String name;
    private RequestedDiskStatus result;

    public DiskStatusCommandEfficiencyDecorator(IDiskStatusCommand baseDiskStatusCommand, long id, String template, String name)
    {
        super(baseDiskStatusCommand);
        this.id = id;
        this.template = template;
        this.name = name;
    }

    @Override
    public void execute()
    {
        result = new RequestedDiskStatus(id, String.format(template, name));

        if(lastDiskStatus == null || System.currentTimeMillis() - lastDiskStatus.getTimeExecuted() > 60_000)
        {
            super.execute();
            lastDiskStatus = super.getResult();
        }
        result.setDiskCommandOutput(lastDiskStatus.getDiskCommandOutput());
    }

    @Override
    public RequestedDiskStatus getResult() { return result; }
}