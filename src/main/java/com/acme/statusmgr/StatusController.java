package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.CommandExecution.diskCommands.DiskStatusCommandEfficiencyDecorator;
import com.acme.statusmgr.CommandExecution.diskCommands.DiskStatusCommandSecurityDecorator;
import com.acme.statusmgr.CommandExecution.diskCommands.IDiskStatusCommand;
import com.acme.statusmgr.CommandExecution.serverStatusCommand.GetBasicServerStatusCommand;
import com.acme.statusmgr.CommandExecution.serverStatusCommand.GetDetailedServerStatusCommand;
import com.acme.statusmgr.CommandExecution.diskCommands.GetDiskStatusCommand;
import com.acme.statusmgr.CommandExecution.SimpleExecutor;
import com.acme.statusmgr.beans.complex.ServerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.acme.statusmgr.beans.*;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach
 *
 *
 *
 */

@RestController
@RequestMapping("/server")
public class StatusController {

    @Autowired
    private ServerStatusFactoryInterface serverStatusFactory;

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();

    @RequestMapping("/status")
    public ServerStatus getServiceStatus(@RequestParam(value="name", defaultValue="Anonymous") String name, @RequestParam (required = false) List<String> details) {
        System.out.println("*** DEBUG INFO ***" + details);
        GetBasicServerStatusCommand command = new GetBasicServerStatusCommand(counter.incrementAndGet(), template, name);
        SimpleExecutor executor = new SimpleExecutor(command);
        executor.executeCommand();
        return command.getResult();
    }

    /**
     * Deals with requests that specify details
     * @param name the name of the requester
     * @param details the details requested
     * @return a server status object with all the details requested
     */
    @RequestMapping(value = "/status/detailed")
    public ServerStatusInterface getDetailedServiceStatus(@RequestParam(value="name", defaultValue="Anonymous") String name,
                                                          @RequestParam (required = true) List<String> details, @RequestParam (required = false) String levelofdetail)
    {
        GetDetailedServerStatusCommand command = new GetDetailedServerStatusCommand(counter.incrementAndGet(), template, name, details, levelofdetail, serverStatusFactory);
        SimpleExecutor executor = new SimpleExecutor(command);
        executor.executeCommand();
        return command.getResult();

    }

    @RequestMapping("/disk/status")
    public DiskStatus getDiskStatus(@RequestParam(value="name", defaultValue="Anonymous") String name) {
        long id = counter.incrementAndGet();
        IDiskStatusCommand command = new DiskStatusCommandSecurityDecorator(new DiskStatusCommandEfficiencyDecorator(new GetDiskStatusCommand(id, template, name)), name);
        SimpleExecutor executor = new SimpleExecutor(command);
        executor.executeCommand();
        return command.getResult();
    }
}