package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.beans.RequestedDiskStatus;

/**
 * Represents a basic request to get the
 * disk status and return it
 */
public class GetDiskStatusCommand implements IDiskStatusCommand
{
    private RequestedDiskStatus result;
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
        result = new RequestedDiskStatus(id, String.format(template, name));
        result.setDiskCommandOutput(result.generateDiskCommandOutput());
        result.setTimeExecuted(System.currentTimeMillis());
    }

    @Override
    public RequestedDiskStatus getResult(){return result;}
}