package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.diskmgr.DiskManager;
import com.acme.statusmgr.CheckDiskCommandConcurrencyException;

/**
 * DiskStatus command decorator that ensures that no
 * more than 1 Disk check commands are ever running
 * at the same time.
 * If you try to run the check command while another
 * is running, a CheckDiskCommandConcurrencyException
 * will be thrown.
 */
public class DiskStatusCommandConcurrencyDecorator extends DiskStatusCommandDecorator
{
    public DiskStatusCommandConcurrencyDecorator(IDiskStatusCommand baseDiskStatusCommand)
    { super(baseDiskStatusCommand); }

    @Override
    public void execute()
    {
        if(DiskManager.getInstance().numTestsRunning() > 1)
            throw new CheckDiskCommandConcurrencyException();
        else
            super.execute();
    }
}