package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.beans.DiskStatus;

/**
 * Base class for DiskStatusCommand Decorators
 * Used for leveraging decorator pattern to add
 * functionality to the DiskStatus command.
 */
public abstract class DiskStatusCommandDecorator implements IDiskStatusCommand
{
    private IDiskStatusCommand baseDiskStatusCommand;

    public DiskStatusCommandDecorator(IDiskStatusCommand baseDiskStatusCommand){this.baseDiskStatusCommand = baseDiskStatusCommand;}

    @Override
    public DiskStatus getResult() { return baseDiskStatusCommand.getResult(); }

    @Override
    public void execute() { baseDiskStatusCommand.execute(); }
}