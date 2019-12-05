package com.acme.statusmgr.CommandExecution;

import com.acme.statusmgr.beans.complex.ServerStatus;

/**
 * Represents a basic request to get the
 * basic server status and return it
 */
public class GetBasicServerStatusCommand implements ICommand
{
    private ServerStatus result;
    private long id;
    private String template;
    private String name;

    public GetBasicServerStatusCommand(long id, String template, String name)
    {
        this.id = id;
        this.template = template;
        this.name = name;
    }

    @Override
    public void execute()
    {
        result = new ServerStatus(id, String.format(template, name));
        result.setStatusDesc(result.generateStatusDesc());
    }

    public ServerStatus getResult(){return result;}
}