package com.acme.statusmgr.CommandExecution;

import com.acme.statusmgr.beans.DiskStatus;

/**
 * Represents a basic request to get the
 * disk status and return it
 */
public class GetDiskStatusCommand implements ICommand
{
    private DiskStatus result;
    private long id;
    private String template;
    private String name;

    public GetDiskStatusCommand(long id, String template, String name)
    {
        this.id = id;
        this.template = template;
        this.name = name;
    }

    @Override
    public void execute()
    {
        result = new DiskStatus(id, String.format(template, name));
        result.setDiskCommandOutput(result.generateDiskCommandOutput());
    }

    public DiskStatus getResult(){return result;}
}