package com.acme.statusmgr.CommandExecution;

import com.acme.statusmgr.InvalidComplexLevelException;
import com.acme.statusmgr.InvalidDetailException;
import com.acme.statusmgr.beans.ServerStatusFactoryInterface;
import com.acme.statusmgr.beans.ServerStatusInterface;
import com.acme.statusmgr.beans.simple.SimpleServerStatusFactory;

import java.util.List;

/**
 * Represents a request to get the
 * detailed server status and return it
 */
public class GetDetailedServerStatusCommand implements ICommand
{
    private ServerStatusInterface status;
    private long id;
    private String template;
    private String name;
    private List<String> details;
    private String levelOfDetail;
    private ServerStatusFactoryInterface serverStatusFactory;

    public GetDetailedServerStatusCommand(long id, String template, String name, List<String> details, String levelofdetail, ServerStatusFactoryInterface serverStatusFactory)
    {
        this.id = id;
        this.template = template;
        this.name = name;
        this.details = details;
        this.levelOfDetail = levelofdetail;
        this.serverStatusFactory = serverStatusFactory;
    }

    @Override
    public void execute()
    {
        if(levelOfDetail != null)
        {
            if(levelOfDetail.equals("simple"))
                serverStatusFactory = new SimpleServerStatusFactory();
            else if(!levelOfDetail.equals("complex"))
                throw new InvalidComplexLevelException();
        }

        status = serverStatusFactory.getServerStatus(id, String.format(template, name));

        for (String detail : details)
        {
            switch (detail)
            {
                case "operations":
                {
                    status = serverStatusFactory.getDetailedServerStatusWithOperations(status);
                    break;
                }
                case "extensions":
                {
                    status = serverStatusFactory.getDetailedServerStatusWithExtensions(status);
                    break;
                }
                case "memory":
                {
                    status = serverStatusFactory.getDetailedServerStatusWithMemory(status);
                    break;
                }
                default:
                {
                    throw new InvalidDetailException();
                }
            }
        }
        status.setStatusDesc(status.generateStatusDesc());
    }

    public ServerStatusInterface getResult(){return status;}
}