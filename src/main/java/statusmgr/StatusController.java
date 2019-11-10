package statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import statusmgr.beans.*;

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

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();

    @RequestMapping("/status")
    public ServerStatus getServiceStatus(@RequestParam(value="name", defaultValue="Anonymous") String name, @RequestParam (required = false) List<String> details) {
        System.out.println("*** DEBUG INFO ***" + details);
        return new ServerStatus(counter.incrementAndGet(),
                            String.format(template, name));
    }

    /**
     * Deals with requests that specify details
     * @param name the name of the requester
     * @param details the details requested
     * @return a server status object with all the details requested
     */
    @RequestMapping(value = "/status/detailed")
    public ServerStatusInterface getDetailedServiceStatus(@RequestParam(value="name", defaultValue="Anonymous") String name, @RequestParam (required = true) List<String> details)
    {
        ServerStatusInterface status = new ServerStatus(counter.incrementAndGet(), String.format(template, name));

        for (String detail : details)
        {
            switch (detail)
            {
                case "operations":
                {
                    status = new DetailedServerStatusWithOperations(status);
                    break;
                }
                case "extensions":
                {
                    status = new DetailedServerStatusWithExtensions(status);
                    break;
                }
                case "memory":
                {
                    status = new DetailedServerStatusWithMemory(status);
                    break;
                }
                default:
                {
                    throw new InvalidDetailException("Invalid details option: junkERROR");
                }
            }
        }
        return status;
    }
}
