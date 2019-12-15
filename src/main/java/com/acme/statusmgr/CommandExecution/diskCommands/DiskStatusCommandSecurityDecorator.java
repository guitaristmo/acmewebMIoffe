package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.InvalidNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

/**
 * A DiskStatusCommand Decorator that adds security to
 * the diskStatus Command. It requires that a request for
 * diskStatus comes with a name that is not 'Anonymous'
 */
public class DiskStatusCommandSecurityDecorator extends DiskStatusCommandDecorator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public DiskStatusCommandSecurityDecorator(IDiskStatusCommand baseDiskStatusCommand, String name)
    {
        super(baseDiskStatusCommand);
        if(name == null || name.equals("Anonymous"))
            throw new InvalidNameException();
        LOGGER.info("User "+name+" was allowed to access disk status information");
    }
}