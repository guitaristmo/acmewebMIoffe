package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.CommandExecution.GetBasicServerStatusCommand;
import com.acme.statusmgr.CommandExecution.GetDetailedServerStatusCommand;
import com.acme.statusmgr.CommandExecution.SimpleExecutor;
import com.acme.statusmgr.beans.complex.ServerStatus;
import com.acme.statusmgr.beans.simple.SimpleServerStatusFactory;
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
//        if(levelofdetail != null)
//        {
//            if(levelofdetail.equals("simple"))
//                serverStatusFactory = new SimpleServerStatusFactory();
//            else if(!levelofdetail.equals("complex"))
//                throw new InvalidComplexLevelException();
//        }
//
//        ServerStatusInterface status = serverStatusFactory.getServerStatus(counter.incrementAndGet(), String.format(template, name));
//
//        for (String detail : details)
//        {
//            switch (detail)
//            {
//                case "operations":
//                {
//                    status = serverStatusFactory.getDetailedServerStatusWithOperations(status);
//                    break;
//                }
//                case "extensions":
//                {
//                    status = serverStatusFactory.getDetailedServerStatusWithExtensions(status);
//                    break;
//                }
//                case "memory":
//                {
//                    status = serverStatusFactory.getDetailedServerStatusWithMemory(status);
//                    break;
//                }
//                default:
//                {
//                    throw new InvalidDetailException();
//                }
//            }
//        }
//        return status;
    }

    @RequestMapping("/disk/status")
    public DiskStatus getdiskStatus(@RequestParam(value="name", defaultValue="Anonymous") String name) {
        return new DiskStatus(counter.incrementAndGet(), String.format(template, name));
    }
}